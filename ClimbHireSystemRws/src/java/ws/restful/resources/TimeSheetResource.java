/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.TimeSheetSessionBeanLocal;
import entity.TimeSheetEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllTimeSheetsRsp;

/**
 * REST Web Service
 *
 * @author rycan
 */
@Path("TimeSheet")
public class TimeSheetResource {

    @Context
    private UriInfo context;
    
    private final SessionBeanLookup sessionBeanLookup;
    
    private final TimeSheetSessionBeanLocal timeSheetSessionBeanLocal;
    
    

    /**
     * Creates a new instance of TimeSheetResource
     */
    public TimeSheetResource() {
        
        sessionBeanLookup = new SessionBeanLookup();
        timeSheetSessionBeanLocal = sessionBeanLookup.lookupTimeSheetSessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of ws.restful.resources.TimeSheetResource
     * @return an instance of java.lang.String
     */
    @Path("retrieveAllTimeSheets")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllTimeSheets() {
        try
        {
            List<TimeSheetEntity> timeSheets = timeSheetSessionBeanLocal.retrieveAllTimeSheets();
            
            for(TimeSheetEntity timeSheet : timeSheets) 
            {
               timeSheet.setEmployee(null);
                          
            }
            
            RetrieveAllTimeSheetsRsp retrieveAllTimeSheetsRsp = new RetrieveAllTimeSheetsRsp(timeSheets);

            return Response.status(Response.Status.OK).entity(retrieveAllTimeSheetsRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of TimeSheetResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    
}
