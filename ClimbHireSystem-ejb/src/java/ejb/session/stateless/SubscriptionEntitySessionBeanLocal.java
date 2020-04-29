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

/**
 *
 * @author Casse
 */
@Local
public interface SubscriptionEntitySessionBeanLocal {

    public List<SubscriptionEntity> retrieveAllSubscription();

    public SubscriptionEntity createNewSubscription(SubscriptionEntity newSubscription);

    public SubscriptionEntity retrieveSubscriptionByCompany(CompanyEntity company) throws CompanyNotFoundException;
    
}
