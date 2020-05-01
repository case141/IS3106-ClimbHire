/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import entity.AdminEntity;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.session.stateless.AdminEntitySessionBeanLocal;
import ejb.session.stateless.CompanyEntitySessionBeanLocal;
import ejb.session.stateless.JobListingEntitySessionBeanLocal;
import ejb.session.stateless.PaymentEntitySessionBeanLocal;
import ejb.session.stateless.SubscriptionEntitySessionBeanLocal;
import entity.CompanyEntity;
import entity.JobListingEntity;
import entity.PaymentEntity;
import entity.SubscriptionEntity;
import java.sql.Timestamp;
import java.util.Date;
import util.enumeration.JobListingStatusEnum;
import util.enumeration.PaymentStatusEnum;
import util.enumeration.SubscriptionStatusEnum;
import util.enumeration.SubscriptionTypeEnum;
import util.exception.AdminNotFoundException;

/**
 *
 * @author Casse
 */
@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB(name = "AdminEntitySessionBeanLocal")
    private AdminEntitySessionBeanLocal adminSessionBeanLocal;
    @EJB(name = "CompanyEntitySessionBeanLocal")
    private CompanyEntitySessionBeanLocal companySessionBeanLocal;
    @EJB(name = "SubscriptionEntitySessionBeanLocal")
    private SubscriptionEntitySessionBeanLocal subscriptionSessionBeanLocal;
    @EJB(name = "JobListingEntitySessionBeanLocal")
    private JobListingEntitySessionBeanLocal jobListingEntitySessionBeanLocal;
    @EJB(name = "PaymentEntitySessionBeanLocal")
    private PaymentEntitySessionBeanLocal paymentEntitySessionBeanLocal;

    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PostConstruct
    public void postConstruct()
    {
        try
        {
            adminSessionBeanLocal.retrieveAdminByName("Admin One");
        }
        catch(AdminNotFoundException ex)
        {
            initializeData();
        }
    }
    private void initializeData()
    {
        try
        {
            //create admin
            adminSessionBeanLocal.createNewAdmin(new AdminEntity("Admin One", "password", "adminone@gmail.com"));
            adminSessionBeanLocal.createNewAdmin(new AdminEntity("Admin Two", "password", "admintwo@gmail.com"));

            //create base company
            CompanyEntity baseCompany = companySessionBeanLocal.createNewCompany(new CompanyEntity("Base Company", "password", "basecompany@gmail.com", 91234567, 
                    "We are a software company.", new Date(), new Timestamp(System.currentTimeMillis())));
            
            //create subscription for base company
            SubscriptionEntity newSubscription = subscriptionSessionBeanLocal.createNewSubscription(new SubscriptionEntity(SubscriptionTypeEnum.MONTHLY, "Unlock all features, No Perks", 
                    100.00, SubscriptionStatusEnum.ACTIVE, new Date(), baseCompany));
            companySessionBeanLocal.setCompanySubscription(baseCompany, newSubscription);
            
            jobListingEntitySessionBeanLocal.createNewJobListing(new JobListingEntity("IOS Application Developer", "Clementi Building 1", new Date(), 5000.00, 30.00, 
                    "Designing and building mobile applications for Apple's IOS platform.", "Full Time", JobListingStatusEnum.OPEN, 2), baseCompany.getCompanyId());
        
            PaymentEntity payment = paymentEntitySessionBeanLocal.createNewPayment(new PaymentEntity(100.00, PaymentStatusEnum.PAID, baseCompany, new Date()));
            baseCompany.getPaymentHistory().add(payment);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }    
    }
}
