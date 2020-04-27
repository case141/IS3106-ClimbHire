/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Casse
 */
@Entity
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    private String companyName;
    private String password;
    private String email;
    private Integer contactNumber;
    private String companyBio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfFounding;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateJoined;
    @OneToOne(optional=false)
    private Subscription subscription;
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true)
    private ArrayList<Payment> paymentHistory;

    public Company() {
    }

    public Company(String password, String email, Integer contactNumber, String companyBio, Date dateOfFounding, Date dateJoined) {
        this.password = password;
        this.email = email;
        this.contactNumber = contactNumber;
        this.companyBio = companyBio;
        this.dateOfFounding = dateOfFounding;
        this.dateJoined = dateJoined;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Payment> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(ArrayList<Payment> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCompanyBio() {
        return companyBio;
    }

    public void setCompanyBio(String companyBio) {
        this.companyBio = companyBio;
    }

    public Date getDateOfFounding() {
        return dateOfFounding;
    }

    public void setDateOfFounding(Date dateOfFounding) {
        this.dateOfFounding = dateOfFounding;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }
    
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyId != null ? companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the companyId fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Company[ id=" + companyId + " ]";
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
    
}
