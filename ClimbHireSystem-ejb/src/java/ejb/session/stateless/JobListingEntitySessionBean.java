/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ApplicationEntity;
import entity.CompanyEntity;
import entity.JobListingEntity;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.enumeration.JobListingStatusEnum;
import util.exception.ApplicationNotFoundException;
import util.exception.CompanyNotFoundException;
import util.exception.CreateNewJobListingException;
import util.exception.InputDataValidationException;
import util.exception.JobListingExistException;
import util.exception.JobListingNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Casse
 */
@Stateless
public class JobListingEntitySessionBean implements JobListingEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    @EJB
    private CompanyEntitySessionBeanLocal companyEntitySessionBeanLocal;
    @EJB
    private ApplicationEntitySessionBeanLocal applicationEntitySessionBeanLocal;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    public JobListingEntitySessionBean()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public List<JobListingEntity> retrieveAllJobListings()
    {
        Query query = em.createQuery("SELECT j FROM JobListingEntity j");
        
        return query.getResultList();
    }
    
    @Override
    public JobListingEntity retrieveJobListingById(Long jobListingId) throws JobListingNotFoundException
    {
        JobListingEntity jobListingEntity = em.find(JobListingEntity.class, jobListingId);
        
        if(jobListingEntity != null)
        {
            return jobListingEntity;
        }
        else
        {
            throw new JobListingNotFoundException("Job Listing ID " + jobListingId + " does not exist!");
        }
    }
    
    @Override
    public JobListingEntity createNewJobListing(JobListingEntity newJobListing, Long companyId) throws UnknownPersistenceException, InputDataValidationException, CreateNewJobListingException, CompanyNotFoundException
    {
        
        Set<ConstraintViolation<JobListingEntity>>constraintViolations = validator.validate(newJobListing);
        
        if(constraintViolations.isEmpty())
        {  
            try
            {
                if(companyId == null)
                {
                    throw new CreateNewJobListingException("The new job listing must be associated a company");
                }
                
                CompanyEntity companyEntity = companyEntitySessionBeanLocal.retrieveCompanyByCompanyId(companyId);
                
                em.persist(newJobListing);
                newJobListing.setCompany(companyEntity);
                
                em.flush();

                return newJobListing;
            }
            catch(PersistenceException ex)
            {
                throw new UnknownPersistenceException(ex.getMessage());
            }
            catch(CompanyNotFoundException ex)
            {
                throw new CreateNewJobListingException("An error has occurred while creating the new job listing: " + ex.getMessage());
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public void updateJobListingDetails(JobListingEntity jobListingEntity, List<Long> applicationIds) throws InputDataValidationException, JobListingNotFoundException, ApplicationNotFoundException
    {
        if(jobListingEntity != null && jobListingEntity.getJobListingId()!= null)
        {
            Set<ConstraintViolation<JobListingEntity>>constraintViolations = validator.validate(jobListingEntity);
        
            if(constraintViolations.isEmpty())
            {
                JobListingEntity jobListingEntityToUpdate = retrieveJobListingById(jobListingEntity.getJobListingId());
                    
                if(applicationIds != null)
                {
                    for(Long applicationId: applicationIds)
                    {
                        ApplicationEntity applicationEntity = applicationEntitySessionBeanLocal.retrieveApplicationByApplicationId(applicationId);
                        jobListingEntityToUpdate.addApplication(applicationEntity);
                    }
                }
                jobListingEntity.getQualifications().forEach((qualification) -> {
                    jobListingEntityToUpdate.addQualification(qualification);
                });
                jobListingEntity.getSkillsRequired().forEach((skillRequired) -> {
                    jobListingEntityToUpdate.addSkillsRequired(skillRequired);
                });
                
                //A job listing's company cannot be changed
                jobListingEntityToUpdate.setJobTitle(jobListingEntity.getJobTitle());
                jobListingEntityToUpdate.setWorkLocation(jobListingEntity.getWorkLocation());
                jobListingEntityToUpdate.setBasicMonthlyPay(jobListingEntity.getBasicMonthlyPay());
                jobListingEntityToUpdate.setContract(jobListingEntity.getContract());
                jobListingEntityToUpdate.setPayPerHour(jobListingEntity.getPayPerHour());
                jobListingEntityToUpdate.setResponsibilities(jobListingEntity.getResponsibilities());
                jobListingEntityToUpdate.setJobListingStatusEnum(jobListingEntity.getJobListingStatusEnum());
                jobListingEntityToUpdate.setExpiryDate(jobListingEntity.getExpiryDate());
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
            }
        }
        else
        {
            throw new JobListingNotFoundException("Product ID not provided for product to be updated");
        }
    }
    
    @Override
    public void closeJobListing(Long jobListingId) throws JobListingNotFoundException
    {
        JobListingEntity jobListingToClose = em.find(JobListingEntity.class, jobListingId);
        
        if(jobListingToClose != null)
        {
            jobListingToClose.setJobListingStatusEnum(JobListingStatusEnum.CLOSED);
            
        }
        else
        {
            throw new JobListingNotFoundException("Job Listing ID " + jobListingId + " does not exist!");
        }
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<JobListingEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
    
}
