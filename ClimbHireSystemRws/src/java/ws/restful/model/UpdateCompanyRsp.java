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
public class UpdateCompanyRsp {
    private CompanyEntity company;
    
    public UpdateCompanyRsp() {
        
    }

    public UpdateCompanyRsp(CompanyEntity company) {
        this.company = company;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
    
}
