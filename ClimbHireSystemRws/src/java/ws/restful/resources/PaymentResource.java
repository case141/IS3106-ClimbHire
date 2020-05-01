/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

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
import util.exception.CreateNewPaymentRecordException;
import ws.restful.model.CreateNewPaymentRecordReq;
import ws.restful.model.CreateNewPaymentRecordRsp;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllPaymentsRsp;

/**
 * REST Web Service
 *
 * @author rycan
 */
@Path("Payment")
public class PaymentResource {
    
    private final SessionBeanLookup sessionBeanLookup;
    private final PaymentEntitySessionBeanLocal paymentEntitySessionBean;

    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of PaymentResource
     */
    public PaymentResource() {
        sessionBeanLookup = new SessionBeanLookup();
        
        paymentEntitySessionBean = sessionBeanLookup.lookupPaymentEntitySessionBeanLocal();       
    }

    /**
     * Retrieves representation of an instance of ws.restful.resources.PaymentResource
     * @return an instance of java.lang.String
     */
    @Path("retrieveAllPaymentRecords")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPaymentRecords() {
        try
        {
            List<PaymentEntity> paymentRecords = paymentEntitySessionBean.retrieveAllPaymentRecords();
            
            for(PaymentEntity payment : paymentRecords) 
            {
                payment.setCompany(null);
            }
            
            RetrieveAllPaymentsRsp retrieveAllSubscriptionsRsp = new RetrieveAllPaymentsRsp(paymentRecords);

            return Response.status(Response.Status.OK).entity(retrieveAllSubscriptionsRsp).build();
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
    @Path("createPaymentRecord")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPaymentRecord(CreateNewPaymentRecordReq createPaymentRecordReq) {
        if(createPaymentRecordReq != null){
            try 
            {    
                PaymentEntity paymentEntity  = paymentEntitySessionBean.createNewPayment(createPaymentRecordReq.getPaymentEntity(), createPaymentRecordReq.getCompanyId());
                CreateNewPaymentRecordRsp createPaymentRecordRsp = new CreateNewPaymentRecordRsp(paymentEntity.getPaymentId());
                
                return Response.status(Response.Status.OK).entity(createPaymentRecordRsp).build();
                
         
            } catch (CreateNewPaymentRecordException ex) 
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
