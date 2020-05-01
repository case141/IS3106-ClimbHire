/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.AdminEntity;

/**
 *
 * @author rycan
 */
public class AdminLoginRsp {
    private AdminEntity adminEntity;

    public AdminLoginRsp() {
    }

    public AdminLoginRsp(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }
     
    
}
