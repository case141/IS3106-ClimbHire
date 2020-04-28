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
public class PositionTypeEntity implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionTypeId;
    
    @Column(nullable = false, length = 15)
    @NotNull
    @Size(max = 15)
    private String name;
    
    @OneToMany(mappedBy = "parentPositionTypeEntity")
    private List<PositionTypeEntity> subPositionTypeEntities;
    
    @ManyToOne
    private PositionTypeEntity parentPositionTypeEntity;
    
    @OneToMany(mappedBy = "positionTypeEntity")
    private List<JobEntity> jobEntities;

    public PositionTypeEntity() 
    {
        subPositionTypeEntities = new ArrayList<>();
        
        jobEntities = new ArrayList<>();
    }
    
    public PositionTypeEntity(String name) 
    {
        this();
        
        this.name = name;
    }
    
    public Long getPositionTypeId() 
    {
        return positionTypeId;
    }
    
    public void setPositionTypeId(Long positionTypeId) 
    {
        this.positionTypeId = positionTypeId;
    }

    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (positionTypeId != null ? positionTypeId.hashCode() : 0);
        
        return hash;
    }
    
    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the positionTypeId fields are not set
        if (!(object instanceof PositionTypeEntity)) 
        {
            return false;
        }
        
        PositionTypeEntity other = (PositionTypeEntity) object;
        
        if ((this.positionTypeId == null && other.positionTypeId != null) || (this.positionTypeId != null && !this.positionTypeId.equals(other.positionTypeId))) 
        {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() 
    {
        return "entity.PositionTypeEntity[ id=" + positionTypeId + " ]";
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public List<PositionTypeEntity> getSubPositionTypeEntities() 
    {
        return subPositionTypeEntities;
    }
    
    public void setSubPositionTypeEntities(List<PositionTypeEntity> subPositionTypeEntities) 
    {
        this.subPositionTypeEntities = subPositionTypeEntities;
    }

    public PositionTypeEntity getParentPositionTypeEntity() 
    {
        return parentPositionTypeEntity;
    }
    
    public void setParentPositionTypeEntity(PositionTypeEntity parentPositionTypeEntity) 
    {
        if(this.parentPositionTypeEntity != null)
        {
            if(this.parentPositionTypeEntity.getSubPositionTypeEntities().contains(this))
            {
                this.parentPositionTypeEntity.getSubPositionTypeEntities().remove(this);
            }
        }
        
        this.parentPositionTypeEntity = parentPositionTypeEntity;
        
        if(this.parentPositionTypeEntity != null)
        {
            if(!this.parentPositionTypeEntity.getSubPositionTypeEntities().contains(this))
            {
                this.parentPositionTypeEntity.getSubPositionTypeEntities().add(this);
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