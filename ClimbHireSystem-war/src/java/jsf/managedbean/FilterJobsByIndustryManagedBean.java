package jsf.managedbean;

import ejb.session.stateless.IndustryEntitySessionBeanLocal;
import ejb.session.stateless.JobEntitySessionBeanLocal;
import entity.IndustryEntity;
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
import util.exception.IndustryNotFoundException;

@Named(value = "filterJobsByIndustryManagedBean")
@ViewScoped
public class FilterJobsByIndustryManagedBean implements Serializable
{
    @EJB
    private IndustryEntitySessionBeanLocal industryEntitySessionBeanLocal;
    @EJB
    private JobEntitySessionBeanLocal jobEntitySessionBeanLocal;
    
    @Inject
    private ViewJobManagedBean viewJobManagedBean;
        
    private TreeNode treeNode;
    private TreeNode selectedTreeNode;
    
    private List<JobEntity> jobEntities;
    
    public FilterJobsByIndustryManagedBean() 
    {
    }
   
    @PostConstruct
    public void postConstruct()
    {
        List<IndustryEntity> industryEntities = industryEntitySessionBeanLocal.retrieveAllRootIndustries();
        treeNode = new DefaultTreeNode("Root", null);
        
        for(IndustryEntity industryEntity:industryEntities)
        {
            createTreeNode(industryEntity, treeNode);
        }
        
        Long selectedIndustryId = (Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("jobFilterIndustry");
        
        if(selectedIndustryId != null)
        {
            for(TreeNode tn:treeNode.getChildren())
            {
                IndustryEntity ie = (IndustryEntity)tn.getData();

                if(ie.getIndustryId().equals(selectedIndustryId))
                {
                    selectedTreeNode = tn;
                    break;
                }
                else
                {
                    selectedTreeNode = searchTreeNode(selectedIndustryId, tn);
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
                IndustryEntity ie = (IndustryEntity)selectedTreeNode.getData();
                jobEntities = jobEntitySessionBeanLocal.filterJobsByIndustry(ie.getIndustryId());
            }
            catch(IndustryNotFoundException ex)
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
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("backMode", "filterJobsByIndustry");
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewJobDetails.xhtml");
    }

    private void createTreeNode(IndustryEntity industryEntity, TreeNode parentTreeNode)
    {
        TreeNode treeNode = new DefaultTreeNode(industryEntity, parentTreeNode);
                
        for(IndustryEntity ie:industryEntity.getSubIndustryEntities())
        {
            createTreeNode(ie, treeNode);
        }
    }

    private TreeNode searchTreeNode(Long selectedIndustryId, TreeNode treeNode)
    {
        for(TreeNode tn:treeNode.getChildren())
        {
            IndustryEntity ie = (IndustryEntity)tn.getData();
            
            if(ie.getIndustryId().equals(selectedIndustryId))
            {
                return tn;
            }
            else
            {
                return searchTreeNode(selectedIndustryId, tn);
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
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jobFilterIndustry", ((IndustryEntity)selectedTreeNode.getData()).getIndustryId());
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
