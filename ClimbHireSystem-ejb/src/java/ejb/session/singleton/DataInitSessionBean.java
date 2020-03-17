/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.AdminSessionBeanLocal;
import entity.Admin;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Casse
 */
@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB(name = "AdminSessionBeanLocal")
    private AdminSessionBeanLocal adminSessionBeanLocal;

    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PostConstruct
    public void postConstruct()
    {
        if(em.find(Admin.class, 111l) == null)
        {
            adminSessionBeanLocal.createNewAdmin(new Admin("Admin One", "password", "adminone@gmail.com"));
            adminSessionBeanLocal.createNewAdmin(new Admin("Admin Two", "password", "admintwo@gmail.com"));
        }
    }
}
