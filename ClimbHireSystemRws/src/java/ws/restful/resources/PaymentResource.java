/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.CompanyEntitySessionBeanLocal;
import ejb.session.stateless.PaymentEntitySessionBeanLocal;
import entity.PaymentEntity;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllPaymentRsp;

/**
 * REST Web Service
 *
 * @author Casse
 */
@Path("Payment")
public class PaymentResource {

    @Context
    private UriInfo context;
    
    private final SessionBeanLookup sessionBeanLookup;

    private final PaymentEntitySessionBeanLocal paymentEntitySessionBean;
    private final CompanyEntitySessionBeanLocal companyEntitySessionBean;

    /**
     * Creates a new instance of PaymentResource
     */
    public PaymentResource() {
        sessionBeanLookup = new SessionBeanLookup();
        
        paymentEntitySessionBean = sessionBeanLookup.lookupPaymentEntitySessionBeanLocal();
        companyEntitySessionBean = sessionBeanLookup.lookupCompanyEntitySessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of ws.restful.resources.PaymentResource
     * @return an instance of java.lang.String
     */
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPayment() {
        try
        {
            List<PaymentEntity> paymentHistory = paymentEntitySessionBean.retrieveAllPayments();
            
            for (PaymentEntity payment : paymentHistory) 
            {
                payment.setCompany(null);
            }
            
            RetrieveAllPaymentRsp retrieveAllPaymentsRsp = new RetrieveAllPaymentRsp(paymentHistory);

            return Response.status(Status.OK).entity(retrieveAllPaymentsRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of PaymentResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
