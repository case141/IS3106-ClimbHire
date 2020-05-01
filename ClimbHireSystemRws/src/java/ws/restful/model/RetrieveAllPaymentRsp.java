/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.PaymentEntity;
import java.util.List;

/**
 *
 * @author Casse
 */
public class RetrieveAllPaymentRsp {
    private List<PaymentEntity> paymentList;

    public RetrieveAllPaymentRsp() {
        
    }
    
    public RetrieveAllPaymentRsp(List<PaymentEntity> paymentList) {
        this.paymentList = paymentList;
    }

    public List<PaymentEntity> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<PaymentEntity> paymentList) {
        this.paymentList = paymentList;
    }
    
}
