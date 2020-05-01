/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.SubscriptionEntity;

/**
 *
 * @author Casse
 */
public class RetrieveSubscriptionRsp {
    private SubscriptionEntity subscriptionEntity;

    public RetrieveSubscriptionRsp() {
        
    }
    
    public RetrieveSubscriptionRsp(SubscriptionEntity subscriptionEntity) {
        this.subscriptionEntity = subscriptionEntity;
    }

    public SubscriptionEntity getSubscriptionEntity() {
        return subscriptionEntity;
    }

    public void setSubscriptionEntity(SubscriptionEntity subscriptionEntity) {
        this.subscriptionEntity = subscriptionEntity;
    }
    
}
