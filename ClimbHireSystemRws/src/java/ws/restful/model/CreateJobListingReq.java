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
public class CreateJobListingReq {
    private String email; //company email
    private String password;
    private JobListingEntity jobListingEntity;
    private Long companyId;
    
    public CreateJobListingReq() {
        
    }

    public CreateJobListingReq(String email, String password, JobListingEntity jobListingEntity, Long companyId) {
        this.email = email;
        this.password = password;
        this.jobListingEntity = jobListingEntity;
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JobListingEntity getJobListingEntity() {
        return jobListingEntity;
    }

    public void setJobListingEntity(JobListingEntity jobListingEntity) {
        this.jobListingEntity = jobListingEntity;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    
}
