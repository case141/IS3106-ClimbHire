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
public class RetrieveAllAdminsRsp {
    
    public List<AdminEntity> admins;

    public RetrieveAllAdminsRsp(){
        
    }
            
    public RetrieveAllAdminsRsp(List<AdminEntity> admins) {
        this.admins = admins;
    }

    public List<AdminEntity> getAdmins() {
        return admins;
    }

    public void setAdmins(List<AdminEntity> admins) {
        this.admins = admins;
    }
    
    
}
