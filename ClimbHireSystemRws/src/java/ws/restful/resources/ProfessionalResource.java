/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.ProfessionalEntitySessionBeanLocal;
import entity.ProfessionalEntity;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import util.exception.ProfessionalNotFoundException;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllProfessionalsRsp;
import ws.restful.model.RetrieveProfessionalRsp;

/**
 * REST Web Service
 *
 * @author rycan
 */
@Path("Professional")
public class ProfessionalResource {

    

    @Context
    private UriInfo context;

    private final SessionBeanLookup sessionBeanLookup;
    
    private final ProfessionalEntitySessionBeanLocal professionalEntitySessionBeanLocal;
     
    /**
     * Creates a new instance of ProfessionalResource
     */
    public ProfessionalResource() {
        
        sessionBeanLookup = new SessionBeanLookup();
        
        professionalEntitySessionBeanLocal = sessionBeanLookup.lookupProfessionalEntitySessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of ws.restful.resources.ProfessionalResource
     * @return an instance of java.lang.String
     */
    @Path("retrieveAllProfessionals")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllProfessionals() {
        try
        {
            List<ProfessionalEntity> professionals = professionalEntitySessionBeanLocal.retrieveAllProfessionals();
            
            for(ProfessionalEntity professional : professionals) 
            {
                professional.setTimeSheets(null);
                professional.setJobsApplied(null);
                professional.setCompany(null);
               
            }
            
            RetrieveAllProfessionalsRsp retrieveAllProfessionalsRsp = new RetrieveAllProfessionalsRsp(professionals);

            return Response.status(Response.Status.OK).entity(retrieveAllProfessionalsRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    /*
    @QueryParam("email") String email, 
                                        @QueryParam("password") String password,
    */
    @Path("retrieveProfessional/{professionalId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveProfessional(@PathParam("professionalId") Long professionalId)
    {
        try
        {
            //StaffEntity staffEntity = staffEntitySessionBeanLocal.staffLogin(username, password);
            //System.out.println("********** ProductResource.retrieveProduct(): Staff " + staffEntity.getUsername() + " login remotely via web service");

            ProfessionalEntity professional = professionalEntitySessionBeanLocal.retrieveProfessionalById(professionalId);
            professional.setTimeSheets(null);
            professional.setJobsApplied(null);
            professional.setCompany(null);
            /*
            if(professionalEntity.getCategoryEntity().getParentCategoryEntity() != null)
            {
                productEntity.getCategoryEntity().getParentCategoryEntity().getSubCategoryEntities().clear();
            }

            productEntity.getCategoryEntity().getProductEntities().clear();

            for(TagEntity tagEntity:productEntity.getTagEntities())
            {
                tagEntity.getProductEntities().clear();
            }
            */
                   
            return Response.status(Status.OK).entity(new RetrieveProfessionalRsp(professional)).build();
        }
        /*
        catch(InvalidLoginCredentialException ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.UNAUTHORIZED).entity(errorRsp).build();
        }
        */
        catch(ProfessionalNotFoundException ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of ProfessionalResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

   
}
