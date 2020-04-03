package jsf.managedbean;

import entity.JobEntity;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "viewJobManagedBean")
@ViewScoped
public class ViewJobManagedBean implements Serializable
{
    private JobEntity jobEntityToView;
    
    public ViewJobManagedBean()
    {
        jobEntityToView = new JobEntity();
    }
    
    @PostConstruct
    public void postConstruct()
    {        
    }
    
    public JobEntity getJobEntityToView() 
    {
        return jobEntityToView;
    }

    public void setJobEntityToView(JobEntity jobEntityToView) 
    {
        this.jobEntityToView = jobEntityToView;
    }    
}
