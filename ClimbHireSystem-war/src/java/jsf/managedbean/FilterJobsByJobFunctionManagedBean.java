package jsf.managedbean;

import ejb.session.stateless.JobFunctionEntitySessionBeanLocal;
import ejb.session.stateless.JobEntitySessionBeanLocal;
import entity.JobFunctionEntity;
import entity.JobEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import util.exception.JobFunctionNotFoundException;

@Named(value = "filterJobsByJobFunctionManagedBean")
@ViewScoped
public class FilterJobsByJobFunctionManagedBean implements Serializable
{
    @EJB
    private JobFunctionEntitySessionBeanLocal jobFunctionEntitySessionBeanLocal;
    @EJB
    private JobEntitySessionBeanLocal jobEntitySessionBeanLocal;
    
    @Inject
    private ViewJobManagedBean viewJobManagedBean;
        
    private TreeNode treeNode;
    private TreeNode selectedTreeNode;
    
    private List<JobEntity> jobEntities;
    
    public FilterJobsByJobFunctionManagedBean() 
    {
    }
   
    @PostConstruct
    public void postConstruct()
    {
        List<JobFunctionEntity> jobFunctionEntities = jobFunctionEntitySessionBeanLocal.retrieveAllRootJobFunctions();
        treeNode = new DefaultTreeNode("Root", null);
        
        for(JobFunctionEntity jobFunctionEntity:jobFunctionEntities)
        {
            createTreeNode(jobFunctionEntity, treeNode);
        }
        
        Long selectedJobFunctionId = (Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("jobFilterJobFunction");
        
        if(selectedJobFunctionId != null)
        {
            for(TreeNode tn:treeNode.getChildren())
            {
                JobFunctionEntity jfe = (JobFunctionEntity)tn.getData();

                if(jfe.getJobFunctionId().equals(selectedJobFunctionId))
                {
                    selectedTreeNode = tn;
                    break;
                }
                else
                {
                    selectedTreeNode = searchTreeNode(selectedJobFunctionId, tn);
                }            
            }
        }
        
        filterJob();
    }
    
    public void filterJob()
    {
        if(selectedTreeNode != null)
        {               
            try
            {
                JobFunctionEntity jfe = (JobFunctionEntity)selectedTreeNode.getData();
                jobEntities = jobEntitySessionBeanLocal.filterJobsByJobFunction(jfe.getJobFunctionId());
            }
            catch(JobFunctionNotFoundException ex)
            {
                jobEntities = jobEntitySessionBeanLocal.retrieveAllJobs();
            }
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
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("backMode", "filterJobsByJobFunction");
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewJobDetails.xhtml");
    }
    
    private void createTreeNode(JobFunctionEntity jobFunctionEntity, TreeNode parentTreeNode)
    {
        TreeNode treeNode = new DefaultTreeNode(jobFunctionEntity, parentTreeNode);
                
        for(JobFunctionEntity jfe:jobFunctionEntity.getSubJobFunctionEntities())
        {
            createTreeNode(jfe, treeNode);
        }
    }
    
    private TreeNode searchTreeNode(Long selectedJobFunctionId, TreeNode treeNode)
    {
        for(TreeNode tn:treeNode.getChildren())
        {
            JobFunctionEntity jfe = (JobFunctionEntity)tn.getData();
            
            if(jfe.getJobFunctionId().equals(selectedJobFunctionId))
            {
                return tn;
            }
            else
            {
                return searchTreeNode(selectedJobFunctionId, tn);
            }            
        }
        
        return null;
    }

    public ViewJobManagedBean getViewJobManagedBean() 
    {
        return viewJobManagedBean;
    }

    public void setViewJobManagedBean(ViewJobManagedBean viewJobManagedBean) 
    {
        this.viewJobManagedBean = viewJobManagedBean;
    }
    
    public TreeNode getTreeNode() 
    {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) 
    {
        this.treeNode = treeNode;
    }
    
    public TreeNode getSelectedTreeNode()
    {
        return selectedTreeNode;
    }

    public void setSelectedTreeNode(TreeNode selectedTreeNode) 
    {
        this.selectedTreeNode = selectedTreeNode;
        
        if(selectedTreeNode != null)
        {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jobFilterJobFunction", ((JobFunctionEntity)selectedTreeNode.getData()).getJobFunctionId());
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
