/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.AdminEntity;
import javafx.event.ActionEvent;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ejb.session.stateless.AdminEntitySessionBeanLocal;

/**
 *
 * @author Casse
 */
@Named(value = "createNewAdminManagedBean")
@RequestScoped
public class CreateNewAdminManagedBean {

    @EJB(name = "AdminEntitySessionBeanLocal")
    private AdminEntitySessionBeanLocal adminEntitySessionBeanLocal;

    private AdminEntity newAdmin;
    
    /**
     * Creates a new instance of CreateNewAdminManagedBean
     */
    public CreateNewAdminManagedBean() 
    {
        newAdmin = new AdminEntity();
    }

    public void createNewAdmin(ActionEvent event)
    {
        Long newAdminId = adminEntitySessionBeanLocal.createNewAdmin(newAdmin);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Admin created successfully: " + newAdminId, null));
    }
    
    public AdminEntity getNewAdmin() 
    {
        return newAdmin;
    }

    public void setNewAdmin(AdminEntity newAdmin) 
    {
        this.newAdmin = newAdmin;
    }
    
}

