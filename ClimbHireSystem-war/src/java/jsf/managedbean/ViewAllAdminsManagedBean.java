/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.AdminSessionBeanLocal;
import entity.Admin;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Casse
 */
@Named(value = "viewAllAdminsManagedBean")
@RequestScoped
public class ViewAllAdminsManagedBean {

    @EJB(name = "AdminSessionBeanLocal")
    private AdminSessionBeanLocal adminSessionBeanLocal;
    
    private List<Admin> admins;

    /**
     * Creates a new instance of ViewAllAdminsManagedBean
     */
    public ViewAllAdminsManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct()
    {
        admins = adminSessionBeanLocal.retrieveAllAdmins();
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }
    
}
