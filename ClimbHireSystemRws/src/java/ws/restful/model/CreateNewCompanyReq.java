/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.CompanyEntity;
import entity.PaymentEntity;
import entity.SubscriptionEntity;

/**
 *
 * @author Casse
 */
public class CreateNewCompanyReq {
    private CompanyEntity newCompany;
    private SubscriptionEntity newSubscription;
    private PaymentEntity newPaymentRecord;

    public CreateNewCompanyReq(){
        
    }
    
    public CreateNewCompanyReq(CompanyEntity newCompany, SubscriptionEntity newSubscription, PaymentEntity newPaymentRecord) {
        this.newCompany = newCompany;
        this.newSubscription = newSubscription;
        this.newPaymentRecord = newPaymentRecord;
    }

    public CompanyEntity getNewCompany() {
        return newCompany;
    }

    public void setNewCompany(CompanyEntity newCompany) {
        this.newCompany = newCompany;
    }

    public SubscriptionEntity getNewSubscription() {
        return newSubscription;
    }

    public void setNewSubscription(SubscriptionEntity newSubscription) {
        this.newSubscription = newSubscription;
    }

    public PaymentEntity getNewPaymentRecord() {
        return newPaymentRecord;
    }

    public void setNewPaymentRecord(PaymentEntity newPaymentRecord) {
        this.newPaymentRecord = newPaymentRecord;
    }
    
    
}
