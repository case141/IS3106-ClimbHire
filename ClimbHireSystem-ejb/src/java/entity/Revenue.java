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
import javax.persistence.Temporal;

/**
 *
 * @author Casse
 */
@Entity
public class Revenue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revenueId;
    private ArrayList<Payment> paymentRecords;
    private Double totalRevenue;
    private String revenueMonth;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate; //beginning of revenueMonth
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate; //end of revenueMonth

    public Revenue() {
    }

    public Revenue(ArrayList<Payment> paymentRecords, Double totalRevenue, String month, Date startDate, Date endDate) {
        this.paymentRecords = paymentRecords;
        this.totalRevenue = totalRevenue;
        this.revenueMonth = month;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ArrayList<Payment> getPaymentRecords() {
        return paymentRecords;
    }

    public void setPaymentRecords(ArrayList<Payment> paymentRecords) {
        this.paymentRecords = paymentRecords;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getRevenueMonth() {
        return revenueMonth;
    }

    public void setRevenueMonth(String revenueMonth) {
        this.revenueMonth = revenueMonth;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(Long revenueId) {
        this.revenueId = revenueId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (revenueId != null ? revenueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the revenueId fields are not set
        if (!(object instanceof Revenue)) {
            return false;
        }
        Revenue other = (Revenue) object;
        if ((this.revenueId == null && other.revenueId != null) || (this.revenueId != null && !this.revenueId.equals(other.revenueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Revenue[ id=" + revenueId + " ]";
    }
    
}
