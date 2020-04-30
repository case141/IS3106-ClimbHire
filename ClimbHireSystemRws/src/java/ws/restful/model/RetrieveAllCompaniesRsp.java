/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.CompanyEntity;
import java.util.List;

/**
 *
 * @author Casse
 */
public class RetrieveAllCompaniesRsp {
    
    public List<CompanyEntity> companies;

    public RetrieveAllCompaniesRsp(){
        
    }
    
    public RetrieveAllCompaniesRsp(List<CompanyEntity> companies) {
        this.companies = companies;
    }

    public List<CompanyEntity> getCompanyEntity() {
        return companies;
    }

    public void setCompanyEntity(List<CompanyEntity> companies) {
        this.companies = companies;
    }
    
}
