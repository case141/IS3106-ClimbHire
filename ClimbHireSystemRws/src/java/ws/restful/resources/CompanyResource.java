/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.CompanyEntitySessionBeanLocal;
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
import javax.ws.rs.core.Response.Status;
import ws.restful.model.CreateNewCompanyReq;
import ws.restful.model.CreateNewCompanyRsp;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllCompaniesRsp;

/**
 * REST Web Service
 *
 * @author Casse
 */
@Path("Company")
public class CompanyResource {

    @Context
    private UriInfo context;

    private CompanyEntitySessionBeanLocal companyEntitySessionBean = lookupCompanyEntitySessionBeanLocal();
    
    /**
     * Creates a new instance of CompanyResource
     */
    public CompanyResource() {
    }

    /**
     * Retrieves representation of an instance of ws.restful.resources.CompanyResource
     * @return an instance of java.lang.String
     */
    @Path("retrieveAllCompanies")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllCompanies() {
        
        try
        {
            List<CompanyEntity> companies = companyEntitySessionBean.retrieveAllCompanies();

            RetrieveAllCompaniesRsp retrieveAllCompaniesRsp = new RetrieveAllCompaniesRsp(companies);
            
            return Response.status(Status.OK).entity(retrieveAllCompaniesRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of CompanyResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putXml(CreateNewCompanyReq createNewCompanyReq) {
        if(createNewCompanyReq != null)
        {
            try
            {
                Long newCompanyId = companyEntitySessionBean.createNewCompany(createNewCompanyReq.getNewCompany()).getCompanyId();

                CreateNewCompanyRsp createNewCompanyRsp = new CreateNewCompanyRsp(newCompanyId);

                return Response.status(Status.OK).entity(createNewCompanyRsp).build();
            }
            catch(Exception ex)
            {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else
        {
            ErrorRsp errorRsp = new ErrorRsp("Invalid Request");
            
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }

    private CompanyEntitySessionBeanLocal lookupCompanyEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CompanyEntitySessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/CompanyEntitySessionBean!ejb.session.stateless.CompanyEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
