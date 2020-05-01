/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.SubscriptionEntity;

/**
 *
 * @author rycan
 */
public class CreateNewSubscriptionReq {
    
    private SubscriptionEntity subscriptionEntity;
    private Long companyId;

    public CreateNewSubscriptionReq() {
    }

    public CreateNewSubscriptionReq(SubscriptionEntity subscriptionEntity, Long companyId) {
        this.subscriptionEntity = subscriptionEntity;
        this.companyId = companyId;
    }

    public SubscriptionEntity getSubscriptionEntity() {
        return subscriptionEntity;
    }

    public void setSubscriptionEntity(SubscriptionEntity subscriptionEntity) {
        this.subscriptionEntity = subscriptionEntity;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    
    
    
}
