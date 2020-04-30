/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import entity.ProfessionalEntity;
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
public class ProfessionalEntitySessionBean implements ProfessionalEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    
    @Override
    public List<ProfessionalEntity> retrieveAllProfessionals()
    {
        Query query = em.createQuery("SELECT p FROM ProfessionalEntity p");
        
        return query.getResultList();
    }
    
}
