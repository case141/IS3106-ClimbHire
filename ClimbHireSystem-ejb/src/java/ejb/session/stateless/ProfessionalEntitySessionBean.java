/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import entity.CompanyEntity;
import entity.ProfessionalEntity;
import java.util.List;
import java.util.Set;
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
import util.exception.CompanyNotFoundException;
import util.exception.CreateNewProfessionalException;
import util.exception.InputDataValidationException;
import util.exception.ProfessionalNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Casse
 */
@Stateless
public class ProfessionalEntitySessionBean implements ProfessionalEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    
    @EJB
    private CompanyEntitySessionBeanLocal companyEntitySessionBeanLocal;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    
    public ProfessionalEntitySessionBean()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    
    @Override
    public List<ProfessionalEntity> retrieveAllProfessionals()
    {
        Query query = em.createQuery("SELECT p FROM ProfessionalEntity p");
        
        return query.getResultList();
    }
    
    
    @Override
    public ProfessionalEntity retrieveProfessionalByEmail(String professionalEmail) throws ProfessionalNotFoundException
    {
        Query query = em.createQuery("SELECT p FROM ProfessionalEntity p WHERE p.email = :inProfessionalEmail");
        query.setParameter("inProfessionalEmail", professionalEmail);
        
        try
        {
            return (ProfessionalEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new ProfessionalNotFoundException("Professional Email " + professionalEmail + " does not exist!");
        }
    }
    
    @Override
    public ProfessionalEntity retrieveProfessionalById(Long professionalId) throws ProfessionalNotFoundException
    {
        ProfessionalEntity professionalEntity = em.find(ProfessionalEntity.class, professionalId);
        
        if(professionalEntity != null)
        {
            professionalEntity.getJobsApplied();
            professionalEntity.getTimeSheets();
            professionalEntity.getCompany();
            
            return professionalEntity;
        }
        else
        {
            throw new ProfessionalNotFoundException("Professional ID " + professionalId + " does not exist!");
        }
    }
    
    @Override
    public ProfessionalEntity createNewProfessional(ProfessionalEntity newProfessional, Long companyId) throws UnknownPersistenceException, InputDataValidationException, CreateNewProfessionalException, ProfessionalNotFoundException
    {
        
        Set<ConstraintViolation<ProfessionalEntity>>constraintViolations = validator.validate(newProfessional);
        
        if(constraintViolations.isEmpty())
        {  
            try
            {
                if(companyId == null)
                {
                    throw new CreateNewProfessionalException("The professional must be associated with a company");
                }
                
                CompanyEntity companyEntity = companyEntitySessionBeanLocal.retrieveCompanyByCompanyId(companyId);
                
                em.persist(newProfessional);
                newProfessional.setCompany(companyEntity);
                
                em.flush();

                return newProfessional;
            }
            catch(PersistenceException ex)
            {
                throw new UnknownPersistenceException(ex.getMessage());
            }
            catch(CompanyNotFoundException ex)
            {
                throw new CreateNewProfessionalException("An error has occurred while creating the new job listing: " + ex.getMessage());
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
        
        
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<ProfessionalEntity>>constraintViolations)
    {
            String msg = "Input data validation error!:";

            for(ConstraintViolation constraintViolation:constraintViolations)
            {
                msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
            }

            return msg;
    }
    
    
}