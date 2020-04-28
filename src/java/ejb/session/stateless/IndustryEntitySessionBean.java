package ejb.session.stateless;

import entity.IndustryEntity;
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
import util.exception.IndustryNotFoundException;
import util.exception.CreateNewIndustryException;
import util.exception.DeleteIndustryException;
import util.exception.InputDataValidationException;
import util.exception.UpdateIndustryException;

@Stateless
@Local(IndustryEntitySessionBeanLocal.class)

public class IndustryEntitySessionBean implements IndustryEntitySessionBeanLocal 
{
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager entityManager;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public IndustryEntitySessionBean() 
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public IndustryEntity createNewIndustryEntity(IndustryEntity newIndustryEntity, Long parentIndustryId) throws InputDataValidationException, CreateNewIndustryException 
    {
        Set<ConstraintViolation<IndustryEntity>> constraintViolations = validator.validate(newIndustryEntity);

        if (constraintViolations.isEmpty()) 
        {
            try 
            {
                if (parentIndustryId != null) 
                {
                    IndustryEntity parentIndustryEntity = retrieveIndustryByIndustryId(parentIndustryId);

                    if (!parentIndustryEntity.getJobEntities().isEmpty()) 
                    {
                        throw new CreateNewIndustryException("Parent industry cannot be associated with any job");
                    }

                    newIndustryEntity.setParentIndustryEntity(parentIndustryEntity);
                }

                entityManager.persist(newIndustryEntity);
                entityManager.flush();

                return newIndustryEntity;
            } 
            catch (PersistenceException ex) 
            {
                if (ex.getCause() != null
                        && ex.getCause().getCause() != null
                        && ex.getCause().getCause().getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException")) 
                {
                    throw new CreateNewIndustryException("Industry with same name already exist");
                } 
                else 
                {
                    throw new CreateNewIndustryException("An unexpected error has occurred: " + ex.getMessage());
                }
            } 
            catch (Exception ex) 
            {
                throw new CreateNewIndustryException("An unexpected error has occurred: " + ex.getMessage());
            }
        } 
        else 
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }

    @Override
    public List<IndustryEntity> retrieveAllIndustries() 
    {
        Query query = entityManager.createQuery("SELECT i FROM IndustryEntity i ORDER BY i.name ASC");
        List<IndustryEntity> industryEntities = query.getResultList();

        for (IndustryEntity industryEntity : industryEntities) 
        {
            industryEntity.getParentIndustryEntity();
            industryEntity.getSubIndustryEntities().size();
            industryEntity.getJobEntities().size();
        }

        return industryEntities;
    }

    @Override
    public List<IndustryEntity> retrieveAllRootIndustries() 
    {
        Query query = entityManager.createQuery("SELECT i FROM IndustryEntity i WHERE i.parentIndustryEntity IS NULL ORDER BY i.name ASC");
        List<IndustryEntity> rootIndustryEntities = query.getResultList();

        for (IndustryEntity rootIndustryEntity : rootIndustryEntities) 
        {
            lazilyLoadSubIndustries(rootIndustryEntity);

            rootIndustryEntity.getJobEntities().size();
        }

        return rootIndustryEntities;
    }

    @Override
    public List<IndustryEntity> retrieveAllLeafIndustries() 
    {
        Query query = entityManager.createQuery("SELECT i FROM IndustryEntity i WHERE i.subIndustryEntities IS EMPTY ORDER BY i.name ASC");
        List<IndustryEntity> leafIndustryEntities = query.getResultList();

        for (IndustryEntity leafIndustryEntity : leafIndustryEntities) 
        {
            leafIndustryEntity.getJobEntities().size();
        }

        return leafIndustryEntities;
    }

    @Override
    public List<IndustryEntity> retrieveAllIndustriesWithoutJob() 
    {
        Query query = entityManager.createQuery("SELECT i FROM IndustryEntity i WHERE i.jobEntities IS EMPTY ORDER BY i.name ASC");
        List<IndustryEntity> rootIndustryEntities = query.getResultList();

        for (IndustryEntity rootIndustryEntity : rootIndustryEntities) 
        {
            rootIndustryEntity.getParentIndustryEntity();
        }

        return rootIndustryEntities;
    }

    @Override
    public IndustryEntity retrieveIndustryByIndustryId(Long industryId) throws IndustryNotFoundException 
    {
        IndustryEntity industryEntity = entityManager.find(IndustryEntity.class, industryId);

        if (industryEntity != null) 
        {
            return industryEntity;
        } 
        else 
        {
            throw new IndustryNotFoundException("Industry ID " + industryId + " does not exist!");
        }
    }

    @Override
    public void updateIndustry(IndustryEntity industryEntity, Long parentIndustryId) throws InputDataValidationException, IndustryNotFoundException, UpdateIndustryException 
    {
        Set<ConstraintViolation<IndustryEntity>> constraintViolations = validator.validate(industryEntity);

        if (constraintViolations.isEmpty()) 
        {
            if (industryEntity.getIndustryId() != null) 
            {
                IndustryEntity industryEntityToUpdate = retrieveIndustryByIndustryId(industryEntity.getIndustryId());

                Query query = entityManager.createQuery("SELECT i FROM IndustryEntity i WHERE i.name = :inName AND i.industryId <> :inIndustryId");
                query.setParameter("inName", industryEntity.getName());
                query.setParameter("inIndustryId", industryEntity.getIndustryId());

                if (!query.getResultList().isEmpty()) 
                {
                    throw new UpdateIndustryException("The name of the industry to be updated is duplicated");
                }

                industryEntityToUpdate.setName(industryEntity.getName());

                if (parentIndustryId != null) 
                {
                    if (industryEntityToUpdate.getIndustryId().equals(parentIndustryId)) 
                    {
                        throw new UpdateIndustryException("Industry cannot be its own parent");
                    } 
                    else if (industryEntityToUpdate.getParentIndustryEntity() == null || (!industryEntityToUpdate.getParentIndustryEntity().getIndustryId().equals(parentIndustryId))) 
                    {
                        IndustryEntity parentIndustryEntityToUpdate = retrieveIndustryByIndustryId(parentIndustryId);

                        if (!parentIndustryEntityToUpdate.getJobEntities().isEmpty()) 
                        {
                            throw new UpdateIndustryException("Parent industry cannot have any job associated with it");
                        }

                        industryEntityToUpdate.setParentIndustryEntity(parentIndustryEntityToUpdate);
                    }
                } 
                else 
                {
                    industryEntityToUpdate.setParentIndustryEntity(null);
                }
            } 
            else 
            {
                throw new IndustryNotFoundException("Industry ID not provided for industry to be updated");
            }
        } 
        else 
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }

    @Override
    public void deleteIndustry(Long industryId) throws IndustryNotFoundException, DeleteIndustryException 
    {
        IndustryEntity industryEntityToRemove = retrieveIndustryByIndustryId(industryId);

        if (!industryEntityToRemove.getSubIndustryEntities().isEmpty()) 
        {
            throw new DeleteIndustryException("Industry ID " + industryId + " is associated with existing sub-industries and cannot be deleted!");
        } 
        else if (!industryEntityToRemove.getJobEntities().isEmpty()) 
        {
            throw new DeleteIndustryException("Industry ID " + industryId + " is associated with existing jobs and cannot be deleted!");
        } 
        else 
        {
            industryEntityToRemove.setParentIndustryEntity(null);

            entityManager.remove(industryEntityToRemove);
        }
    }

    private void lazilyLoadSubIndustries(IndustryEntity industryEntity) 
    {
        for (IndustryEntity ie : industryEntity.getSubIndustryEntities()) 
        {
            lazilyLoadSubIndustries(ie);
        }
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<IndustryEntity>> constraintViolations) 
    {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) 
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }
}
