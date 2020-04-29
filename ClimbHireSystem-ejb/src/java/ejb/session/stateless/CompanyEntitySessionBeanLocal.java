/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CompanyEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CompanyNotFoundException;

/**
 *
 * @author Casse
 */
@Local
public interface CompanyEntitySessionBeanLocal {

    public List<CompanyEntity> retrieveAllCompanies();

    public Long createNewCompany(CompanyEntity newCompany);

    public CompanyEntity retrieveCompanyByEmail(String companyEmail) throws CompanyNotFoundException;
    
}
