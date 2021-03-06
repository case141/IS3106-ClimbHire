/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CompanyEntity;
import entity.SubscriptionEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CompanyNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.SubscriptionCompanyExistException;
import util.exception.SubscriptionNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Casse
 */
@Local
public interface SubscriptionEntitySessionBeanLocal {

    public List<SubscriptionEntity> retrieveAllSubscription();

    public SubscriptionEntity createNewSubscription(SubscriptionEntity newSubscription) throws SubscriptionCompanyExistException, UnknownPersistenceException, InputDataValidationException;

    public SubscriptionEntity retrieveSubscriptionByCompany(CompanyEntity company) throws CompanyNotFoundException;

    public void switchSubscriptionPlan(SubscriptionEntity subscription) throws InputDataValidationException, SubscriptionNotFoundException, CompanyNotFoundException;

    public void terminateSubscriptionPlan(SubscriptionEntity subscription) throws SubscriptionNotFoundException, InputDataValidationException, CompanyNotFoundException;
    
}
