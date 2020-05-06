/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.SubscriptionEntitySessionBeanLocal;
import entity.SubscriptionEntity;
import java.util.List;
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
import util.exception.CreateNewSubscriptionException;
import ws.restful.model.CreateNewSubscriptionReq;
import ws.restful.model.CreateNewSubscriptionRsp;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllSubscriptionsRsp;

/**
 * REST Web Service
 *
 * @author Casse
 */
@Path("Subscription")
public class SubscriptionResource {

    private final SessionBeanLookup sessionBeanLookup;
    
    private final SubscriptionEntitySessionBeanLocal subscriptionEntitySessionBeanLocal;

    @Context
    private UriInfo context;
    
    

    /**
     * Creates a new instance of SubscriptionResource
     */
    public SubscriptionResource() {
        sessionBeanLookup = new SessionBeanLookup();
        
        subscriptionEntitySessionBeanLocal = sessionBeanLookup.lookupSubscriptionEntitySessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of ws.restful.resources.SubscriptionResource
     * @return an instance of java.lang.String
     */
    @Path("retrieveAllSubscriptions")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllSubscriptions() {
        try
        {
            List<SubscriptionEntity> subscriptions = subscriptionEntitySessionBeanLocal.retrieveAllSubscription();
            
            for (SubscriptionEntity subscription : subscriptions) 
            {
                subscription.setCompany(null);            
            }
            
            RetrieveAllSubscriptionsRsp retrieveAllSubscriptionsRsp = new RetrieveAllSubscriptionsRsp(subscriptions);

            return Response.status(Response.Status.OK).entity(retrieveAllSubscriptionsRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of SubscriptionResource
     * @param content representation for the resource
     */
    @Path("createNewSubscription")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewSubscription(CreateNewSubscriptionReq createNewSubscriptionReq) 
    {
        if(createNewSubscriptionReq != null){
            try 
            {    
                SubscriptionEntity subscriptionEntity  = subscriptionEntitySessionBeanLocal.createNewSubscription(createNewSubscriptionReq.getSubscriptionEntity(), createNewSubscriptionReq.getCompanyEntity().getCompanyId());
                CreateNewSubscriptionRsp createNewSubscriptionRsp = new CreateNewSubscriptionRsp(subscriptionEntity.getSubscriptionId());
                
                return Response.status(Response.Status.OK).entity(createNewSubscriptionRsp).build();
                
         
            } catch (CreateNewSubscriptionException ex) 
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
        else
        {
            ErrorRsp errorRsp = new ErrorRsp("Invalid create new job listing request");
            
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }

}
