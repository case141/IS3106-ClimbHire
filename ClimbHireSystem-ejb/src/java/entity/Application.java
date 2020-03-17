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
import javax.persistence.Temporal;

/**
 *
 * @author Casse
 */
@Entity
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationId;
    private String applicationStatus;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date applicationCreatedDate;
    private Professional createdBy;
    private JobListing createdFor;

    public Application() {
    }

    public Application(String applicationStatus, Date applicationCreatedDate, Professional createdBy, JobListing createdFor) {
        this.applicationStatus = applicationStatus;
        this.applicationCreatedDate = applicationCreatedDate;
        this.createdBy = createdBy;
        this.createdFor = createdFor;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Date getApplicationCreatedDate() {
        return applicationCreatedDate;
    }

    public void setApplicationCreatedDate(Date applicationCreatedDate) {
        this.applicationCreatedDate = applicationCreatedDate;
    }

    public Professional getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Professional createdBy) {
        this.createdBy = createdBy;
    }

    public JobListing getCreatedFor() {
        return createdFor;
    }

    public void setCreatedFor(JobListing createdFor) {
        this.createdFor = createdFor;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationId != null ? applicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the applicationId fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.applicationId == null && other.applicationId != null) || (this.applicationId != null && !this.applicationId.equals(other.applicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Application[ id=" + applicationId + " ]";
    }
    
}
