/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("Resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.restful.resources.AdminResource.class);
        resources.add(ws.restful.resources.CompanyResource.class);
        resources.add(ws.restful.resources.JobListingResource.class);
        resources.add(ws.restful.resources.PaymentResource.class);
        resources.add(ws.restful.resources.ProfessionalResource.class);
        resources.add(ws.restful.resources.SubscriptionResource.class);
        resources.add(ws.restful.resources.TimeSheetResource.class);
    }
}
