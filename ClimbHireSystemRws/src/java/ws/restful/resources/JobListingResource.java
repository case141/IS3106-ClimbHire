/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.CompanyEntitySessionBeanLocal;
import ejb.session.stateless.JobListingEntitySessionBeanLocal;
import entity.CompanyEntity;
import entity.JobListingEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.exception.CompanyNotFoundException;
import util.exception.CreateNewJobListingException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.JobListingExistException;
import util.exception.UnknownPersistenceException;
import ws.restful.model.CreateJobListingReq;
import ws.restful.model.CreateJobListingRsp;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllCompaniesRsp;
import ws.restful.model.RetrieveAllJobListingsRsp;

/**
 * REST Web Service
 *
 * @author Casse
 */
@Path("JobListing")
public class JobListingResource {

    @Context
    private UriInfo context;
    
    private final SessionBeanLookup sessionBeanLookup;

    private final JobListingEntitySessionBeanLocal jobListingEntitySessionBean;
    private final CompanyEntitySessionBeanLocal companyEntitySessionBean;
    
    
    /**
     * Creates a new instance of JobListingResource
     */
    public JobListingResource() {
        sessionBeanLookup = new SessionBeanLookup();
        
        jobListingEntitySessionBean = sessionBeanLookup.lookupJobListingEntitySessionBeanLocal();
        companyEntitySessionBean = sessionBeanLookup.lookupCompanyEntitySessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of ws.restful.resources.JobListingResource
     * @return an instance of java.lang.String
     */
    @Path("retrieveAllJobListings")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllJobListings() {
        try
        {
            List<JobListingEntity> jobListings = jobListingEntitySessionBean.retrieveAllJobListings();
            
            for (JobListingEntity jobListing : jobListings) 
            {
                jobListing.setApplicationList(null);
                jobListing.setCompany(null);
            }
            
            RetrieveAllJobListingsRsp retrieveAllJobListingRsp = new RetrieveAllJobListingsRsp(jobListings);

            return Response.status(Response.Status.OK).entity(retrieveAllJobListingRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of JobListingResource
     * @param content representation for the resource
     */
    @Path("createJobListing")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobListing(CreateJobListingReq createJobListingReq) {
        if(createJobListingReq != null){
            try {
                    
                CompanyEntity companyEntity = companyEntitySessionBean.companyLogin(createJobListingReq.getEmail(), createJobListingReq.getPassword());
                
                JobListingEntity jobListingEntity  = jobListingEntitySessionBean.createNewJobListing(createJobListingReq.getJobListingEntity(), createJobListingReq.getCompanyId());
                CreateJobListingRsp createJobListingRsp = new CreateJobListingRsp(jobListingEntity.getJobListingId());
                
                return Response.status(Response.Status.OK).entity(createJobListingRsp).build();
                
            } catch (InvalidLoginCredentialException ex) {
                
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
                return Response.status(Response.Status.UNAUTHORIZED).entity(errorRsp).build();
                
            } catch (CreateNewJobListingException ex) {
                
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                
                return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
            }
            catch(Exception ex)
            {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else
        {
            ErrorRsp errorRsp = new ErrorRsp("Invalid create new job listing request");
            
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
    
    
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response closeJobListing(){
//        
//    }
    
}
