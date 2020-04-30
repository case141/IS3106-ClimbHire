/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.JobListingEntitySessionBeanLocal;
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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    
    
    /**
     * Creates a new instance of JobListingResource
     */
    public JobListingResource() {
        sessionBeanLookup = new SessionBeanLookup();
        
        jobListingEntitySessionBean = sessionBeanLookup.lookupJobListingEntitySessionBeanLocal();
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
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putXml(String content) {
    }
    
}
