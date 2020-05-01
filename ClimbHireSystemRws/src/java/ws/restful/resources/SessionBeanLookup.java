/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.AdminEntitySessionBeanLocal;
import ejb.session.stateless.CompanyEntitySessionBeanLocal;
import ejb.session.stateless.JobListingEntitySessionBeanLocal;
import ejb.session.stateless.PaymentEntitySessionBeanLocal;
import ejb.session.stateless.ProfessionalEntitySessionBeanLocal;
import ejb.session.stateless.SubscriptionEntitySessionBeanLocal;
import ejb.session.stateless.TimeSheetSessionBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SessionBeanLookup {
    
    public SessionBeanLookup()
    {
    }
    
    public AdminEntitySessionBeanLocal lookupAdminEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (AdminEntitySessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/AdminEntitySessionBean!ejb.session.stateless.AdminEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public CompanyEntitySessionBeanLocal lookupCompanyEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CompanyEntitySessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/CompanyEntitySessionBean!ejb.session.stateless.CompanyEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public SubscriptionEntitySessionBeanLocal lookupSubscriptionEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (SubscriptionEntitySessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/SubscriptionEntitySessionBean!ejb.session.stateless.SubscriptionEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public JobListingEntitySessionBeanLocal lookupJobListingEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (JobListingEntitySessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/JobListingEntitySessionBean!ejb.session.stateless.JobListingEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public PaymentEntitySessionBeanLocal lookupPaymentEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PaymentEntitySessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/PaymentEntitySessionBean!ejb.session.stateless.PaymentEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public ProfessionalEntitySessionBeanLocal lookupProfessionalEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ProfessionalEntitySessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/ProfessionalEntitySessionBean!ejb.session.stateless.ProfessionalEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
     
    public TimeSheetSessionBeanLocal lookupTimeSheetSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (TimeSheetSessionBeanLocal) c.lookup("java:global/ClimbHireSystem/ClimbHireSystem-ejb/TimeSheetSessionBean!ejb.session.stateless.TimeSheetSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}