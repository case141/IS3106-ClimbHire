/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import util.enumeration.SubscriptionStatusEnum;
import util.enumeration.SubscriptionTypeEnum;

/**
 *
 * @author Casse
 */
@Entity
public class SubscriptionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private SubscriptionTypeEnum subscriptionTypeEnum;
    private String description;
    private Double amount;
    private SubscriptionStatusEnum statusEnum;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date renewalDate;
    
    
    @OneToOne(optional = true)
    @JoinColumn(nullable = false)
    private CompanyEntity company;
    

    public SubscriptionEntity() {
        
  
    }

    public SubscriptionEntity(SubscriptionTypeEnum subscriptionTypeEnum, String description, Double amount, SubscriptionStatusEnum statusEnum, Date renewalDate) {
        this.subscriptionTypeEnum = subscriptionTypeEnum;
        this.description = description;
        this.amount = amount;
        this.statusEnum = statusEnum;
        this.renewalDate = renewalDate;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public SubscriptionTypeEnum getSubscriptionType() {
        return subscriptionTypeEnum;
    }

    public void setSubscriptionType(SubscriptionTypeEnum subscriptionTypeEnum) {
        this.subscriptionTypeEnum = subscriptionTypeEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public SubscriptionStatusEnum getStatus() {
        return statusEnum;
    }

    public void setStatus(SubscriptionStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
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
        hash += (subscriptionId != null ? subscriptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the subscriptionId fields are not set
        if (!(object instanceof SubscriptionEntity)) {
            return false;
        }
        SubscriptionEntity other = (SubscriptionEntity) object;
        if ((this.subscriptionId == null && other.subscriptionId != null) || (this.subscriptionId != null && !this.subscriptionId.equals(other.subscriptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Subscription[ id=" + subscriptionId + " ]";
    }

}
