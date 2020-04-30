/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.AdminEntity;
import java.util.List;

/**
 *
 * @author Casse
 */
public class RetrieveAllAdmins {
    
    public List<AdminEntity> admins;

    public RetrieveAllAdmins(){
        
    }
            
    public RetrieveAllAdmins(List<AdminEntity> admins) {
        this.admins = admins;
    }

    public List<AdminEntity> getAdmins() {
        return admins;
    }

    public void setAdmins(List<AdminEntity> admins) {
        this.admins = admins;
    }
    
    
}
