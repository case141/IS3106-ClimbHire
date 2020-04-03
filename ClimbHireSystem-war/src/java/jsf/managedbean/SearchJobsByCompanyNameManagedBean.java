package jsf.managedbean;

import ejb.session.stateless.JobEntitySessionBeanLocal;
import entity.JobEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "searchJobsByCompanyNameManagedBean")
@ViewScoped
public class SearchJobsByCompanyNameManagedBean implements Serializable
{
    @EJB
    private JobEntitySessionBeanLocal jobEntitySessionBeanLocal;
   
    @Inject
    private ViewJobManagedBean viewJobManagedBean;
    
    private String searchString;
    private List<JobEntity> jobEntities;
     
    public SearchJobsByCompanyNameManagedBean() 
    {
    }
    
    @PostConstruct
    public void postConstruct()
    {
        searchString = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("jobSearchString");
        
        if(searchString == null || searchString.trim().length() == 0)
        {
            jobEntities = jobEntitySessionBeanLocal.retrieveAllJobs();
        }
        else
        {
            jobEntities = jobEntitySessionBeanLocal.searchJobsByCompanyName(searchString);
        }
    }
    
    public void searchJob()
    {
        if(searchString == null || searchString.trim().length() == 0)
        {
            jobEntities = jobEntitySessionBeanLocal.retrieveAllJobs();
        }
        else
        {
            jobEntities = jobEntitySessionBeanLocal.searchJobsByCompanyName(searchString);
        }
    }

    public ViewJobManagedBean getViewJobManagedBean() 
    {
        return viewJobManagedBean;
    }

    public void setViewJobManagedBean(ViewJobManagedBean viewJobManagedBean) 
    {
        this.viewJobManagedBean = viewJobManagedBean;
    }
    
    public String getSearchString() 
    {
        return searchString;
    }

    public void setSearchString(String searchString)
    {
        this.searchString = searchString;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jobSearchString", searchString);
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
