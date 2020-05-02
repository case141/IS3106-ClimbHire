/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CompanyEntity;
import entity.PaymentEntity;
import entity.SubscriptionEntity;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
import util.enumeration.SubscriptionStatusEnum;
import util.exception.CompanyEmailExistException;
import util.exception.CompanyNotFoundException;
import util.exception.CreateNewPaymentRecordException;
import util.exception.CreateNewSubscriptionException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.PaymentCompanyExistException;
import util.exception.SetCompanySubscriptionException;
import util.exception.SubscriptionCompanyExistException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateCompanyException;
import util.security.CryptographicHelper;

/**
 *
 * @author Casse
 */
@Stateless
public class CompanyEntitySessionBean implements CompanyEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private SubscriptionEntitySessionBeanLocal subscriptionEntitySessionBeanLocal;  
    @EJB
    private PaymentEntitySessionBeanLocal paymentEntitySessionBeanLocal;
    
    @EJB
    private CompanyEntitySessionBeanLocal companyEntitySessionBeanLocal;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public CompanyEntitySessionBean()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public CompanyEntity createNewCompany(CompanyEntity newCompany) throws 
            UnknownPersistenceException, InputDataValidationException, CompanyNotFoundException
    {
        Set<ConstraintViolation<CompanyEntity>>constraintViolations = validator.validate(newCompany);
        
        if(constraintViolations.isEmpty())
        {  
                                    
                em.persist(newCompany);      
                em.flush();

                return newCompany;
            
                
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }  
    }
    /*
    @Override
    public CompanyEntity createNewCompany(CompanyEntity newCompany, SubscriptionEntity newSubscription, PaymentEntity newPaymentRecord) throws 
            UnknownPersistenceException, InputDataValidationException, CreateNewSubscriptionException, SubscriptionCompanyExistException, CreateNewPaymentRecordException, PaymentCompanyExistException,
            CompanyNotFoundException, SetCompanySubscriptionException
    {
        Set<ConstraintViolation<CompanyEntity>>constraintViolations = validator.validate(newCompany);
        
        if(constraintViolations.isEmpty())
        {  
            try
            {                        
                em.persist(newCompany);      
                companyEntitySessionBeanLocal.setCompanySubscription(newCompany, subscriptionEntitySessionBeanLocal.createNewSubscription(newSubscription, newCompany.getCompanyId()));            
                newCompany.getPaymentHistory().add(paymentEntitySessionBeanLocal.createNewPayment(newPaymentRecord, newCompany.getCompanyId()));

                em.flush();

                return newCompany;
            }
            catch(CompanyNotFoundException ex)
            {
                throw new CompanyNotFoundException("The company does not exist!");
            }
            
            catch(CreateNewSubscriptionException ex)
            {
                throw new CreateNewSubscriptionException("The new subscription must be associated with a company");
            }      
            catch(SubscriptionCompanyExistException ex)
            {
                throw new SubscriptionCompanyExistException("The subscription already exists with a company");
            }
            catch(CreateNewPaymentRecordException ex)
            {
                throw new CreateNewPaymentRecordException("The new payment record must be associated with a company");
            }
            catch(PaymentCompanyExistException ex)
            {
                throw new SubscriptionCompanyExistException("The payment record already exists with a company");
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }  
    }
    */
    
    @Override
    public List<CompanyEntity> retrieveAllCompanies()
    {
        Query query = em.createQuery("SELECT c FROM CompanyEntity c");
        
        return query.getResultList();
    }
    
    @Override
    public CompanyEntity retrieveCompanyByEmail(String companyEmail) throws CompanyNotFoundException
    {
        Query query = em.createQuery("SELECT c FROM CompanyEntity c WHERE c.email = :inCompanyEmail");
        query.setParameter("inCompanyEmail", companyEmail);
        
        try
        {
            return (CompanyEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new CompanyNotFoundException("Company Email " + companyEmail + " does not exist!");
        }
    }
    
    @Override
    public CompanyEntity retrieveCompanyByCompanyId(Long companyId) throws CompanyNotFoundException
    {
        CompanyEntity companyEntity = em.find(CompanyEntity.class, companyId);
        
        if(companyEntity != null)
        {
            return companyEntity;
        }
        else
        {
            throw new CompanyNotFoundException("Company ID " + companyId + " does not exist!");
        }
    }
    
    @Override
    public CompanyEntity companyLogin(String companyEmail, String password) throws InvalidLoginCredentialException 
    {
        try
        {
            CompanyEntity companyEntity = retrieveCompanyByEmail(companyEmail);            
            String passwordHash = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + companyEntity.getSalt()));
            
            //if password matches and company is active
            if(companyEntity.getPassword().equals(passwordHash) && companyEntity.getSubscription().getStatus() == SubscriptionStatusEnum.ACTIVE)
            {             
                return companyEntity;
            }
            else
            {
                throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
            }
        }
        catch(CompanyNotFoundException ex)
        {
            throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
        }
    }
    
    public void updateCompanyProfile(CompanyEntity companyEntity) throws CompanyNotFoundException, UpdateCompanyException, InputDataValidationException
    {
        if(companyEntity != null && companyEntity.getCompanyId()!= null)
        {
            Set<ConstraintViolation<CompanyEntity>>constraintViolations = validator.validate(companyEntity);
        
            if(constraintViolations.isEmpty())
            {
                CompanyEntity companyEntityToUpdate = retrieveCompanyByCompanyId(companyEntity.getCompanyId());

                if(companyEntityToUpdate.getEmail().equals(companyEntity.getEmail()))
                {
                    companyEntityToUpdate.setCompanyName(companyEntity.getCompanyName());
                    companyEntityToUpdate.setContactNumber(companyEntity.getContactNumber());
                    companyEntityToUpdate.setCompanyBio(companyEntity.getCompanyBio());
                    companyEntityToUpdate.setDateOfFounding(companyEntity.getDateOfFounding());         
                    // company cannot update account credentials: email and password through this business method       
                }
                else
                {
                    throw new UpdateCompanyException("Email of company record to be updated does not match the existing record");
                }
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
            }
        }
        else
        {
            throw new CompanyNotFoundException("Company Id not provided for company to be updated");
        }
    }
    
    @Override
    public void setCompanySubscription(CompanyEntity companyEntity, SubscriptionEntity subscriptionEntity) throws CompanyNotFoundException, SetCompanySubscriptionException
    {
        if(companyEntity != null && companyEntity.getCompanyId()!= null)
        {
            CompanyEntity companyEntityToUpdate = retrieveCompanyByCompanyId(companyEntity.getCompanyId());

            if(subscriptionEntity.getCompany().equals(companyEntity))
            {
                companyEntityToUpdate.setSubscription(subscriptionEntity);           
            }
            else
            {
                throw new SetCompanySubscriptionException("Email of company record to be updated does not match the existing record");
            }
        }
        else
        {
            throw new CompanyNotFoundException("Company Id not provided for company to be updated");
        }
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<CompanyEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
}
