package jsf.managedbean;

import ejb.session.stateless.JobEntitySessionBeanLocal;
import ejb.session.stateless.TagEntitySessionBeanLocal;
import entity.JobEntity;
import entity.TagEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "filterJobsByTagsManagedBean")
@ViewScoped
public class FilterJobsByTagsManagedBean implements Serializable
{
    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;
    @EJB
    private JobEntitySessionBeanLocal jobEntitySessionBeanLocal;
    
    @Inject
    private ViewJobManagedBean viewJobManagedBean;
    
    private String condition;
    private List<Long> selectedTagIds;
    private List<SelectItem> selectItems;
    private List<JobEntity> jobEntities;
    
    public FilterJobsByTagsManagedBean()
    {
        condition = "OR";
    }
    
    @PostConstruct
    public void postConstruct()
    {
        List<TagEntity> tagEntities = tagEntitySessionBeanLocal.retrieveAllTags();
        selectItems = new ArrayList<>();
        
        for(TagEntity tagEntity:tagEntities)
        {
            selectItems.add(new SelectItem(tagEntity.getTagId(), tagEntity.getName(), tagEntity.getName()));
        }
        
        condition = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("jobFilterCondition");        
        selectedTagIds = (List<Long>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("jobFilterTags");
        
        filterJob();
    }
    
    @PreDestroy
    public void preDestroy()
    {
    }
    
    public void filterJob()
    {        
        if(selectedTagIds != null && selectedTagIds.size() > 0)
        {
            jobEntities = jobEntitySessionBeanLocal.filterJobsByTags(selectedTagIds, condition);
        }
        else
        {
            jobEntities = jobEntitySessionBeanLocal.retrieveAllJobs();
        }
    }
    
    public void viewJobDetails(ActionEvent event) throws IOException
    {
        Long jobIdToView = (Long)event.getComponent().getAttributes().get("jobId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("jobIdToView", jobIdToView);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("backMode", "filterJobsByTags");
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewJobDetails.xhtml");
    }

    public ViewJobManagedBean getViewJobManagedBean() 
    {
        return viewJobManagedBean;
    }

    public void setViewJobManagedBean(ViewJobManagedBean viewJobManagedBean) 
    {
        this.viewJobManagedBean = viewJobManagedBean;
    }
    
    public String getCondition() 
    {
        return condition;
    }

    public void setCondition(String condition) 
    {
        this.condition = condition;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jobFilterCondition", condition);
    }
    
    public List<Long> getSelectedTagIds() 
    {
        return selectedTagIds;
    }

    public void setSelectedTagIds(List<Long> selectedTagIds) 
    {
        this.selectedTagIds = selectedTagIds;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jobFilterTags", selectedTagIds);
    }

    public List<SelectItem> getSelectItems() 
    {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) 
    {
        this.selectItems = selectItems;
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