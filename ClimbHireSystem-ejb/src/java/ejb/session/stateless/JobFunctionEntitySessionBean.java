package ejb.session.stateless;

import entity.JobFunctionEntity;
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
import util.exception.JobFunctionNotFoundException;
import util.exception.CreateNewJobFunctionException;
import util.exception.DeleteJobFunctionException;
import util.exception.InputDataValidationException;
import util.exception.UpdateJobFunctionException;

@Stateless
@Local(JobFunctionEntitySessionBeanLocal.class)

public class JobFunctionEntitySessionBean implements JobFunctionEntitySessionBeanLocal 
{
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager entityManager;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public JobFunctionEntitySessionBean() 
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public JobFunctionEntity createNewJobFunctionEntity(JobFunctionEntity newJobFunctionEntity, Long parentJobFunctionId) throws InputDataValidationException, CreateNewJobFunctionException 
    {
        Set<ConstraintViolation<JobFunctionEntity>> constraintViolations = validator.validate(newJobFunctionEntity);

        if (constraintViolations.isEmpty()) 
        {
            try 
            {
                if (parentJobFunctionId != null) 
                {
                    JobFunctionEntity parentJobFunctionEntity = retrieveJobFunctionByJobFunctionId(parentJobFunctionId);

                    if (!parentJobFunctionEntity.getJobEntities().isEmpty()) 
                    {
                        throw new CreateNewJobFunctionException("Parent jobFunction cannot be associated with any job");
                    }

                    newJobFunctionEntity.setParentJobFunctionEntity(parentJobFunctionEntity);
                }

                entityManager.persist(newJobFunctionEntity);
                entityManager.flush();

                return newJobFunctionEntity;
            } 
            catch (PersistenceException ex) 
            {
                if (ex.getCause() != null
                        && ex.getCause().getCause() != null
                        && ex.getCause().getCause().getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException")) 
                {
                    throw new CreateNewJobFunctionException("JobFunction with same name already exist");
                } 
                else 
                {
                    throw new CreateNewJobFunctionException("An unexpected error has occurred: " + ex.getMessage());
                }
            } 
            catch (Exception ex) 
            {
                throw new CreateNewJobFunctionException("An unexpected error has occurred: " + ex.getMessage());
            }
        } 
        else 
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }

    @Override
    public List<JobFunctionEntity> retrieveAllJobFunctions() 
    {
        Query query = entityManager.createQuery("SELECT jf FROM JobFunctionEntity jf ORDER BY jf.name ASC");
        List<JobFunctionEntity> jobFunctionEntities = query.getResultList();

        for (JobFunctionEntity jobFunctionEntity : jobFunctionEntities) 
        {
            jobFunctionEntity.getParentJobFunctionEntity();
            jobFunctionEntity.getSubJobFunctionEntities().size();
            jobFunctionEntity.getJobEntities().size();
        }

        return jobFunctionEntities;
    }

    @Override
    public List<JobFunctionEntity> retrieveAllRootJobFunctions() 
    {
        Query query = entityManager.createQuery("SELECT jf FROM JobFunctionEntity jf WHERE jf.parentJobFunctionEntity IS NULL ORDER BY jf.name ASC");
        List<JobFunctionEntity> rootJobFunctionEntities = query.getResultList();

        for (JobFunctionEntity rootJobFunctionEntity : rootJobFunctionEntities) 
        {
            lazilyLoadSubJobFunctions(rootJobFunctionEntity);

            rootJobFunctionEntity.getJobEntities().size();
        }

        return rootJobFunctionEntities;
    }

    @Override
    public List<JobFunctionEntity> retrieveAllLeafJobFunctions() 
    {
        Query query = entityManager.createQuery("SELECT jf FROM JobFunctionEntity jf WHERE jf.subJobFunctionEntities IS EMPTY ORDER BY jf.name ASC");
        List<JobFunctionEntity> leafJobFunctionEntities = query.getResultList();

        for (JobFunctionEntity leafJobFunctionEntity : leafJobFunctionEntities) 
        {
            leafJobFunctionEntity.getJobEntities().size();
        }

        return leafJobFunctionEntities;
    }

    @Override
    public List<JobFunctionEntity> retrieveAllJobFunctionsWithoutJob() 
    {
        Query query = entityManager.createQuery("SELECT jf FROM JobFunctionEntity jf WHERE jf.jobEntities IS EMPTY ORDER BY jf.name ASC");
        List<JobFunctionEntity> rootJobFunctionEntities = query.getResultList();

        for (JobFunctionEntity rootJobFunctionEntity : rootJobFunctionEntities) 
        {
            rootJobFunctionEntity.getParentJobFunctionEntity();
        }

        return rootJobFunctionEntities;
    }

    @Override
    public JobFunctionEntity retrieveJobFunctionByJobFunctionId(Long jobFunctionId) throws JobFunctionNotFoundException 
    {
        JobFunctionEntity jobFunctionEntity = entityManager.find(JobFunctionEntity.class, jobFunctionId);

        if (jobFunctionEntity != null) 
        {
            return jobFunctionEntity;
        } 
        else 
        {
            throw new JobFunctionNotFoundException("JobFunction ID " + jobFunctionId + " does not exist!");
        }
    }

    @Override
    public void updateJobFunction(JobFunctionEntity jobFunctionEntity, Long parentJobFunctionId) throws InputDataValidationException, JobFunctionNotFoundException, UpdateJobFunctionException 
    {
        Set<ConstraintViolation<JobFunctionEntity>> constraintViolations = validator.validate(jobFunctionEntity);

        if (constraintViolations.isEmpty()) 
        {
            if (jobFunctionEntity.getJobFunctionId() != null) 
            {
                JobFunctionEntity jobFunctionEntityToUpdate = retrieveJobFunctionByJobFunctionId(jobFunctionEntity.getJobFunctionId());

                Query query = entityManager.createQuery("SELECT jf FROM JobFunctionEntity jf WHERE jf.name = :inName AND jf.jobFunctionId <> :inJobFunctionId");
                query.setParameter("inName", jobFunctionEntity.getName());
                query.setParameter("inJobFunctionId", jobFunctionEntity.getJobFunctionId());

                if (!query.getResultList().isEmpty()) 
                {
                    throw new UpdateJobFunctionException("The name of the jobFunction to be updated is duplicated");
                }

                jobFunctionEntityToUpdate.setName(jobFunctionEntity.getName());

                if (parentJobFunctionId != null) 
                {
                    if (jobFunctionEntityToUpdate.getJobFunctionId().equals(parentJobFunctionId)) 
                    {
                        throw new UpdateJobFunctionException("JobFunction cannot be its own parent");
                    } 
                    else if (jobFunctionEntityToUpdate.getParentJobFunctionEntity() == null || (!jobFunctionEntityToUpdate.getParentJobFunctionEntity().getJobFunctionId().equals(parentJobFunctionId))) 
                    {
                        JobFunctionEntity parentJobFunctionEntityToUpdate = retrieveJobFunctionByJobFunctionId(parentJobFunctionId);

                        if (!parentJobFunctionEntityToUpdate.getJobEntities().isEmpty()) 
                        {
                            throw new UpdateJobFunctionException("Parent jobFunction cannot have any job associated with it");
                        }

                        jobFunctionEntityToUpdate.setParentJobFunctionEntity(parentJobFunctionEntityToUpdate);
                    }
                } 
                else 
                {
                    jobFunctionEntityToUpdate.setParentJobFunctionEntity(null);
                }
            } 
            else 
            {
                throw new JobFunctionNotFoundException("JobFunction ID not provided for jobFunction to be updated");
            }
        } 
        else 
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }

    @Override
    public void deleteJobFunction(Long jobFunctionId) throws JobFunctionNotFoundException, DeleteJobFunctionException 
    {
        JobFunctionEntity jobFunctionEntityToRemove = retrieveJobFunctionByJobFunctionId(jobFunctionId);

        if (!jobFunctionEntityToRemove.getSubJobFunctionEntities().isEmpty()) 
        {
            throw new DeleteJobFunctionException("JobFunction ID " + jobFunctionId + " is associated with existing sub-jobFunctions and cannot be deleted!");
        } 
        else if (!jobFunctionEntityToRemove.getJobEntities().isEmpty()) 
        {
            throw new DeleteJobFunctionException("JobFunction ID " + jobFunctionId + " is associated with existing jobs and cannot be deleted!");
        } 
        else 
        {
            jobFunctionEntityToRemove.setParentJobFunctionEntity(null);

            entityManager.remove(jobFunctionEntityToRemove);
        }
    }

    private void lazilyLoadSubJobFunctions(JobFunctionEntity jobFunctionEntity) 
    {
        for (JobFunctionEntity jfe : jobFunctionEntity.getSubJobFunctionEntities()) 
        {
            lazilyLoadSubJobFunctions(jfe);
        }
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<JobFunctionEntity>> constraintViolations) 
    {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) 
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }
}
