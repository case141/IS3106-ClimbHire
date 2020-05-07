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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.exception.CompanyNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UpdateCompanyException;
import ws.restful.model.CompanyLoginRsp;
import ws.restful.model.CreateNewCompanyReq;
import ws.restful.model.CreateNewCompanyRsp;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllCompaniesRsp;
import ws.restful.model.RetrieveCompanyRsp;
import ws.restful.model.UpdateCompanyRsp;

/**
 * REST Web Service
 *
 * @author Casse
 */
@Path("Company")
public class CompanyResource {

    @Context
    private UriInfo context;
    
    private final SessionBeanLookup sessionBeanLookup;
            
    private CompanyEntitySessionBeanLocal companyEntitySessionBeanLocal;

    /**
     * Creates a new instance of CompanyResource
     */
    public CompanyResource() {
        
        sessionBeanLookup = new SessionBeanLookup();
        
        companyEntitySessionBeanLocal = sessionBeanLookup.lookupCompanyEntitySessionBeanLocal();
    }

    
    @Path("companyLogin")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response companyLogin(@QueryParam("email") String email, 
                                @QueryParam("password") String password)
    {
        try
        {
            CompanyEntity companyEntity = companyEntitySessionBeanLocal.companyLogin(email, password);
            System.out.println("********** CompanyResource.companyLogin(): Company " + companyEntity.getEmail()+ " login remotely via web service");
            companyEntity.setPassword(null);
            companyEntity.setSalt(null);
            companyEntity.setSubscription(null);
            companyEntity.getPaymentHistory().clear();            
            
            return Response.status(Response.Status.OK).entity(new CompanyLoginRsp(companyEntity)).build();
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
     * Retrieves representation of an instance of ws.restful.resources.CompanyResource
     * @return an instance of java.lang.String
     */
//    @Path("retrieveAllCompanies")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllCompanies() {
        try
        {
            List<CompanyEntity> companies = companyEntitySessionBeanLocal.retrieveAllCompanies();
            
            for (CompanyEntity company : companies) 
            {
                company.setSubscription(null);
                company.getPaymentHistory().clear();
            }
            
            RetrieveAllCompaniesRsp retrieveAllCompaniesRsp = new RetrieveAllCompaniesRsp(companies);

            return Response.status(Response.Status.OK).entity(retrieveAllCompaniesRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    
    @Path("retrieveCompany")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveCompany(@QueryParam("email") String email, 
                                    @QueryParam("password") String password)
    {
        try {
            CompanyEntity companyEntity = companyEntitySessionBeanLocal.companyLogin(email, password);
            System.out.println("********** CompanyResource.retrieveCompany(): Company " + companyEntity.getEmail()+ " login remotely via web service");

            CompanyEntity company = companyEntitySessionBeanLocal.retrieveCompanyByEmail(email);
            company.getPaymentHistory().clear();
            company.setSubscription(null);
            
            return Response.status(Response.Status.OK).entity(new RetrieveCompanyRsp(company)).build();
        } catch (InvalidLoginCredentialException ex) {
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
     * PUT method for updating or creating an instance of CompanyResource
     * @param content representation for the resource
     */
    @Path("createNewCompany")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
     public Response createNewCompany(CreateNewCompanyReq createNewCompanyReq) {
        
        if(createNewCompanyReq != null)
        {
         try
         {           
             Long newCompanyId = companyEntitySessionBeanLocal.createNewCompany(createNewCompanyReq.getNewCompany()).getCompanyId();
             
             CreateNewCompanyRsp createNewCompanyRsp = new CreateNewCompanyRsp(newCompanyId);
             
             return Response.status(Response.Status.OK).entity(createNewCompanyRsp).build();
         }
         catch(Exception ex)
         {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
         }
        }
        else 
        {
            ErrorRsp errorRsp = new ErrorRsp("Invalid request");
            
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
     
    @Path("updateCompanyProfile")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
     public Response updateCompanyProfile(UpdateCompanyRsp updateCompanyRsp)
    {  
         if(updateCompanyRsp != null)
         {                
             try 
             {
                 companyEntitySessionBeanLocal.updateCompanyProfile(updateCompanyRsp.getCompany());
                 
                 return Response.status(Response.Status.OK).build();
             } 
             catch (UpdateCompanyException ex) 
             {
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
            ErrorRsp errorRsp = new ErrorRsp("Invalid update company request");
            
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
         }
    }
}
