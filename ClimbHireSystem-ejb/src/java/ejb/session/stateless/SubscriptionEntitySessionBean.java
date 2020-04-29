/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CompanyEntity;
import entity.SubscriptionEntity;
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
public class SubscriptionEntitySessionBean implements SubscriptionEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewSubscription(SubscriptionEntity newSubscription)
    {
        em.persist(newSubscription);
        em.flush();
        
        return newSubscription.getSubscriptionId();
    }
    
    @Override
    public List<SubscriptionEntity> retrieveAllSubscription()
    {
        Query query = em.createQuery("SELECT s FROM SubscriptionEntity s");
        
        return query.getResultList();
    }
    
    @Override
    public SubscriptionEntity retrieveSubscriptionByCompany(CompanyEntity company) throws CompanyNotFoundException
    {
        Query query = em.createQuery("SELECT s FROM SubscriptionEntity s WHERE s.company = :inCompany");
        query.setParameter("inCompany", company);
        
        try
        {
            return (SubscriptionEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new CompanyNotFoundException("Subscription for Company " + company + " does not exist!");
        }
    }
}
