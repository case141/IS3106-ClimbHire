/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CompanyEntity;
import entity.SubscriptionEntity;
import java.util.List;
import java.util.Set;
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
import util.exception.CompanyNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.SubscriptionCompanyExistException;
import util.exception.SubscriptionNotFoundException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateCompanyException;

/**
 *
 * @author Casse
 */
@Stateless
public class SubscriptionEntitySessionBean implements SubscriptionEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    public SubscriptionEntitySessionBean()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public SubscriptionEntity createNewSubscription(SubscriptionEntity newSubscription) throws SubscriptionCompanyExistException, UnknownPersistenceException, InputDataValidationException
    {
        Set<ConstraintViolation<SubscriptionEntity>>constraintViolations = validator.validate(newSubscription);
        
        if(constraintViolations.isEmpty())
        {  
            try
            {
                em.persist(newSubscription);
                em.flush();

                return newSubscription;
            }
            catch(PersistenceException ex)
            {
                if(ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException"))
                {
                    if(ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException"))
                    {
                        throw new SubscriptionCompanyExistException();
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
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public List<SubscriptionEntity> retrieveAllSubscription()
    {
        Query query = em.createQuery("SELECT s FROM SubscriptionEntity s");
        
        return query.getResultList();
    }
    
    @Override
    public SubscriptionEntity retrieveSubscriptionByCompany(CompanyEntity company) throws CompanyNotFoundException
    {
        Query query = em.createQuery("SELECT s FROM SubscriptionEntity s WHERE s.company = :inCompany");
        query.setParameter("inCompany", company);
        
        try
        {
            return (SubscriptionEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new CompanyNotFoundException("Subscription for Company " + company + " does not exist!");
        }
    }
    
    @Override
    public void switchSubscriptionPlan(SubscriptionEntity subscription) throws InputDataValidationException, SubscriptionNotFoundException, CompanyNotFoundException
    {
        if(subscription != null && subscription.getSubscriptionId()!= null)
        {
            Set<ConstraintViolation<SubscriptionEntity>>constraintViolations = validator.validate(subscription);
        
            if(constraintViolations.isEmpty())
            {
                SubscriptionEntity subscriptionEntityToUpdate = retrieveSubscriptionByCompany(subscription.getCompany());

                // upgrade or downgrade subscription through this method
                subscriptionEntityToUpdate.setAmount(subscription.getAmount());
                subscriptionEntityToUpdate.setDescription(subscription.getDescription());
                subscriptionEntityToUpdate.setSubscriptionType(subscription.getSubscriptionType());      
                // cannot change subscription status or renewal dates through this method, company will never change 
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
            }
        }
        else
        {
            throw new SubscriptionNotFoundException("Subscription Company not provided for subscription to be updated");
        }
    }
    
    @Override
    public void terminateSubscriptionPlan(SubscriptionEntity subscription) throws SubscriptionNotFoundException, InputDataValidationException, CompanyNotFoundException
    {
        if(subscription != null && subscription.getCompany()!= null)
        {
            Set<ConstraintViolation<SubscriptionEntity>>constraintViolations = validator.validate(subscription);
        
            if(constraintViolations.isEmpty())
            {
                SubscriptionEntity subscriptionToTerminate = retrieveSubscriptionByCompany(subscription.getCompany());

                subscriptionToTerminate.setRenewalDate(subscription.getRenewalDate()); //set to NULL
                subscriptionToTerminate.setStatus(subscription.getStatus()); //set to INACTIVE
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
            }
        }
        else
        {
            throw new SubscriptionNotFoundException("Subscription Company not provided for subscription to be updated");
        }
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<SubscriptionEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
}
