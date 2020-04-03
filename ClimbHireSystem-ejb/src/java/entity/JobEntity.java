package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class JobEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(nullable = false, unique = true, length = 60)
    @NotNull
    @Size(max = 60)
    private String jobTitle;

    @Column(nullable = false, length = 160)
    @NotNull
    @Size(max = 160)
    private String companyName;

    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    private String location;

    @Column(nullable = false)
    @NotNull
    @Min(0)
    private Integer numberOfVacancies;

    @Column(length = 5000)
    @Size(max = 5000)
    private String description;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePosted;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date applicationDeadline;

    @Digits(integer = 9, fraction = 2)
    private double salary;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private PositionTypeEntity positionTypeEntity;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private IndustryEntity industryEntity;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private JobFunctionEntity jobFunctionEntity;

    @ManyToMany(mappedBy = "jobEntities")
    private List<TagEntity> tagEntities;

    public JobEntity() {
        numberOfVacancies = 0;

        tagEntities = new ArrayList<>();
    }

    public JobEntity(String jobTitle, String companyName, String location, Integer numberOfVacancies, String description, double salary) {
        this();

        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.location = location;
        this.numberOfVacancies = numberOfVacancies;
        this.description = description;
        this.salary = salary;
//        this.datePosted = datePosted;
//        this.applicationDeadline = applicationDeadline;
    }

    public void addTag(TagEntity tagEntity) {
        if (tagEntity != null) {
            if (!this.tagEntities.contains(tagEntity)) {
                this.tagEntities.add(tagEntity);

                if (!tagEntity.getJobEntities().contains(this)) {
                    tagEntity.getJobEntities().add(this);
                }
            }
        }
    }

    public void removeTag(TagEntity tagEntity) {
        if (tagEntity != null) {
            if (this.tagEntities.contains(tagEntity)) {
                this.tagEntities.remove(tagEntity);

                if (tagEntity.getJobEntities().contains(this)) {
                    tagEntity.getJobEntities().remove(this);
                }
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.jobId != null ? this.jobId.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof JobEntity)) {
            return false;
        }

        JobEntity other = (JobEntity) object;

        if ((this.jobId == null && other.jobId != null) || (this.jobId != null && !this.jobId.equals(other.jobId))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "entity.chs.JobEntity [ jobId=" + this.jobId + " ]";
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getNumberOfVacancies() {
        return numberOfVacancies;
    }

    public void setNumberOfVacancies(Integer numberOfVacancies) {
        this.numberOfVacancies = numberOfVacancies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

//    public Date getPostedOn() 
//    {
//        return postedOn;
//    }
//
//    public void setPostedOn(Date postedOn) 
//    {
//        this.postedOn = postedOn;
//    }
//    
//    public Date getApplicationDeadline() 
//    {
//        return applicationDeadline;
//    }
//
//    public void setApplicationDeadline(Date applicationDeadline) 
//    {
//        this.applicationDeadline = applicationDeadline;
//    }
    public PositionTypeEntity getPositionTypeEntity() {
        return positionTypeEntity;
    }

    public void setPositionTypeEntity(PositionTypeEntity positionTypeEntity) {
        if (this.positionTypeEntity != null) {
            if (this.positionTypeEntity.getJobEntities().contains(this)) {
                this.positionTypeEntity.getJobEntities().remove(this);
            }
        }

        this.positionTypeEntity = positionTypeEntity;

        if (this.positionTypeEntity != null) {
            if (!this.positionTypeEntity.getJobEntities().contains(this)) {
                this.positionTypeEntity.getJobEntities().add(this);
            }
        }
    }

    public IndustryEntity getIndustryEntity() {
        return industryEntity;
    }

    public void setIndustryEntity(IndustryEntity industryEntity) {
        if (this.industryEntity != null) {
            if (this.industryEntity.getJobEntities().contains(this)) {
                this.industryEntity.getJobEntities().remove(this);
            }
        }

        this.industryEntity = industryEntity;

        if (this.industryEntity != null) {
            if (!this.industryEntity.getJobEntities().contains(this)) {
                this.industryEntity.getJobEntities().add(this);
            }
        }
    }

    public JobFunctionEntity getJobFunctionEntity() {
        return jobFunctionEntity;
    }

    public void setJobFunctionEntity(JobFunctionEntity jobFunctionEntity) {
        if (this.jobFunctionEntity != null) {
            if (this.jobFunctionEntity.getJobEntities().contains(this)) {
                this.jobFunctionEntity.getJobEntities().remove(this);
            }
        }

        this.jobFunctionEntity = jobFunctionEntity;

        if (this.jobFunctionEntity != null) {
            if (!this.jobFunctionEntity.getJobEntities().contains(this)) {
                this.jobFunctionEntity.getJobEntities().add(this);
            }
        }
    }

    public List<TagEntity> getTagEntities() {
        return tagEntities;
    }

    public void setTagEntities(List<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
    }
}
