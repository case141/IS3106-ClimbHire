/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.AdminSessionBeanLocal;
import entity.Admin;
import javafx.event.ActionEvent;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Casse
 */
@Named(value = "createNewAdminManagedBean")
@RequestScoped
public class CreateNewAdminManagedBean {

    @EJB(name = "AdminSessionBeanLocal")
    private AdminSessionBeanLocal adminSessionBeanLocal;

    private Admin newAdmin;
    
    /**
     * Creates a new instance of CreateNewAdminManagedBean
     */
    public CreateNewAdminManagedBean() 
    {
        
        newAdmin = new Admin();
    }

    public void createNewAdmin(ActionEvent event)
    {
        Long newAdminId = adminSessionBeanLocal.createNewAdmin(newAdmin);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Admin created successfully: " + newAdminId, null));
    }
    
    public Admin getNewAdmin() 
    {
        return newAdmin;
    }

    public void setNewAdmin(Admin newAdmin) 
    {
        this.newAdmin = newAdmin;
    }
    
}
