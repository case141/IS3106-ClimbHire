/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import entity.CompanyEntity;
import entity.ProfessionalEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CompanyNotFoundException;
import util.exception.ProfessionalNotFoundException;

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
    
    
    @Override
    public ProfessionalEntity retrieveProfessionalByEmail(String professionalEmail) throws ProfessionalNotFoundException
    {
        Query query = em.createQuery("SELECT p FROM ProfessionalEntity p WHERE p.email = :inProfessionalEmail");
        query.setParameter("inProfessionalEmail", professionalEmail);
        
        try
        {
            return (ProfessionalEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new ProfessionalNotFoundException("Professional Email " + professionalEmail + " does not exist!");
        }
    }
    
    
}
