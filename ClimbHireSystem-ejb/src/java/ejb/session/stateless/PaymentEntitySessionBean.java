/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import entity.CompanyEntity;
import entity.PaymentEntity;
import java.util.List;
import java.util.Set;
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
import util.exception.CompanyNotFoundException;
import util.exception.CreateNewPaymentRecordException;
import util.exception.InputDataValidationException;
import util.exception.PaymentCompanyExistException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Casse
 */
@Stateless
public class PaymentEntitySessionBean implements PaymentEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    @EJB
    private CompanyEntitySessionBeanLocal companyEntitySessionBeanLocal;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    public PaymentEntitySessionBean()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public PaymentEntity createNewPayment(PaymentEntity newPaymentRecord, Long companyId) throws PaymentCompanyExistException, UnknownPersistenceException, InputDataValidationException, CreateNewPaymentRecordException
    {
        Set<ConstraintViolation<PaymentEntity>>constraintViolations = validator.validate(newPaymentRecord);
        
        if(constraintViolations.isEmpty())
        {  
            try
            {
                if(companyId == null)
                {
                    throw new CreateNewPaymentRecordException("The new payment record must be associated with a company");
                }
                
                CompanyEntity companyEntity = companyEntitySessionBeanLocal.retrieveCompanyByCompanyId(companyId);
                
                em.persist(newPaymentRecord);
                newPaymentRecord.setCompany(companyEntity);
                
                em.flush();

                return newPaymentRecord;
            }
            catch(PersistenceException ex)
            {
                throw new UnknownPersistenceException(ex.getMessage());
            }
            catch(CompanyNotFoundException ex)
            {
                throw new CreateNewPaymentRecordException("An error has occurred while creating the new payment record: " + ex.getMessage());
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public List<PaymentEntity> retrieveAllPaymentRecords()
    {
        Query query = em.createQuery("SELECT p FROM PaymentEntity p");
        
        return query.getResultList();
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<PaymentEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
}
