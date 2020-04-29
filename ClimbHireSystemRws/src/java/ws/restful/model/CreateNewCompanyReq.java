/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.CompanyEntity;

/**
 *
 * @author Casse
 */
public class CreateNewCompanyReq {
    
    private CompanyEntity newCompany;

    public CreateNewCompanyReq() {
        
    }
    
    public CreateNewCompanyReq(CompanyEntity newCompany) {
        this.newCompany = newCompany;
    }

    public CompanyEntity getNewCompany() {
        return newCompany;
    }

    public void setNewCompany(CompanyEntity newCompany) {
        this.newCompany = newCompany;
    }
    
    
}
