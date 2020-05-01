/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.ApplicationEntity;
import entity.JobListingEntity;
import java.util.List;

/**
 *
 * @author Casse
 */
public class UpdateJobListingRsp {
    private JobListingEntity jobListingEntity;
    private List<Long> applicationIds;

    public UpdateJobListingRsp() {
        
    }
    
    public UpdateJobListingRsp(JobListingEntity jobListingEntity, List<Long> applicationIds) {
        this.jobListingEntity = jobListingEntity;
        this.applicationIds = applicationIds;
    }

    public JobListingEntity getJobListingEntity() {
        return jobListingEntity;
    }

    public void setJobListingEntity(JobListingEntity jobListingEntity) {
        this.jobListingEntity = jobListingEntity;
    }

    public List<Long> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(List<Long> applicationIds) {
        this.applicationIds = applicationIds;
    }
    
}
