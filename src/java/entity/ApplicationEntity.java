package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class ApplicationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;
    private int jobId;
    private String applicationStatus;
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date applicationCreatedDate;
//    private Professional createdBy;
//    private JobEntity createdFor;
    private String resume;

//    public Application(String applicationStatus, Date applicationCreatedDate, Professional createdBy, JobEntity createdFor) {
//        this.applicationStatus = applicationStatus;
//        this.applicationCreatedDate = applicationCreatedDate;
//        this.createdBy = createdBy;
//        this.createdFor = createdFor;
//    }
    public ApplicationEntity()
    {}
    
    public ApplicationEntity(int jobId, String applicationStatus, String resume) {
        this();
        this.jobId = jobId;
        this.applicationStatus = applicationStatus;
        this.resume = resume;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

//    public Date getApplicationCreatedDate() {
//        return applicationCreatedDate;
//    }
//
//    public void setApplicationCreatedDate(Date applicationCreatedDate) {
//        this.applicationCreatedDate = applicationCreatedDate;
//    }
//
//    public Professional getCreatedBy() {
//        return createdBy;
//    }
//    public void setCreatedBy(Professional createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public JobEntity getCreatedFor() {
//        return createdFor;
//    }
//
//    public void setCreatedFor(JobEntity createdFor) {
//        this.createdFor = createdFor;
//    }
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

}
