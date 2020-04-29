/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CompanyEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CompanyNotFoundException;

/**
 *
 * @author Casse
 */
@Stateless
public class CompanyEntitySessionBean implements CompanyEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewCompany(CompanyEntity newCompany)
    {
        em.persist(newCompany);
        em.flush();
        
        return newCompany.getCompanyId();
    }
    
    @Override
    public List<CompanyEntity> retrieveAllCompanies()
    {
        Query query = em.createQuery("SELECT c FROM CompanyEntity c");
        
        return query.getResultList();
    }
    
    @Override
    public CompanyEntity retrieveCompanyByEmail(String companyEmail) throws CompanyNotFoundException
    {
        Query query = em.createQuery("SELECT c FROM CompanyEntity c WHERE c.companyName = :inCompanyName");
        query.setParameter("inCompanyName", companyEmail);
        
        try
        {
            return (CompanyEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new CompanyNotFoundException("Company Username " + companyEmail + " does not exist!");
        }
    }
}
