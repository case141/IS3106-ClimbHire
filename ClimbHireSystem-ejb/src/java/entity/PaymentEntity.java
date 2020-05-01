/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import util.enumeration.PaymentStatusEnum;

/**
 *
 * @author Casse
 */
@Entity
public class PaymentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Double amountPaid;
    private PaymentStatusEnum paymentStatusEnum;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date paymentDate;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CompanyEntity company;
    
    
    public PaymentEntity() {
    }

    public PaymentEntity(Double amountPaid, PaymentStatusEnum paymentStatusEnum, CompanyEntity company, Date paymentDate) {
        this.amountPaid = amountPaid;
        this.paymentStatusEnum = paymentStatusEnum;
        this.company = company;
        this.paymentDate = paymentDate;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Date getDatePaid() {
        return paymentDate;
    }

    public void setDatePaid(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public PaymentStatusEnum getStatus() {
        return paymentStatusEnum;
    }

    public void setStatus(PaymentStatusEnum paymentStatusEnum) {
        this.paymentStatusEnum = paymentStatusEnum;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the paymentId fields are not set
        if (!(object instanceof PaymentEntity)) {
            return false;
        }
        PaymentEntity other = (PaymentEntity) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Payment[ id=" + paymentId + " ]";
    }
    
}
