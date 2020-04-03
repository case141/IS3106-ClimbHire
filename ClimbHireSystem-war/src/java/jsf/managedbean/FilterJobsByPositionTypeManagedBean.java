package jsf.managedbean;

import ejb.session.stateless.PositionTypeEntitySessionBeanLocal;
import ejb.session.stateless.JobEntitySessionBeanLocal;
import entity.PositionTypeEntity;
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
import util.exception.PositionTypeNotFoundException;

@Named(value = "filterJobsByPositionTypeManagedBean")
@ViewScoped
public class FilterJobsByPositionTypeManagedBean implements Serializable
{
    @EJB
    private PositionTypeEntitySessionBeanLocal positionTypeEntitySessionBeanLocal;
    @EJB
    private JobEntitySessionBeanLocal jobEntitySessionBeanLocal;
    
    @Inject
    private ViewJobManagedBean viewJobManagedBean;
        
    private TreeNode treeNode;
    private TreeNode selectedTreeNode;
    
    private List<JobEntity> jobEntities;
    
    public FilterJobsByPositionTypeManagedBean() 
    {
    }
   
    @PostConstruct
    public void postConstruct()
    {
        List<PositionTypeEntity> positionTypeEntities = positionTypeEntitySessionBeanLocal.retrieveAllRootPositionTypes();
        treeNode = new DefaultTreeNode("Root", null);
        
        for(PositionTypeEntity positionTypeEntity:positionTypeEntities)
        {
            createTreeNode(positionTypeEntity, treeNode);
        }
        
        Long selectedPositionTypeId = (Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("jobFilterPositionType");
        
        if(selectedPositionTypeId != null)
        {
            for(TreeNode tn:treeNode.getChildren())
            {
                PositionTypeEntity pte = (PositionTypeEntity)tn.getData();

                if(pte.getPositionTypeId().equals(selectedPositionTypeId))
                {
                    selectedTreeNode = tn;
                    break;
                }
                else
                {
                    selectedTreeNode = searchTreeNode(selectedPositionTypeId, tn);
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
                PositionTypeEntity pte = (PositionTypeEntity)selectedTreeNode.getData();
                jobEntities = jobEntitySessionBeanLocal.filterJobsByPositionType(pte.getPositionTypeId());
            }
            catch(PositionTypeNotFoundException ex)
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
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("backMode", "filterJobsByPositionType");
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewJobDetails.xhtml");
    }
   
    private void createTreeNode(PositionTypeEntity positionTypeEntity, TreeNode parentTreeNode)
    {
        TreeNode treeNode = new DefaultTreeNode(positionTypeEntity, parentTreeNode);
                
        for(PositionTypeEntity pte:positionTypeEntity.getSubPositionTypeEntities())
        {
            createTreeNode(pte, treeNode);
        }
    }
    
    private TreeNode searchTreeNode(Long selectedPositionTypeId, TreeNode treeNode)
    {
        for(TreeNode tn:treeNode.getChildren())
        {
            PositionTypeEntity pte = (PositionTypeEntity)tn.getData();
            
            if(pte.getPositionTypeId().equals(selectedPositionTypeId))
            {
                return tn;
            }
            else
            {
                return searchTreeNode(selectedPositionTypeId, tn);
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
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jobFilterPositionType", ((PositionTypeEntity)selectedTreeNode.getData()).getPositionTypeId());
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
