/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllCompaniesRsp;
import ws.restful.model.RetrieveAllSubscriptionsRsp;

/**
 * REST Web Service
 *
 * @author Casse
 */
@Path("Subscription")
public class SubscriptionResource {

    private SubscriptionEntitySessionBeanLocal subscriptionEntitySessionBean = lookupSubscriptionEntitySessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SubscriptionResource
     */
    public SubscriptionResource() {
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
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putXml(String content) {
    }

    private SubscriptionEntitySessionBeanLocal lookupSubscriptionEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (SubscriptionEntitySessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/SubscriptionEntitySessionBean!ejb.session.stateless.SubscriptionEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
