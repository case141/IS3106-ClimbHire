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
public class Professional implements Serializable { //Professional for A Professional Person

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private char gender;
    private Integer contactNumber;
    private String profilePicture;
    private ArrayList<String> previousWorkExperiences;
    private ArrayList<String> skills; //auto-matched with qualifications in JobListing
    private String qualifications;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateJoined;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    //private AccessRightEnum accessRightEnum; //EMPLOYEE or CANDIDATE
    //private ArrayList<JobListing> jobsApplied; //nullable
    //private ArrayList<TimeSheet> timeSheet; //nullable

    public Professional() {
    }

    public Professional(String password, String firstName, String lastName, String address, String email, Integer contactNumber, Date dateJoined, Date dateOfBirth) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.contactNumber = contactNumber;
        this.dateJoined = dateJoined;
        this.dateOfBirth = dateOfBirth;
    }


    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public ArrayList<String> getPreviousWorkExperiences() {
        return previousWorkExperiences;
    }

    public void setPreviousWorkExperiences(ArrayList<String> previousWorkExperiences) {
        this.previousWorkExperiences = previousWorkExperiences;
    }
    
    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userId fields are not set
        if (!(object instanceof Professional)) {
            return false;
        }
        Professional other = (Professional) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Candidate[ id=" + userId + " ]";
    }
    
}
