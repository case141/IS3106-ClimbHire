/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.JobListingEntity;

/**
 *
 * @author Casse
 */
public class CloseJobListingRsp {
    private Long jobListingId;

    public CloseJobListingRsp() {
        
    }
    
    public CloseJobListingRsp(Long jobListingId) {
        this.jobListingId = jobListingId;
    }

    public Long getJobListingId() {
        return jobListingId;
    }

    public void setJobListingId(Long jobListingId) {
        this.jobListingId = jobListingId;
    }
    
    
}
