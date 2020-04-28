package ejb.session.stateless;

import entity.PositionTypeEntity;
import entity.IndustryEntity;
import entity.JobFunctionEntity;
import entity.JobEntity;
import entity.TagEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext; 
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.PositionTypeNotFoundException;
import util.exception.IndustryNotFoundException;
import util.exception.JobFunctionNotFoundException;
import util.exception.CreateNewJobException;
import util.exception.DeleteJobException;
import util.exception.InputDataValidationException;
import util.exception.JobNotFoundException;
import util.exception.JobCompanyNameExistException;
import util.exception.TagNotFoundException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateJobException;

@Stateless
@Local(JobEntitySessionBeanLocal.class)

public class JobEntitySessionBean implements JobEntitySessionBeanLocal 
{
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager entityManager;

    @EJB
    private PositionTypeEntitySessionBeanLocal positionTypeEntitySessionBeanLocal;
    @EJB
    private IndustryEntitySessionBeanLocal industryEntitySessionBeanLocal;
    @EJB
    private JobFunctionEntitySessionBeanLocal jobFunctionEntitySessionBeanLocal;
    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public JobEntitySessionBean() 
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public JobEntity createNewJob(JobEntity newJobEntity, Long positionTypeId, Long industryId, Long jobFunctionId, List<Long> tagIds) throws JobCompanyNameExistException, UnknownPersistenceException, InputDataValidationException, CreateNewJobException 
    {
        Set<ConstraintViolation<JobEntity>> constraintViolations = validator.validate(newJobEntity);

        if (constraintViolations.isEmpty()) 
        {
            try 
            {
                if (positionTypeId == null) 
                {
                    throw new CreateNewJobException("The new job must be associated a leaf position type");
                }

                PositionTypeEntity positionTypeEntity = positionTypeEntitySessionBeanLocal.retrievePositionTypeByPositionTypeId(positionTypeId);

                if (!positionTypeEntity.getSubPositionTypeEntities().isEmpty()) 
                {
                    throw new CreateNewJobException("Selected position type for the new job is not a leaf position type");
                }
                
                entityManager.persist(newJobEntity);
                newJobEntity.setPositionTypeEntity(positionTypeEntity);
                
                if (industryId == null) 
                {
                    throw new CreateNewJobException("The new job must be associated a leaf industry");
                }

                IndustryEntity industryEntity = industryEntitySessionBeanLocal.retrieveIndustryByIndustryId(industryId);

                if (!industryEntity.getSubIndustryEntities().isEmpty()) 
                {
                    throw new CreateNewJobException("Selected industry for the new job is not a leaf industry");
                }
                
                newJobEntity.setIndustryEntity(industryEntity);
                
                if (jobFunctionId == null) 
                {
                    throw new CreateNewJobException("The new job must be associated a leaf job function");
                }

                JobFunctionEntity jobFunctionEntity = jobFunctionEntitySessionBeanLocal.retrieveJobFunctionByJobFunctionId(jobFunctionId);

                if (!jobFunctionEntity.getSubJobFunctionEntities().isEmpty()) 
                {
                    throw new CreateNewJobException("Selected job function for the new job is not a leaf job function");
                }

                newJobEntity.setJobFunctionEntity(jobFunctionEntity);

                if (tagIds != null && (!tagIds.isEmpty())) 
                {
                    for (Long tagId : tagIds) 
                    {
                        TagEntity tagEntity = tagEntitySessionBeanLocal.retrieveTagByTagId(tagId);
                        newJobEntity.addTag(tagEntity);
                    }
                }

                entityManager.flush();

                return newJobEntity;
            } 
            catch (PersistenceException ex) 
            {
                if (ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException")) 
                {
                    if (ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) 
                    {
                        throw new JobCompanyNameExistException();
                    } 
                    else 
                    {
                        throw new UnknownPersistenceException(ex.getMessage());
                    }
                } 
                else 
                {
                    throw new UnknownPersistenceException(ex.getMessage());
                }
            } 
            catch (PositionTypeNotFoundException | IndustryNotFoundException | JobFunctionNotFoundException | TagNotFoundException ex) 
            {
                throw new CreateNewJobException("An error has occurred while creating the new job: " + ex.getMessage());
            }
        } 
        else 
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }

    @Override
    public List<JobEntity> retrieveAllJobs() 
    {
        Query query = entityManager.createQuery("SELECT j FROM JobEntity j ORDER BY j.companyName ASC");
        List<JobEntity> jobEntities = query.getResultList();

        for (JobEntity jobEntity : jobEntities) 
        {
            jobEntity.getPositionTypeEntity();
            jobEntity.getIndustryEntity();
            jobEntity.getJobFunctionEntity();
            jobEntity.getTagEntities().size();
        }

        return jobEntities;
    }

    @Override
    public List<JobEntity> searchJobsByCompanyName(String searchString) 
    {
        Query query = entityManager.createQuery("SELECT j FROM JobEntity j WHERE j.companyName LIKE :inSearchString ORDER BY j.companyName ASC");
        query.setParameter("inSearchString", "%" + searchString + "%");
        List<JobEntity> jobEntities = query.getResultList();

        for (JobEntity jobEntity : jobEntities) 
        {
            jobEntity.getPositionTypeEntity();
            jobEntity.getIndustryEntity();
            jobEntity.getJobFunctionEntity();
            jobEntity.getTagEntities().size();
        }

        return jobEntities;
    }

    @Override
    public List<JobEntity> filterJobsByPositionType(Long positionTypeId) throws PositionTypeNotFoundException 
    {
        List<JobEntity> jobEntities = new ArrayList<>();
        PositionTypeEntity positionTypeEntity = positionTypeEntitySessionBeanLocal.retrievePositionTypeByPositionTypeId(positionTypeId);

        if (positionTypeEntity.getSubPositionTypeEntities().isEmpty()) 
        {
            jobEntities = positionTypeEntity.getJobEntities();
        } 
        else 
        {
            for (PositionTypeEntity subPositionTypeEntity : positionTypeEntity.getSubPositionTypeEntities()) 
            {
                jobEntities.addAll(addSubPositionTypeJobs(subPositionTypeEntity));
            }
        }

        for (JobEntity jobEntity : jobEntities) 
        {
            jobEntity.getPositionTypeEntity();
            jobEntity.getTagEntities().size();
        }

        Collections.sort(jobEntities, new Comparator<JobEntity>() 
        {
            public int compare(JobEntity je1, JobEntity je2) 
            {
                return je1.getCompanyName().compareTo(je2.getCompanyName());
            }
        });

        return jobEntities;
    }

    @Override
    public List<JobEntity> filterJobsByIndustry(Long industryId) throws IndustryNotFoundException 
    {
        List<JobEntity> jobEntities = new ArrayList<>();
        IndustryEntity industryEntity = industryEntitySessionBeanLocal.retrieveIndustryByIndustryId(industryId);

        if (industryEntity.getSubIndustryEntities().isEmpty()) 
        {
            jobEntities = industryEntity.getJobEntities();
        } 
        else 
        {
            for (IndustryEntity subIndustryEntity : industryEntity.getSubIndustryEntities()) 
            {
                jobEntities.addAll(addSubIndustryJobs(subIndustryEntity));
            }
        }

        for (JobEntity jobEntity : jobEntities) 
        {
            jobEntity.getIndustryEntity();
            jobEntity.getTagEntities().size();
        }

        Collections.sort(jobEntities, new Comparator<JobEntity>() 
        {
            public int compare(JobEntity je1, JobEntity je2) 
            {
                return je1.getCompanyName().compareTo(je2.getCompanyName());
            }
        });

        return jobEntities;
    }
    
    @Override
    public List<JobEntity> filterJobsByJobFunction(Long jobFunctionId) throws JobFunctionNotFoundException 
    {
        List<JobEntity> jobEntities = new ArrayList<>();
        JobFunctionEntity jobFunctionEntity = jobFunctionEntitySessionBeanLocal.retrieveJobFunctionByJobFunctionId(jobFunctionId);

        if (jobFunctionEntity.getSubJobFunctionEntities().isEmpty()) 
        {
            jobEntities = jobFunctionEntity.getJobEntities();
        } 
        else 
        {
            for (JobFunctionEntity subJobFunctionEntity : jobFunctionEntity.getSubJobFunctionEntities()) 
            {
                jobEntities.addAll(addSubJobFunctionJobs(subJobFunctionEntity));
            }
        }

        for (JobEntity jobEntity : jobEntities) 
        {
            jobEntity.getJobFunctionEntity();
            jobEntity.getTagEntities().size();
        }

        Collections.sort(jobEntities, new Comparator<JobEntity>() 
        {
            public int compare(JobEntity je1, JobEntity je2) 
            {
                return je1.getCompanyName().compareTo(je2.getCompanyName());
            }
        });

        return jobEntities;
    }

    @Override
    public List<JobEntity> filterJobsByTags(List<Long> tagIds, String condition) 
    {
        List<JobEntity> jobEntities = new ArrayList<>();

        if (tagIds == null || tagIds.isEmpty() || (!condition.equals("AND") && !condition.equals("OR"))) 
        {
            return jobEntities;
        } 
        else 
        {
            if (condition.equals("OR")) 
            {
                Query query = entityManager.createQuery("SELECT DISTINCT je FROM JobEntity je, IN (je.tagEntities) te WHERE te.tagId IN :inTagIds ORDER BY je.companyName ASC");
                query.setParameter("inTagIds", tagIds);
                jobEntities = query.getResultList();
            } 
            else // AND
            {
                String selectClause = "SELECT je FROM JobEntity je";
                String whereClause = "";
                Boolean firstTag = true;
                Integer tagCount = 1;

                for (Long tagId : tagIds) 
                {
                    selectClause += ", IN (je.tagEntities) te" + tagCount;

                    if (firstTag) 
                    {
                        whereClause = "WHERE te1.tagId = " + tagId;
                        firstTag = false;
                    } 
                    else 
                    {
                        whereClause += " AND te" + tagCount + ".tagId = " + tagId;
                    }

                    tagCount++;
                }

                String jpql = selectClause + " " + whereClause + " ORDER BY je.companyName ASC";
                Query query = entityManager.createQuery(jpql);
                jobEntities = query.getResultList();
            }

            for (JobEntity jobEntity : jobEntities) 
            {
                jobEntity.getPositionTypeEntity();
                jobEntity.getIndustryEntity();
                jobEntity.getJobFunctionEntity();
                jobEntity.getTagEntities().size();
            }

            Collections.sort(jobEntities, new Comparator<JobEntity>() 
            {
                public int compare(JobEntity je1, JobEntity je2) 
                {
                    return je1.getCompanyName().compareTo(je2.getCompanyName());
                }
            });

            return jobEntities;
        }
    }

    @Override
    public JobEntity retrieveJobByJobId(Long jobId) throws JobNotFoundException 
    {
        JobEntity jobEntity = entityManager.find(JobEntity.class, jobId);

        if (jobEntity != null) 
        {
            jobEntity.getPositionTypeEntity();
            jobEntity.getIndustryEntity();
            jobEntity.getTagEntities().size();

            return jobEntity;
        } 
        else 
        {
            throw new JobNotFoundException("Job ID " + jobId + " does not exist!");
        }
    }

    @Override
    public JobEntity retrieveJobByJobCompanyName(String companyName) throws JobNotFoundException 
    {
        Query query = entityManager.createQuery("SELECT j FROM JobEntity j WHERE j.companyName = :inCompanyName");
        query.setParameter("inCompanyName", companyName);

        try 
        {
            JobEntity jobEntity = (JobEntity) query.getSingleResult();
            jobEntity.getPositionTypeEntity();
            jobEntity.getIndustryEntity();
            jobEntity.getJobFunctionEntity();
            jobEntity.getTagEntities().size();

            return jobEntity;
        } 
        catch (NoResultException | NonUniqueResultException ex) 
        {
            throw new JobNotFoundException("Company Name " + companyName + " does not exist!");
        }
    }

    @Override
    public void updateJob(JobEntity jobEntity, Long positionTypeId, Long industryId, Long jobFunctionId, List<Long> tagIds) throws JobNotFoundException, PositionTypeNotFoundException, IndustryNotFoundException, JobFunctionNotFoundException, TagNotFoundException, UpdateJobException, InputDataValidationException 
    {
        if (jobEntity != null && jobEntity.getJobId() != null) 
        {
            Set<ConstraintViolation<JobEntity>> constraintViolations = validator.validate(jobEntity);

            if (constraintViolations.isEmpty()) 
            {
                JobEntity jobEntityToUpdate = retrieveJobByJobId(jobEntity.getJobId());

                if (jobEntityToUpdate.getCompanyName().equals(jobEntity.getCompanyName())) 
                {
                    if (positionTypeId != null && (!jobEntityToUpdate.getPositionTypeEntity().getPositionTypeId().equals(positionTypeId))) 
                    {
                        PositionTypeEntity positionTypeEntityToUpdate = positionTypeEntitySessionBeanLocal.retrievePositionTypeByPositionTypeId(positionTypeId);

                        if (!positionTypeEntityToUpdate.getSubPositionTypeEntities().isEmpty()) 
                        {
                            throw new UpdateJobException("Selected position type for the new job is not a leaf position type");
                        }

                        jobEntityToUpdate.setPositionTypeEntity(positionTypeEntityToUpdate);
                    }
                    
                    if (industryId != null && (!jobEntityToUpdate.getIndustryEntity().getIndustryId().equals(industryId))) 
                    {
                        IndustryEntity industryEntityToUpdate = industryEntitySessionBeanLocal.retrieveIndustryByIndustryId(industryId);

                        if (!industryEntityToUpdate.getSubIndustryEntities().isEmpty()) 
                        {
                            throw new UpdateJobException("Selected industry for the new job is not a leaf industry");
                        }

                        jobEntityToUpdate.setIndustryEntity(industryEntityToUpdate);
                    }
                    
                    if (jobFunctionId != null && (!jobEntityToUpdate.getJobFunctionEntity().getJobFunctionId().equals(jobFunctionId))) 
                    {
                        JobFunctionEntity jobFunctionEntityToUpdate = jobFunctionEntitySessionBeanLocal.retrieveJobFunctionByJobFunctionId(jobFunctionId);

                        if (!jobFunctionEntityToUpdate.getSubJobFunctionEntities().isEmpty()) 
                        {
                            throw new UpdateJobException("Selected jobFunction for the new job is not a leaf jobFunction");
                        }

                        jobEntityToUpdate.setJobFunctionEntity(jobFunctionEntityToUpdate);
                    }

                    if (tagIds != null) 
                    {
                        for (TagEntity tagEntity : jobEntityToUpdate.getTagEntities()) 
                        {
                            tagEntity.getJobEntities().remove(jobEntityToUpdate);
                        }

                        jobEntityToUpdate.getTagEntities().clear();

                        for (Long tagId : tagIds) 
                        {
                            TagEntity tagEntity = tagEntitySessionBeanLocal.retrieveTagByTagId(tagId);
                            jobEntityToUpdate.addTag(tagEntity);
                        }
                    }
                    //jobEntityToUpdate.setName(jobEntity.getName());
                    //jobEntityToUpdate.setDescription(jobEntity.getDescription());
                    //jobEntityToUpdate.setQuantityOnHand(jobEntity.getQuantityOnHand());
                    //jobEntityToUpdate.setReorderQuantity(jobEntity.getReorderQuantity());
                    //jobEntityToUpdate.setUnitPrice(jobEntity.getUnitPrice());
                    //jobEntityToUpdate.setJobRating((jobEntity.getJobRating()));
                } 
                else 
                {
                    throw new UpdateJobException("Company Name of job record to be updated does not match the existing record");
                }
            } 
            else 
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
            }
        } 
        else 
        {
            throw new JobNotFoundException("Job ID not provided for job to be updated");
        }
    }

