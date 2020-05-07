/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import util.security.CryptographicHelper;

/**
 *
 * @author Casse
 */
@Entity
public class CompanyEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    private String companyName;
    private String password;   
    @Column(columnDefinition = "CHAR(32) NOT NULL")
    private String salt;
    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Size(max = 64)
    @Email
    private String email;
    private Integer contactNumber;
    private String companyBio; //optional
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfFounding;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateJoined;
    
    @OneToOne(mappedBy = "company")
    private SubscriptionEntity subscription;
    
    @OneToMany(mappedBy = "company")
    private List<PaymentEntity> paymentHistory; //optional
    
    @OneToMany(mappedBy = "company")
    private List<ProfessionalEntity> professionalsList;
    
    @OneToMany(mappedBy = "company")
    private List<JobListingEntity> listOfJobs;
    

    public CompanyEntity() {
        // Newly added in v4.5
        this.salt = CryptographicHelper.getInstance().generateRandomString(32);
        
        paymentHistory = new ArrayList<>();
        professionalsList = new ArrayList<>();
        listOfJobs = new ArrayList<>();  
    }

    public CompanyEntity(String companyName, String password, String email, Integer contactNumber, String companyBio, Date dateOfFounding, Date dateJoined) {
        
        this();
        
        this.companyName = companyName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.companyBio = companyBio;
        this.dateOfFounding = dateOfFounding;
        this.dateJoined = dateJoined;
        
        setPassword(password);
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password != null)
        {
            this.password = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + this.salt));
        }
        else
        {
            this.password = null;
        }
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

    public SubscriptionEntity getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionEntity subscription) {
        this.subscription = subscription;
    }

    public List<PaymentEntity> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(List<PaymentEntity> paymentHistory) {
        this.paymentHistory = paymentHistory;
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
        if (!(object instanceof CompanyEntity)) {
            return false;
        }
        CompanyEntity other = (CompanyEntity) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Company[ id=" + companyId + " ]";
    }

    // Newly added in v4.5
    public String getSalt() {
        return salt;
    }

    // Newly added in v4.5
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<ProfessionalEntity> getProfessionalsList() {
        return professionalsList;
    }

    public void setProfessionalsList(List<ProfessionalEntity> professionalsList) {
        this.professionalsList = professionalsList;
    }

    public List<JobListingEntity> getListOfJobs() {
        return listOfJobs;
    }

    public void setListOfJobs(List<JobListingEntity> listOfJobs) {
        this.listOfJobs = listOfJobs;
    }
}