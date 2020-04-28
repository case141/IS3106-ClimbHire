/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Admin;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Casse
 */
@Stateless
public class AdminSessionBean implements AdminSessionBeanLocal {

    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public Long createNewAdmin(Admin newAdmin)
    {
        em.persist(newAdmin);
        em.flush();
        
        return newAdmin.getAdminId();
    }
    
    @Override
    public List<Admin> retrieveAllAdmins()
    {
        Query query = em.createQuery("SELECT a FROM Admin a");
        
        return query.getResultList();
    }
}
