/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import entity.JobListingEntity;
import entity.PaymentEntity;
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
public class PaymentEntitySessionBean implements PaymentEntitySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    
    @Override
    public PaymentEntity createNewPayment(PaymentEntity newPayment)
    {
        em.persist(newPayment);
        em.flush();
        
        return newPayment;
    }
    
    @Override
    public List<PaymentEntity> retrieveAllPayments()
    {
        Query query = em.createQuery("SELECT p FROM PaymentEntity p");
        
        return query.getResultList();
    }
}
