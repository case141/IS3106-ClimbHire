/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Casse
 */
@Entity
public class JobListing implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobListingId;
    private String jobTitle;
    private String workLocation;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePosted;
    private Double basicMonthlyPay;
    private Double payPerHour;
    private String responsibilities;
    private ArrayList<String> qualifications; //e.g. university, secondary, certifications, etc
    private ArrayList<String> skillsRequired; //e.g. knowledge in Java, etc
    private String contract;
    private String status; //same as vacancy status (i.e. Pending, Closed, etc)
    private Integer numOfPositionAvailable; //number goes down whenever company accepts a candidate
    @ManyToOne(optional=false)
    @JoinColumn(nullable = false)
    private Company company;
    //private ArrayList<Application> applicationList;

    public JobListing() {
    }

    public JobListing(String jobTitle, String workLocation, Date datePosted, Double basicMonthlyPay, Double payPerHour, String responsibilities, String contract, String status, Integer numOfPositionAvailable) {
        this.jobTitle = jobTitle;
        this.workLocation = workLocation;
        this.datePosted = datePosted;
        this.basicMonthlyPay = basicMonthlyPay;
        this.payPerHour = payPerHour;
        this.responsibilities = responsibilities;
        this.contract = contract;
        this.status = status;
        this.numOfPositionAvailable = numOfPositionAvailable;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Double getBasicMonthlyPay() {
        return basicMonthlyPay;
    }

    public void setBasicMonthlyPay(Double basicMonthlyPay) {
        this.basicMonthlyPay = basicMonthlyPay;
    }

    public Double getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(Double payPerHour) {
        this.payPerHour = payPerHour;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumOfPositionAvailable() {
        return numOfPositionAvailable;
    }

    public void setNumOfPositionAvailable(Integer numOfPositionAvailable) {
        this.numOfPositionAvailable = numOfPositionAvailable;
    }

    public Long getJobListingId() {
        return jobListingId;
    }

    public void setJobListingId(Long jobListingId) {
        this.jobListingId = jobListingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobListingId != null ? jobListingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the jobListingId fields are not set
        if (!(object instanceof JobListing)) {
            return false;
        }
        JobListing other = (JobListing) object;
        if ((this.jobListingId == null && other.jobListingId != null) || (this.jobListingId != null && !this.jobListingId.equals(other.jobListingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.JobListing[ id=" + jobListingId + " ]";
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
}
