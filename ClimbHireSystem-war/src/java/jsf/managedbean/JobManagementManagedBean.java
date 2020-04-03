package jsf.managedbean;

import ejb.session.stateless.PositionTypeEntitySessionBeanLocal;
import ejb.session.stateless.IndustryEntitySessionBeanLocal;
import ejb.session.stateless.JobFunctionEntitySessionBeanLocal;
import ejb.session.stateless.JobEntitySessionBeanLocal;
import ejb.session.stateless.TagEntitySessionBeanLocal;
import entity.Application;
import entity.PositionTypeEntity;
import entity.IndustryEntity;
import entity.JobFunctionEntity;
import entity.JobEntity;
import entity.TagEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import util.exception.CreateNewJobException;
import util.exception.DeleteJobException;
import util.exception.InputDataValidationException;
import util.exception.JobNotFoundException;
import util.exception.JobCompanyNameExistException;
import util.exception.UnknownPersistenceException;

@Named
@ViewScoped
public class JobManagementManagedBean implements Serializable 
{
    @EJB
    private JobEntitySessionBeanLocal jobEntitySessionBeanLocal;
    @EJB
    private PositionTypeEntitySessionBeanLocal positionTypeEntitySessionBeanLocal;
    @EJB
    private IndustryEntitySessionBeanLocal industryEntitySessionBeanLocal;
    @EJB
    private JobFunctionEntitySessionBeanLocal jobFunctionEntitySessionBeanLocal;
    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;

    @Inject
    private ViewJobManagedBean viewJobManagedBean;

    private List<JobEntity> jobEntities;
    private List<JobEntity> filteredJobEntities;

    private JobEntity selectedJobEntity;
    private Long positionTypeIdNew;
    private Long industryIdNew;
    private Long jobFunctionIdNew;
    private List<Long> tagIdsNew;
    private List<PositionTypeEntity> positionTypeEntities;
    private List<IndustryEntity> industryEntities;
    private List<JobFunctionEntity> jobFunctionEntities;
    private List<TagEntity> tagEntities;

    private JobEntity selectedJobEntityToUpdate;
    private Long positionTypeIdUpdate;
    private Long industryIdUpdate;
    private Long jobFunctionIdUpdate;
    private List<Long> tagIdsUpdate;
    
    private Application newApplication;

    public JobManagementManagedBean() 
    {
        selectedJobEntity = new JobEntity();
    }

    @PostConstruct
    public void postConstruct() 
    {
        jobEntities = jobEntitySessionBeanLocal.retrieveAllJobs();
        positionTypeEntities = positionTypeEntitySessionBeanLocal.retrieveAllLeafPositionTypes();
        industryEntities = industryEntitySessionBeanLocal.retrieveAllLeafIndustries();
        jobFunctionEntities = jobFunctionEntitySessionBeanLocal.retrieveAllLeafJobFunctions();
        tagEntities = tagEntitySessionBeanLocal.retrieveAllTags();
    }

    public void viewJobDetails(ActionEvent event) throws IOException 
    {
        Long jobIdToView = (Long) event.getComponent().getAttributes().get("jobId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("jobIdToView", jobIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewJobDetails.xhtml");
    }

    public void createNewJob(ActionEvent event) 
    {
        if (positionTypeIdNew == 0) 
        {
            positionTypeIdNew = null;
        }
        if (industryIdNew == 0) 
        {
            industryIdNew = null;
        }
        if (jobFunctionIdNew == 0) 
        {
            jobFunctionIdNew = null;
        }

        try 
        {
            JobEntity je = jobEntitySessionBeanLocal.createNewJob(selectedJobEntity, positionTypeIdNew, industryIdNew, jobFunctionIdNew, tagIdsNew);
            jobEntities.add(je);

            if (filteredJobEntities != null) 
            {
                filteredJobEntities.add(je);
            }

            selectedJobEntity = new JobEntity();
            positionTypeIdNew = null;
            industryIdNew = null;
            jobFunctionIdNew = null;
            tagIdsNew = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New job created successfully (Job ID: " + je.getJobId() + ")", null));
        } 
        catch (InputDataValidationException | CreateNewJobException | JobCompanyNameExistException | UnknownPersistenceException ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new job: " + ex.getMessage(), null));
        }
    }

    public void doUpdateJob(ActionEvent event) 
    {
        selectedJobEntityToUpdate = (JobEntity) event.getComponent().getAttributes().get("jobEntityToUpdate");

        positionTypeIdUpdate = selectedJobEntityToUpdate.getPositionTypeEntity().getPositionTypeId();
        industryIdUpdate = selectedJobEntityToUpdate.getIndustryEntity().getIndustryId();
        jobFunctionIdUpdate = selectedJobEntityToUpdate.getJobFunctionEntity().getJobFunctionId();
        tagIdsUpdate = new ArrayList<>();

        for (TagEntity tagEntity : selectedJobEntityToUpdate.getTagEntities()) 
        {
            tagIdsUpdate.add(tagEntity.getTagId());
        }
    }

