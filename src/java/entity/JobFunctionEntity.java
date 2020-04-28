package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class JobFunctionEntity implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobFunctionId;
    
    @Column(nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    private String name;
    
    @OneToMany(mappedBy = "parentJobFunctionEntity")
    private List<JobFunctionEntity> subJobFunctionEntities;
    
    @ManyToOne
    private JobFunctionEntity parentJobFunctionEntity;
    
    @OneToMany(mappedBy = "jobFunctionEntity")
    private List<JobEntity> jobEntities;

    public JobFunctionEntity() 
    {
        subJobFunctionEntities = new ArrayList<>();
        
        jobEntities = new ArrayList<>();
    }
    
    public JobFunctionEntity(String name) 
    {
        this();
        
        this.name = name;
    }
    
    public Long getJobFunctionId() 
    {
        return jobFunctionId;
    }
    
    public void setJobFunctionId(Long jobFunctionId) 
    {
        this.jobFunctionId = jobFunctionId;
    }

    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (jobFunctionId != null ? jobFunctionId.hashCode() : 0);
        
        return hash;
    }
    
    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the jobFunctionId fields are not set
        if (!(object instanceof JobFunctionEntity)) 
        {
            return false;
        }
        
        JobFunctionEntity other = (JobFunctionEntity) object;
        
        if ((this.jobFunctionId == null && other.jobFunctionId != null) || (this.jobFunctionId != null && !this.jobFunctionId.equals(other.jobFunctionId))) 
        {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() 
    {
        return "entity.JobFunctionEntity[ id=" + jobFunctionId + " ]";
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public List<JobFunctionEntity> getSubJobFunctionEntities() 
    {
        return subJobFunctionEntities;
    }
    
    public void setSubJobFunctionEntities(List<JobFunctionEntity> subJobFunctionEntities) 
    {
        this.subJobFunctionEntities = subJobFunctionEntities;
    }

    public JobFunctionEntity getParentJobFunctionEntity() 
    {
        return parentJobFunctionEntity;
    }
    
    public void setParentJobFunctionEntity(JobFunctionEntity parentJobFunctionEntity) 
    {
        if(this.parentJobFunctionEntity != null)
        {
            if(this.parentJobFunctionEntity.getSubJobFunctionEntities().contains(this))
            {
                this.parentJobFunctionEntity.getSubJobFunctionEntities().remove(this);
            }
        }
        
        this.parentJobFunctionEntity = parentJobFunctionEntity;
        
        if(this.parentJobFunctionEntity != null)
        {
            if(!this.parentJobFunctionEntity.getSubJobFunctionEntities().contains(this))
            {
                this.parentJobFunctionEntity.getSubJobFunctionEntities().add(this);
            }
        }
    }

    public List<JobEntity> getJobEntities() 
    {
        return jobEntities;
    }
    
    public void setJobEntities(List<JobEntity> jobEntities)
    {
        this.jobEntities = jobEntities;
    }
}