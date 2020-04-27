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

/**
 *
 * @author Casse
 */
@Entity
public class ApplicationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;
    private String applicationStatus;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date applicationCreatedDate;
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true)
    private ProfessionalEntity createdBy;
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true)
    private JobListingEntity createdFor;

    public ApplicationEntity() {
    }

    public ApplicationEntity(String applicationStatus, Date applicationCreatedDate, ProfessionalEntity createdBy, JobListingEntity createdFor) {
        this.applicationStatus = applicationStatus;
        this.applicationCreatedDate = applicationCreatedDate;
        this.createdBy = createdBy;
        this.createdFor = createdFor;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
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

    public ProfessionalEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(ProfessionalEntity createdBy) {
        this.createdBy = createdBy;
    }

    public JobListingEntity getCreatedFor() {
        return createdFor;
    }

    public void setCreatedFor(JobListingEntity createdFor) {
        this.createdFor = createdFor;
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
        if (!(object instanceof ApplicationEntity)) {
            return false;
        }
        ApplicationEntity other = (ApplicationEntity) object;
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