    public void updateJob(ActionEvent event) 
    {
        if (positionTypeIdUpdate == 0) 
        {
            positionTypeIdUpdate = null;
        }
        if (industryIdUpdate == 0) 
        {
            industryIdUpdate = null;
        }
        if (jobFunctionIdUpdate == 0) 
        {
            jobFunctionIdUpdate = null;
        }

        try 
        {
            jobEntitySessionBeanLocal.updateJob(selectedJobEntityToUpdate, positionTypeIdUpdate, industryIdUpdate, jobFunctionIdUpdate, tagIdsUpdate);

            for (PositionTypeEntity pte : positionTypeEntities) 
            {
                if (pte.getPositionTypeId().equals(positionTypeIdUpdate)) 
                {
                    selectedJobEntityToUpdate.setPositionTypeEntity(pte);
                    break;
                }
            }

            for (IndustryEntity ie : industryEntities) 
            {
                if (ie.getIndustryId().equals(industryIdUpdate)) 
                {
                    selectedJobEntityToUpdate.setIndustryEntity(ie);
                    break;
                }
            }

            for (JobFunctionEntity jfe : jobFunctionEntities) 
            {
                if (jfe.getJobFunctionId().equals(jobFunctionIdUpdate)) 
                {
                    selectedJobEntityToUpdate.setJobFunctionEntity(jfe);
                    break;
                }
            }

            selectedJobEntityToUpdate.getTagEntities().clear();

            for (TagEntity te : tagEntities) 
            {
                if (tagIdsUpdate.contains(te.getTagId())) 
                {
                    selectedJobEntityToUpdate.getTagEntities().add(te);
                }
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Job updated successfully", null));
        } 
        catch (JobNotFoundException ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating job: " + ex.getMessage(), null));
        } 
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    public void deleteJob(ActionEvent event) 
    {
        try 
        {
            JobEntity jobEntityToDelete = (JobEntity) event.getComponent().getAttributes().get("jobEntityToDelete");
            jobEntitySessionBeanLocal.deleteJob(jobEntityToDelete.getJobId());

            jobEntities.remove(jobEntityToDelete);

            if (filteredJobEntities != null) 
            {
                filteredJobEntities.remove(jobEntityToDelete);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Job deleted successfully", null));
        } 
        catch (JobNotFoundException | DeleteJobException ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting job: " + ex.getMessage(), null));
        } 
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
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

    public List<JobEntity> getJobEntities() 
    {
        return jobEntities;
    }

    public void setJobEntities(List<JobEntity> jobEntities) 
    {
        this.jobEntities = jobEntities;
    }

    public List<JobEntity> getFilteredJobEntities() 
    {
        return filteredJobEntities;
    }

    public void setFilteredJobEntities(List<JobEntity> filteredJobEntities) 
    {
        this.filteredJobEntities = filteredJobEntities;
    }

    public JobEntity getSelectedJobEntity() 
    {
        return selectedJobEntity;
    }

    public void setSelectedJobEntity(JobEntity selectedJobEntity) 
    {
        this.selectedJobEntity = selectedJobEntity;
    }

    public Long getPositionTypeIdNew() 
    {
        return positionTypeIdNew;
    }

    public void setPositionTypeIdNew(Long positionTypeIdNew) 
    {
        this.positionTypeIdNew = positionTypeIdNew;
    }

    public Long getIndustryIdNew() 
    {
        return industryIdNew;
    }

    public void setIndustryIdNew(Long industryIdNew) 
    {
        this.industryIdNew = industryIdNew;
    }

    public Long getJobfunctionIdNew() 
    {
        return jobFunctionIdNew;
    }

    public void setJobFunctionIdNew(Long jobFunctionIdNew) 
    {
        this.jobFunctionIdNew = jobFunctionIdNew;
    }

    public List<Long> getTagIdsNew() 
    {
        return tagIdsNew;
    }

    public void setTagIdsNew(List<Long> tagIdsNew) 
    {
        this.tagIdsNew = tagIdsNew;
    }

    public List<PositionTypeEntity> getPositionTypeEntities() 
    {
        return positionTypeEntities;
    }

    public void setPositionTypeEntities(List<PositionTypeEntity> positionTypeEntities) 
    {
        this.positionTypeEntities = positionTypeEntities;
    }

    public List<IndustryEntity> getIndustryEntities() 
    {
        return industryEntities;
    }

    public void setIndustryEntities(List<IndustryEntity> industryEntities) 
    {
        this.industryEntities = industryEntities;
    }

    public List<JobFunctionEntity> getJobFunctionEntities() 
    {
        return jobFunctionEntities;
    }

    public void setJobFunctionEntities(List<JobFunctionEntity> jobFunctionEntities) 
    {
        this.jobFunctionEntities = jobFunctionEntities;
    }

    public List<TagEntity> getTagEntities() 
    {
        return tagEntities;
    }

    public void setTagEntities(List<TagEntity> tagEntities) 
    {
        this.tagEntities = tagEntities;
    }

    public JobEntity getSelectedJobEntityToUpdate() 
    {
        return selectedJobEntityToUpdate;
    }

    public void setSelectedJobEntityToUpdate(JobEntity selectedJobEntityToUpdate) 
    {
        this.selectedJobEntityToUpdate = selectedJobEntityToUpdate;
    }

    public Long getPositionTypeIdUpdate() 
    {
        return positionTypeIdUpdate;
    }

    public void setPositionTypeIdUpdate(Long positionTypeIdUpdate) 
    {
        this.positionTypeIdUpdate = positionTypeIdUpdate;
    }

    public Long getIndustryIdUpdate() 
    {
        return industryIdUpdate;
    }

    public void setIndustryIdUpdate(Long industryIdUpdate) 
    {
        this.industryIdUpdate = industryIdUpdate;
    }

    public Long getJobFunctionIdUpdate() 
    {
        return jobFunctionIdUpdate;
    }

    public void setJobFunctionIdUpdate(Long jobFunctionIdUpdate) 
    {
        this.jobFunctionIdUpdate = jobFunctionIdUpdate;
    }

    public List<Long> getTagIdsUpdate() 
    {
        return tagIdsUpdate;
    }

    public void setTagIdsUpdate(List<Long> tagIdsUpdate) 
    {
        this.tagIdsUpdate = tagIdsUpdate;
    }
}
