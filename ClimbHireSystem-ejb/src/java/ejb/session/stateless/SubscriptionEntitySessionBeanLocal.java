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
import util.exception.CreateNewSubscriptionException;
import util.exception.InputDataValidationException;
import util.exception.SubscriptionCompanyExistException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Casse
 */
@Local
public interface SubscriptionEntitySessionBeanLocal {

    public List<SubscriptionEntity> retrieveAllSubscription();

    public SubscriptionEntity createNewSubscription(SubscriptionEntity newSubscription, Long companyId) throws SubscriptionCompanyExistException, UnknownPersistenceException, InputDataValidationException, CreateNewSubscriptionException;

    public SubscriptionEntity retrieveSubscriptionByCompany(CompanyEntity company) throws CompanyNotFoundException;
    
}
