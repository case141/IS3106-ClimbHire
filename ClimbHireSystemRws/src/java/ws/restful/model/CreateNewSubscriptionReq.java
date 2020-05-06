/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.CompanyEntity;
import entity.SubscriptionEntity;

/**
 *
 * @author rycan
 */
public class CreateNewSubscriptionReq {
    
    private SubscriptionEntity subscriptionEntity;
    private CompanyEntity companyEntity;

    public CreateNewSubscriptionReq() {
    }

    public CreateNewSubscriptionReq(SubscriptionEntity subscriptionEntity, CompanyEntity companyEntity) {
        this.subscriptionEntity = subscriptionEntity;
        this.companyEntity = companyEntity;
    }

    public SubscriptionEntity getSubscriptionEntity() {
        return subscriptionEntity;
    }

    public void setSubscriptionEntity(SubscriptionEntity subscriptionEntity) {
        this.subscriptionEntity = subscriptionEntity;
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

   
    
    
    
}
