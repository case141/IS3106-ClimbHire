/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

/**
 *
 * @author rycan
 */
public class CreateNewPaymentRecordRsp {
    
    private Long paymentId;

    public CreateNewPaymentRecordRsp() {
    }

    public CreateNewPaymentRecordRsp(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    
    
    
}
