/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.AdminEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import ejb.session.stateless.AdminEntitySessionBeanLocal;

/**
 *
 * @author Casse
 */
@Named(value = "viewAllAdminsManagedBean")
@RequestScoped
public class ViewAllAdminsManagedBean {

    @EJB(name = "AdminSessionBeanLocal")
    private AdminEntitySessionBeanLocal adminSessionBeanLocal;
    
    private List<AdminEntity> admins;

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

    public List<AdminEntity> getAdmins() {
        return admins;
    }

    public void setAdmins(List<AdminEntity> admins) {
        this.admins = admins;
    }
    
}
