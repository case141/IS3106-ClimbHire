/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.AdminEntitySessionBeanLocal;
import entity.AdminEntity;
import entity.CompanyEntity;
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
import ws.restful.model.RetrieveAllAdmins;
import ws.restful.model.RetrieveAllCompaniesRsp;

/**
 * REST Web Service
 *
 * @author Casse
 */
@Path("Admin")
public class AdminResource {

    AdminEntitySessionBeanLocal adminEntitySessionBean = lookupAdminEntitySessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminResource
     */
    public AdminResource() {
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
            List<AdminEntity> admins = adminEntitySessionBean.retrieveAllAdmins();

            return Response.status(Response.Status.OK).entity(new RetrieveAllAdmins(admins)).build();
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

    private AdminEntitySessionBeanLocal lookupAdminEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (AdminEntitySessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/AdminEntitySessionBean!ejb.session.stateless.AdminEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
