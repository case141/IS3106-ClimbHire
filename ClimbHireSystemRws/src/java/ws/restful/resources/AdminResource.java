/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.AdminEntitySessionBeanLocal;
import entity.AdminEntity;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.exception.InvalidLoginCredentialException;
import ws.restful.model.AdminLoginRsp;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllAdminsRsp;

/**
 * REST Web Service
 *
 * @author Casse
 */
@Path("Admin")
public class AdminResource {
    
    @Context
    private UriInfo context;
       
    private final SessionBeanLookup sessionBeanLookup;
    
    private final AdminEntitySessionBeanLocal adminEntitySessionBeanLocal; 

    /**
     * Creates a new instance of AdminResource
     */
    public AdminResource() {
        
        sessionBeanLookup = new SessionBeanLookup();
        
        adminEntitySessionBeanLocal = sessionBeanLookup.lookupAdminEntitySessionBeanLocal();
        
    }
    
    @Path("adminLogin")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response companyLogin(@QueryParam("email") String email, 
                                @QueryParam("password") String password)
    {
        try
        {
            AdminEntity adminEntity = adminEntitySessionBeanLocal.adminLogin(email, password);
            System.out.println("********** AdminResource.adminLogin(): Admin " + adminEntity.getEmail()+ " login remotely via web service");
            adminEntity.setPassword(null);
            adminEntity.setSalt(null);

            return Response.status(Response.Status.OK).entity(new AdminLoginRsp(adminEntity)).build();
        }
        catch(InvalidLoginCredentialException ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.UNAUTHORIZED).entity(errorRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * Retrieves representation of an instance of ws.restful.resources.AdminResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllAdmin() {
        try
        {
            List<AdminEntity> admins = adminEntitySessionBeanLocal.retrieveAllAdmins();

            return Response.status(Response.Status.OK).entity(new RetrieveAllAdminsRsp(admins)).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of AdminResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putXml(String content) {
    }
  
}
