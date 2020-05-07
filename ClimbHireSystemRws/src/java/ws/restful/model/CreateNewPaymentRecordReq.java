/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.PaymentEntity;

/**
 *
 * @author rycan
 */
public class CreateNewPaymentRecordReq {
    
    private PaymentEntity paymentEntity;
    private Long companyId;

    public CreateNewPaymentRecordReq() {
    }

    public CreateNewPaymentRecordReq(PaymentEntity paymentEntity, Long companyId) {
        this.paymentEntity = paymentEntity;
        this.companyId = companyId;
    }

    public PaymentEntity getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(PaymentEntity paymentEntity) {
        this.paymentEntity = paymentEntity;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    
    
    
}