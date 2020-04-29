/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AdminNotFoundException;

/**
 *
 * @author Casse
 */
@Stateless
public class AdminEntitySessionBean implements AdminEntitySessionBeanLocal {

    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public Long createNewAdmin(AdminEntity newAdmin)
    {
        em.persist(newAdmin);
        em.flush();
        
        return newAdmin.getAdminId();
    }
    
    @Override
    public List<AdminEntity> retrieveAllAdmins()
    {
        Query query = em.createQuery("SELECT a FROM AdminEntity a");
        
        return query.getResultList();
    }
    
    @Override
    public AdminEntity retrieveAdminByName(String adminName) throws AdminNotFoundException
    {
        Query query = em.createQuery("SELECT a FROM AdminEntity a WHERE a.adminName = :inAdminName");
        query.setParameter("inAdminName", adminName);
        
        try
        {
            return (AdminEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new AdminNotFoundException("Admin Username " + adminName + " does not exist!");
        }
    }
}
