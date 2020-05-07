/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import util.enumeration.UserTypeEnum;

/**
 *
 * @author Casse
 */
@Entity
public class ProfessionalEntity implements Serializable { //Professional for A ProfessionalEntity Person

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Size(max = 64)
    @Email
    private String email; //no 2 accounts with the same emails
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
    
    private UserTypeEnum userTypeEnum;
    //private AccessRightEnum accessRightEnum; //EMPLOYEE or CANDIDATE
    
    @OneToMany(mappedBy = "createdBy")
    private List<ApplicationEntity> jobsApplied;
    
    @OneToMany(mappedBy = "employee")
    private List<TimeSheetEntity> timeSheets;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CompanyEntity company;
    
    
    
    

    public ProfessionalEntity() {
        previousWorkExperiences = new ArrayList<>();
        skills = new ArrayList();
        jobsApplied = new ArrayList<>();
        timeSheets = new ArrayList<>();
        userTypeEnum = UserTypeEnum.CANDIDATE;
    }

    public ProfessionalEntity(String password, String firstName, String lastName, String address, String email, char gender, Integer contactNumber, Date dateJoined, Date dateOfBirth, UserTypeEnum userTypeEnum) {
        
        this();
        
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.contactNumber = contactNumber;
        this.dateJoined = dateJoined;
        this.dateOfBirth = dateOfBirth;
        this.userTypeEnum = userTypeEnum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
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

    public ArrayList<String> getPreviousWorkExperiences() {
        return previousWorkExperiences;
    }

    public void setPreviousWorkExperiences(ArrayList<String> previousWorkExperiences) {
        this.previousWorkExperiences = previousWorkExperiences;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
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

    public List<ApplicationEntity> getJobsApplied() {
        return jobsApplied;
    }

    public void setJobsApplied(List<ApplicationEntity> jobsApplied) {
        this.jobsApplied = jobsApplied;
    }

    public List<TimeSheetEntity> getTimeSheets() {
        return timeSheets;
    }

    public void setTimeSheets(List<TimeSheetEntity> timeSheets) {
        this.timeSheets = timeSheets;
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
        if (!(object instanceof ProfessionalEntity)) {
            return false;
        }
        ProfessionalEntity other = (ProfessionalEntity) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Candidate[ id=" + userId + " ]";
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
    
}