/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ApplicationEntity;
import entity.CompanyEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.enumeration.ApplicationStatusEnum;
import util.exception.ApplicationNotFoundException;
import util.exception.CompanyNotFoundException;

/**
 *
 * @author Casse
 */
@Stateless
public class ApplicationEntitySessionBean implements ApplicationEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public ApplicationEntitySessionBean()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public ApplicationEntity retrieveApplicationByApplicationId(Long applicationId) throws ApplicationNotFoundException
    {
        ApplicationEntity applicationEntity = em.find(ApplicationEntity.class, applicationId);
        
        if(applicationEntity != null)
        {
            return applicationEntity;
        }
        else
        {
            throw new ApplicationNotFoundException("Application ID " + applicationId + " does not exist!");
        }
    }
    
    public void updateApplicationStatus(ApplicationEntity applicationEntity) throws ApplicationNotFoundException{
        if(applicationEntity != null && applicationEntity.getApplicationId()!= null)
        {
            ApplicationEntity applicationEntityToUpdate = retrieveApplicationByApplicationId(applicationEntity.getApplicationId());
            applicationEntityToUpdate.setApplicationStatusEnum(applicationEntity.getApplicationStatusEnum());          
        }
        else
        {
            throw new ApplicationNotFoundException("Application Id not provided for application to be updated");
        }

    }

}