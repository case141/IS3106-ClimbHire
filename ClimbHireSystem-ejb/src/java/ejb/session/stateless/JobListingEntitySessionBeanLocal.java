/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CompanyEntity;
import entity.JobListingEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CompanyNotFoundException;
import util.exception.CreateNewJobListingException;
import util.exception.InputDataValidationException;
import util.exception.JobListingExistException;
import util.exception.JobListingNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Casse
 */
@Local
public interface JobListingEntitySessionBeanLocal {

    public JobListingEntity createNewJobListing(JobListingEntity newJobListing, Long companyId) throws UnknownPersistenceException, InputDataValidationException, CreateNewJobListingException, CompanyNotFoundException;

    public List<JobListingEntity> retrieveAllJobListings();

    public JobListingEntity retrieveJobListingById(Long jobListingId) throws JobListingNotFoundException;
    
}
