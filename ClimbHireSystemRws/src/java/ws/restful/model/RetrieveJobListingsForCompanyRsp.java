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
public class RetrieveJobListingsForCompanyRsp {
    private List<JobListingEntity> jobListingEntities;

    public RetrieveJobListingsForCompanyRsp() {
        
    }
    
    public RetrieveJobListingsForCompanyRsp(List<JobListingEntity> jobListingEntities) {
        this.jobListingEntities = jobListingEntities;
    }

    public List<JobListingEntity> getJobListingEntities() {
        return jobListingEntities;
    }

    public void setJobListingEntities(List<JobListingEntity> jobListingEntities) {
        this.jobListingEntities = jobListingEntities;
    }
}
