/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.CompanyEntitySessionBeanLocal;
import ejb.session.stateless.SubscriptionEntitySessionBeanLocal;
import entity.CompanyEntity;
import entity.SubscriptionEntity;
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
import javax.ws.rs.core.Response.Status;
import util.exception.CompanyNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.JobListingNotFoundException;
import util.exception.SubscriptionNotFoundException;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllSubscriptionsRsp;
import ws.restful.model.RetrieveSubscriptionRsp;
import ws.restful.model.UpdateSubscriptionRsp;

/**
 * REST Web Service
 *
 * @author Casse
 */
@Path("Subscription")
public class SubscriptionResource {

    private SessionBeanLookup sessionBeanLookup;
    
    private SubscriptionEntitySessionBeanLocal subscriptionEntitySessionBean;
    private CompanyEntitySessionBeanLocal companyEntitySessionBean;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SubscriptionResource
     */
    public SubscriptionResource() {
        sessionBeanLookup = new SessionBeanLookup();
        
        subscriptionEntitySessionBean = sessionBeanLookup.lookupSubscriptionEntitySessionBeanLocal();
        companyEntitySessionBean = sessionBeanLookup.lookupCompanyEntitySessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of ws.restful.resources.SubscriptionResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllSubscriptions() {
        try
        {
            List<SubscriptionEntity> subscriptions = subscriptionEntitySessionBean.retrieveAllSubscription();
            
            for (SubscriptionEntity subscription : subscriptions) 
            {
                subscription.setCompany(null);
            }
            
            RetrieveAllSubscriptionsRsp retrieveAllSubscriptionsRsp = new RetrieveAllSubscriptionsRsp(subscriptions);

            return Response.status(Status.OK).entity(retrieveAllSubscriptionsRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    
    @Path("retrieveSubscription/{subscriptionId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveSubscription(@QueryParam("email") String email, 
                                        @QueryParam("password") String password,
                                        @PathParam("subscriptionId") Long subscriptionId)
    {
        try 
        {
            CompanyEntity companyEntity = companyEntitySessionBean.companyLogin(email, password);
            System.out.println("********** SubscriptionResource.retrieveSubscription(): Company " + companyEntity.getEmail()+ " login remotely via web service");

            SubscriptionEntity subscriptionEntity = subscriptionEntitySessionBean.retrieveSubscriptionByCompany(companyEntity);
            
            return Response.status(Status.OK).entity(new RetrieveSubscriptionRsp(subscriptionEntity)).build();
        } 
        catch (InvalidLoginCredentialException ex) 
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.UNAUTHORIZED).entity(errorRsp).build();
        } 
        catch (CompanyNotFoundException ex) 
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
     * PUT method for updating or creating an instance of SubscriptionResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putXml(String content) {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response switchSubscriptionPlan(UpdateSubscriptionRsp updateSubscriptionRsp) 
    {
        try 
        {
            subscriptionEntitySessionBean.switchSubscriptionPlan(updateSubscriptionRsp.getSubscriptionEntity());
                    
            return Response.status(Status.OK).build();
        } 
        catch (SubscriptionNotFoundException | CompanyNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("terminateSubscription")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response terminateSubscription(UpdateSubscriptionRsp updateSubscriptionRsp) {
        try 
        {
            subscriptionEntitySessionBean.terminateSubscriptionPlan(updateSubscriptionRsp.getSubscriptionEntity());
                    
            return Response.status(Status.OK).build();
        } 
        catch (SubscriptionNotFoundException | CompanyNotFoundException ex) 
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

}
