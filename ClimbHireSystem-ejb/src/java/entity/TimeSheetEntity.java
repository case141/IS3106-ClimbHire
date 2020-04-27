/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Casse
 */
@Entity
public class TimeSheetEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeSheetId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfWork;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startTime;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endTime;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date regularHours;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date overTimeHours;
    private Double totalPay;
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true)
    private ProfessionalEntity employee; 

    public TimeSheetEntity() {
    }

    public TimeSheetEntity(Date dateOfWork, Date startTime, Date endTime, Date regularHours, Date overTimeHours, Double totalPay, ProfessionalEntity employee) {
        this.dateOfWork = dateOfWork;
        this.startTime = startTime;
        this.endTime = endTime;
        this.regularHours = regularHours;
        this.overTimeHours = overTimeHours;
        this.totalPay = totalPay;
        this.employee = employee;
    }

    public Long getTimeSheetId() {
        return timeSheetId;
    }

    public void setTimeSheetId(Long timeSheetId) {
        this.timeSheetId = timeSheetId;
    }

    public Date getDateOfWork() {
        return dateOfWork;
    }

    public void setDateOfWork(Date dateOfWork) {
        this.dateOfWork = dateOfWork;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(Date regularHours) {
        this.regularHours = regularHours;
    }

    public Date getOverTimeHours() {
        return overTimeHours;
    }

    public void setOverTimeHours(Date overTimeHours) {
        this.overTimeHours = overTimeHours;
    }

    public Double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    public ProfessionalEntity getEmployee() {
        return employee;
    }

    public void setEmployee(ProfessionalEntity employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timeSheetId != null ? timeSheetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the timeSheetId fields are not set
        if (!(object instanceof TimeSheetEntity)) {
            return false;
        }
        TimeSheetEntity other = (TimeSheetEntity) object;
        if ((this.timeSheetId == null && other.timeSheetId != null) || (this.timeSheetId != null && !this.timeSheetId.equals(other.timeSheetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TimeSheet[ id=" + timeSheetId + " ]";
    }
    
}
