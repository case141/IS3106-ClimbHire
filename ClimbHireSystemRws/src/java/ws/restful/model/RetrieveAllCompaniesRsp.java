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
    
    private List<CompanyEntity> companies;

    public RetrieveAllCompaniesRsp() {
    }

    public RetrieveAllCompaniesRsp(List<CompanyEntity> companies) {
        this.companies = companies;
    }

    public List<CompanyEntity> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyEntity> companies) {
        this.companies = companies;
    }
    
}
