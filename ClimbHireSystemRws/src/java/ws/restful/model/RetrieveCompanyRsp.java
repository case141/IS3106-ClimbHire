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
public class RetrieveCompanyRsp {
    
    private CompanyEntity companyEntity;
    
    public RetrieveCompanyRsp() {
        
    }

    public RetrieveCompanyRsp(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }
    
}
