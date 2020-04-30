/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.AdminEntitySessionBeanLocal;
import ejb.session.stateless.CompanyEntitySessionBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SessionBeanLookup {
    
    private final String ejbModuleJndiPath;
    
    public SessionBeanLookup()
    {
        ejbModuleJndiPath = "java:global/ClimbHireSystem/ClimbHireSystem-ejb/";
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
    
}