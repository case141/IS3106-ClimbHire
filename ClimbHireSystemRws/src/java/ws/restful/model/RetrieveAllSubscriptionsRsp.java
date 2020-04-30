/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.SubscriptionEntity;
import java.util.List;

/**
 *
 * @author Casse
 */
public class RetrieveAllSubscriptionsRsp {
    public List<SubscriptionEntity> subscriptions;
    
    public RetrieveAllSubscriptionsRsp(){
        
    }

    public RetrieveAllSubscriptionsRsp(List<SubscriptionEntity> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<SubscriptionEntity> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionEntity> subscriptions) {
        this.subscriptions = subscriptions;
    }
    
    
}
