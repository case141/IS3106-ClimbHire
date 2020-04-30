/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.JobListingEntity;
import java.util.List;

/**
 *
 * @author Casse
 */
public class RetrieveAllJobListingsRsp {
    private List<JobListingEntity> jobListingEntities;

    public RetrieveAllJobListingsRsp() {
        
    }
    
    public RetrieveAllJobListingsRsp(List<JobListingEntity> jobListingEntities) {
        this.jobListingEntities = jobListingEntities;
    }

    public List<JobListingEntity> getJobListingEntities() {
        return jobListingEntities;
    }

    public void setJobListingEntities(List<JobListingEntity> jobListingEntities) {
        this.jobListingEntities = jobListingEntities;
    }
    
    
}