    @Override
    public void deleteJob(Long jobId) throws JobNotFoundException, DeleteJobException 
    {
        JobEntity jobEntityToRemove = retrieveJobByJobId(jobId);

        jobEntityToRemove.getPositionTypeEntity().getJobEntities().remove(jobEntityToRemove);
        jobEntityToRemove.getIndustryEntity().getJobEntities().remove(jobEntityToRemove);
        jobEntityToRemove.getJobFunctionEntity().getJobEntities().remove(jobEntityToRemove);

        for (TagEntity tagEntity : jobEntityToRemove.getTagEntities()) 
        {
            tagEntity.getJobEntities().remove(jobEntityToRemove);
        }

        jobEntityToRemove.getTagEntities().clear();

        entityManager.remove(jobEntityToRemove);
    }

    private List<JobEntity> addSubPositionTypeJobs(PositionTypeEntity positionTypeEntity) 
    {
        List<JobEntity> jobEntities = new ArrayList<>();

        if (positionTypeEntity.getSubPositionTypeEntities().isEmpty()) 
        {
            return positionTypeEntity.getJobEntities();
        } 
        else 
        {
            for (PositionTypeEntity subPositionTypeEntity : positionTypeEntity.getSubPositionTypeEntities()) 
            {
                jobEntities.addAll(addSubPositionTypeJobs(subPositionTypeEntity));
            }

            return jobEntities;
        }
    }

    private List<JobEntity> addSubIndustryJobs(IndustryEntity industryEntity) 
    {
        List<JobEntity> jobEntities = new ArrayList<>();

        if (industryEntity.getSubIndustryEntities().isEmpty()) 
        {
            return industryEntity.getJobEntities();
        } 
        else 
        {
            for (IndustryEntity subIndustryEntity : industryEntity.getSubIndustryEntities()) 
            {
                jobEntities.addAll(addSubIndustryJobs(subIndustryEntity));
            }

            return jobEntities;
        }
    }

    private List<JobEntity> addSubJobFunctionJobs(JobFunctionEntity jobFunctionEntity) 
    {
        List<JobEntity> jobEntities = new ArrayList<>();

        if (jobFunctionEntity.getSubJobFunctionEntities().isEmpty()) 
        {
            return jobFunctionEntity.getJobEntities();
        } 
        else 
        {
            for (JobFunctionEntity subJobFunctionEntity : jobFunctionEntity.getSubJobFunctionEntities()) 
            {
                jobEntities.addAll(addSubJobFunctionJobs(subJobFunctionEntity));
            }

            return jobEntities;
        }
    }
        
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<JobEntity>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }
}
