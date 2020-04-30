/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CompanyEntity;
import entity.SubscriptionEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CompanyEmailExistException;
import util.exception.CompanyNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.SetCompanySubscriptionException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateCompanyException;

/**
 *
 * @author Casse
 */
@Local
public interface CompanyEntitySessionBeanLocal {

    public List<CompanyEntity> retrieveAllCompanies();
    
    public CompanyEntity retrieveCompanyByEmail(String companyEmail) throws CompanyNotFoundException;

    public CompanyEntity retrieveCompanyByCompanyId(Long companyId) throws CompanyNotFoundException;

    public CompanyEntity createNewCompany(CompanyEntity newCompany) throws CompanyEmailExistException, UnknownPersistenceException, InputDataValidationException;

    public void setCompanySubscription(CompanyEntity companyEntity, SubscriptionEntity subscriptionEntity) throws CompanyNotFoundException, SetCompanySubscriptionException;
   
}
