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
public class IndustryEntity implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long industryId;
    
    @Column(nullable = false, unique = true, length = 50)
    @NotNull
    @Size(max = 50)
    private String name;
    
    @OneToMany(mappedBy = "parentIndustryEntity")
    private List<IndustryEntity> subIndustryEntities;
    
    @ManyToOne
    private IndustryEntity parentIndustryEntity;
    
    @OneToMany(mappedBy = "industryEntity")
    private List<JobEntity> jobEntities;

    public IndustryEntity() 
    {
        subIndustryEntities = new ArrayList<>();
        
        jobEntities = new ArrayList<>();
    }
    
    public IndustryEntity(String name) 
    {
        this();
        
        this.name = name;
    }
    
    public Long getIndustryId() 
    {
        return industryId;
    }
    
    public void setIndustryId(Long industryId) 
    {
        this.industryId = industryId;
    }

    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (industryId != null ? industryId.hashCode() : 0);
        
        return hash;
    }
    
    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the industryId fields are not set
        if (!(object instanceof IndustryEntity)) 
        {
            return false;
        }
        
        IndustryEntity other = (IndustryEntity) object;
        
        if ((this.industryId == null && other.industryId != null) || (this.industryId != null && !this.industryId.equals(other.industryId))) 
        {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() 
    {
        return "entity.IndustryEntity[ id=" + industryId + " ]";
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public List<IndustryEntity> getSubIndustryEntities() 
    {
        return subIndustryEntities;
    }
    
    public void setSubIndustryEntities(List<IndustryEntity> subIndustryEntities) 
    {
        this.subIndustryEntities = subIndustryEntities;
    }

    public IndustryEntity getParentIndustryEntity() 
    {
        return parentIndustryEntity;
    }
    
    public void setParentIndustryEntity(IndustryEntity parentIndustryEntity) 
    {
        if(this.parentIndustryEntity != null)
        {
            if(this.parentIndustryEntity.getSubIndustryEntities().contains(this))
            {
                this.parentIndustryEntity.getSubIndustryEntities().remove(this);
            }
        }
        
        this.parentIndustryEntity = parentIndustryEntity;
        
        if(this.parentIndustryEntity != null)
        {
            if(!this.parentIndustryEntity.getSubIndustryEntities().contains(this))
            {
                this.parentIndustryEntity.getSubIndustryEntities().add(this);
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