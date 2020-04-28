package ejb.session.stateless;

import entity.PositionTypeEntity;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.PositionTypeNotFoundException;
import util.exception.CreateNewPositionTypeException;
import util.exception.DeletePositionTypeException;
import util.exception.InputDataValidationException;
import util.exception.UpdatePositionTypeException;

@Stateless
@Local(PositionTypeEntitySessionBeanLocal.class)

public class PositionTypeEntitySessionBean implements PositionTypeEntitySessionBeanLocal 
{
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager entityManager;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public PositionTypeEntitySessionBean() 
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public PositionTypeEntity createNewPositionTypeEntity(PositionTypeEntity newPositionTypeEntity, Long parentPositionTypeId) throws InputDataValidationException, CreateNewPositionTypeException 
    {
        Set<ConstraintViolation<PositionTypeEntity>> constraintViolations = validator.validate(newPositionTypeEntity);

        if (constraintViolations.isEmpty()) 
        {
            try 
            {
                if (parentPositionTypeId != null) 
                {
                    PositionTypeEntity parentPositionTypeEntity = retrievePositionTypeByPositionTypeId(parentPositionTypeId);

                    if (!parentPositionTypeEntity.getJobEntities().isEmpty()) 
                    {
                        throw new CreateNewPositionTypeException("Parent positionType cannot be associated with any job");
                    }

                    newPositionTypeEntity.setParentPositionTypeEntity(parentPositionTypeEntity);
                }

                entityManager.persist(newPositionTypeEntity);
                entityManager.flush();

                return newPositionTypeEntity;
            } 
            catch (PersistenceException ex) 
            {
                if (ex.getCause() != null
                        && ex.getCause().getCause() != null
                        && ex.getCause().getCause().getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException")) 
                {
                    throw new CreateNewPositionTypeException("PositionType with same name already exist");
                } 
                else 
                {
                    throw new CreateNewPositionTypeException("An unexpected error has occurred: " + ex.getMessage());
                }
            } 
            catch (Exception ex) 
            {
                throw new CreateNewPositionTypeException("An unexpected error has occurred: " + ex.getMessage());
            }
        } 
        else 
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }

    @Override
    public List<PositionTypeEntity> retrieveAllPositionTypes() 
    {
        Query query = entityManager.createQuery("SELECT pt FROM PositionTypeEntity pt ORDER BY pt.name ASC");
        List<PositionTypeEntity> positionTypeEntities = query.getResultList();

        for (PositionTypeEntity positionTypeEntity : positionTypeEntities) 
        {
            positionTypeEntity.getParentPositionTypeEntity();
            positionTypeEntity.getSubPositionTypeEntities().size();
            positionTypeEntity.getJobEntities().size();
        }

        return positionTypeEntities;
    }

    @Override
    public List<PositionTypeEntity> retrieveAllRootPositionTypes() 
    {
        Query query = entityManager.createQuery("SELECT pt FROM PositionTypeEntity pt WHERE pt.parentPositionTypeEntity IS NULL ORDER BY pt.name ASC");
        List<PositionTypeEntity> rootPositionTypeEntities = query.getResultList();

        for (PositionTypeEntity rootPositionTypeEntity : rootPositionTypeEntities) 
        {
            lazilyLoadSubPositionTypes(rootPositionTypeEntity);

            rootPositionTypeEntity.getJobEntities().size();
        }

        return rootPositionTypeEntities;
    }

    @Override
    public List<PositionTypeEntity> retrieveAllLeafPositionTypes() 
    {
        Query query = entityManager.createQuery("SELECT pt FROM PositionTypeEntity pt WHERE pt.subPositionTypeEntities IS EMPTY ORDER BY pt.name ASC");
        List<PositionTypeEntity> leafPositionTypeEntities = query.getResultList();

        for (PositionTypeEntity leafPositionTypeEntity : leafPositionTypeEntities) 
        {
            leafPositionTypeEntity.getJobEntities().size();
        }

        return leafPositionTypeEntities;
    }

    @Override
    public List<PositionTypeEntity> retrieveAllPositionTypesWithoutJob() 
    {
        Query query = entityManager.createQuery("SELECT pt FROM PositionTypeEntity pt WHERE pt.jobEntities IS EMPTY ORDER BY pt.name ASC");
        List<PositionTypeEntity> rootPositionTypeEntities = query.getResultList();

        for (PositionTypeEntity rootPositionTypeEntity : rootPositionTypeEntities) 
        {
            rootPositionTypeEntity.getParentPositionTypeEntity();
        }

        return rootPositionTypeEntities;
    }

    @Override
    public PositionTypeEntity retrievePositionTypeByPositionTypeId(Long positionTypeId) throws PositionTypeNotFoundException 
    {
        PositionTypeEntity positionTypeEntity = entityManager.find(PositionTypeEntity.class, positionTypeId);

        if (positionTypeEntity != null) 
        {
            return positionTypeEntity;
        } 
        else 
        {
            throw new PositionTypeNotFoundException("PositionType ID " + positionTypeId + " does not exist!");
        }
    }

    @Override
    public void updatePositionType(PositionTypeEntity positionTypeEntity, Long parentPositionTypeId) throws InputDataValidationException, PositionTypeNotFoundException, UpdatePositionTypeException 
    {
        Set<ConstraintViolation<PositionTypeEntity>> constraintViolations = validator.validate(positionTypeEntity);

        if (constraintViolations.isEmpty()) 
        {
            if (positionTypeEntity.getPositionTypeId() != null) 
            {
                PositionTypeEntity positionTypeEntityToUpdate = retrievePositionTypeByPositionTypeId(positionTypeEntity.getPositionTypeId());

                Query query = entityManager.createQuery("SELECT pt FROM PositionTypeEntity pt WHERE pt.name = :inName AND pt.positionTypeId <> :inPositionTypeId");
                query.setParameter("inName", positionTypeEntity.getName());
                query.setParameter("inPositionTypeId", positionTypeEntity.getPositionTypeId());

                if (!query.getResultList().isEmpty()) 
                {
                    throw new UpdatePositionTypeException("The name of the positionType to be updated is duplicated");
                }

                positionTypeEntityToUpdate.setName(positionTypeEntity.getName());

                if (parentPositionTypeId != null) 
                {
                    if (positionTypeEntityToUpdate.getPositionTypeId().equals(parentPositionTypeId)) 
                    {
                        throw new UpdatePositionTypeException("PositionType cannot be its own parent");
                    } 
                    else if (positionTypeEntityToUpdate.getParentPositionTypeEntity() == null || (!positionTypeEntityToUpdate.getParentPositionTypeEntity().getPositionTypeId().equals(parentPositionTypeId))) 
                    {
                        PositionTypeEntity parentPositionTypeEntityToUpdate = retrievePositionTypeByPositionTypeId(parentPositionTypeId);

                        if (!parentPositionTypeEntityToUpdate.getJobEntities().isEmpty()) 
                        {
                            throw new UpdatePositionTypeException("Parent positionType cannot have any job associated with it");
                        }

                        positionTypeEntityToUpdate.setParentPositionTypeEntity(parentPositionTypeEntityToUpdate);
                    }
                } 
                else 
                {
                    positionTypeEntityToUpdate.setParentPositionTypeEntity(null);
                }
            } 
            else 
            {
                throw new PositionTypeNotFoundException("PositionType ID not provided for positionType to be updated");
            }
        } 
        else 
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }

    @Override
    public void deletePositionType(Long positionTypeId) throws PositionTypeNotFoundException, DeletePositionTypeException 
    {
        PositionTypeEntity positionTypeEntityToRemove = retrievePositionTypeByPositionTypeId(positionTypeId);

        if (!positionTypeEntityToRemove.getSubPositionTypeEntities().isEmpty()) 
        {
            throw new DeletePositionTypeException("PositionType ID " + positionTypeId + " is associated with existing sub-positionTypes and cannot be deleted!");
        } 
        else if (!positionTypeEntityToRemove.getJobEntities().isEmpty()) 
        {
            throw new DeletePositionTypeException("PositionType ID " + positionTypeId + " is associated with existing jobs and cannot be deleted!");
        } 
        else 
        {
            positionTypeEntityToRemove.setParentPositionTypeEntity(null);

            entityManager.remove(positionTypeEntityToRemove);
        }
    }

    private void lazilyLoadSubPositionTypes(PositionTypeEntity positionTypeEntity) 
    {
        for (PositionTypeEntity pte : positionTypeEntity.getSubPositionTypeEntities()) 
        {
            lazilyLoadSubPositionTypes(pte);
        }
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<PositionTypeEntity>> constraintViolations) 
    {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) 
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }
}
