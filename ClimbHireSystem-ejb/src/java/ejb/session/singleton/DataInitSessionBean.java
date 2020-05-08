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
import ejb.session.stateless.ProfessionalEntitySessionBeanLocal;
import ejb.session.stateless.SubscriptionEntitySessionBeanLocal;
import entity.CompanyEntity;
import entity.JobListingEntity;
import entity.PaymentEntity;
import entity.ProfessionalEntity;
import entity.SubscriptionEntity;
import java.sql.Timestamp;
import java.util.Date;
import util.enumeration.JobListingStatusEnum;
import util.enumeration.PaymentStatusEnum;
import util.enumeration.SubscriptionStatusEnum;
import util.enumeration.SubscriptionTypeEnum;
import util.enumeration.UserTypeEnum;
import util.exception.AdminNotFoundException;
import util.exception.CompanyNotFoundException;

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
    @EJB
    private ProfessionalEntitySessionBeanLocal professionalEntitySessionBeanLocal;

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

            CompanyEntity baseCompany = companySessionBeanLocal.createNewCompany(new CompanyEntity("Base Company", "password", "basecompany@gmail.com", 91234567, 
                    "We are a software company.", new Date(), new Timestamp(System.currentTimeMillis())));
            
            CompanyEntity company2 = companySessionBeanLocal.createNewCompany(new CompanyEntity("Company 2", "password", "company2@gmail.com", 81396788, 
                    "We are company 2.", new Date(), new Timestamp(System.currentTimeMillis())));
            
            CompanyEntity company3 = companySessionBeanLocal.createNewCompany(new CompanyEntity("Company 3", "password", "company3@gmail.com", 813267898, 
                    "We are company 3.", new Date(), new Timestamp(System.currentTimeMillis())));
            
            CompanyEntity company4 = companySessionBeanLocal.createNewCompany(new CompanyEntity("Company 4", "password", "company4@gmail.com", 82679054, 
                    "We are company 4.", new Date(), new Timestamp(System.currentTimeMillis())));
            
            //create subscription for base company
            SubscriptionEntity newSubscription = subscriptionSessionBeanLocal.createNewSubscription(new SubscriptionEntity(SubscriptionTypeEnum.MONTHLY, "Unlock all features, No Perks", 
                    100.00, SubscriptionStatusEnum.ACTIVE, new Date(), baseCompany));
            companySessionBeanLocal.setCompanySubscription(baseCompany, newSubscription);
            
            jobListingEntitySessionBeanLocal.createNewJobListing(new JobListingEntity("IOS Application Developer", "Clementi Building 1", new Date(), 5000.00, 30.00, 
                    "Designing and building mobile applications for Apple's IOS platform.", "Full Time", JobListingStatusEnum.OPEN, 2), baseCompany.getCompanyId());
               
            jobListingEntitySessionBeanLocal.createNewJobListing(new JobListingEntity("Android Developer", "Clementi Building 1", new Date(), 5000.00, 30.00, 
                    "Designing and building mobile applications for Android platform.", "Full Time", JobListingStatusEnum.OPEN, 2), baseCompany.getCompanyId());
               
            jobListingEntitySessionBeanLocal.createNewJobListing(new JobListingEntity("Data Scientist", "Clementi Building 1", new Date(), 5000.00, 30.00, 
                    "To manage and analyze data for the company's projects.", "Full Time", JobListingStatusEnum.OPEN, 2), baseCompany.getCompanyId());
        
            PaymentEntity paymentOne = paymentEntitySessionBeanLocal.createNewPayment(new PaymentEntity(100.00, PaymentStatusEnum.PAID, baseCompany, new Date()));
            baseCompany.getPaymentHistory().add(paymentOne);
            
            PaymentEntity paymentTwo = paymentEntitySessionBeanLocal.createNewPayment(new PaymentEntity(100.00, PaymentStatusEnum.PAID, baseCompany, new Date()));
            baseCompany.getPaymentHistory().add(paymentTwo);
            
            PaymentEntity paymentThree = paymentEntitySessionBeanLocal.createNewPayment(new PaymentEntity(100.00, PaymentStatusEnum.PAID, baseCompany, new Date()));
            baseCompany.getPaymentHistory().add(paymentThree);
            
            PaymentEntity paymentFour = paymentEntitySessionBeanLocal.createNewPayment(new PaymentEntity(100.00, PaymentStatusEnum.PAID, baseCompany, new Date()));
            baseCompany.getPaymentHistory().add(paymentFour);
            
            PaymentEntity paymentFive = paymentEntitySessionBeanLocal.createNewPayment(new PaymentEntity(100.00, PaymentStatusEnum.PAID, baseCompany, new Date()));
            baseCompany.getPaymentHistory().add(paymentFive);
            
            ProfessionalEntity professional1 = professionalEntitySessionBeanLocal.createNewProfessional(new ProfessionalEntity("password", "John", "Tan", "Hougang Avenue 7", "johntan@gmail.com",
            'M', 81345678, new Timestamp(System.currentTimeMillis()), new Date(), UserTypeEnum.EMPLOYEE), baseCompany.getCompanyId());
            baseCompany.getProfessionalsList().add(professional1);
            
            ProfessionalEntity professional2 = professionalEntitySessionBeanLocal.createNewProfessional(new ProfessionalEntity("password", "Dylan", "Lim", "Bedok Avenue 5", "DylanLim@gmail.com",
            'M', 92678950, new Timestamp(System.currentTimeMillis()), new Date(), UserTypeEnum.EMPLOYEE), baseCompany.getCompanyId());
            baseCompany.getProfessionalsList().add(professional2);
            
            ProfessionalEntity professional3 = professionalEntitySessionBeanLocal.createNewProfessional(new ProfessionalEntity("password", "Mary", "Lim", "Ang Mo Kio Avenue 6", "MaryLim@gmail.com",
            'F', 82368792, new Timestamp(System.currentTimeMillis()), new Date(), UserTypeEnum.EMPLOYEE), baseCompany.getCompanyId());
            baseCompany.getProfessionalsList().add(professional3);
            
            ProfessionalEntity professional4 = professionalEntitySessionBeanLocal.createNewProfessional(new ProfessionalEntity("password", "Jane", "Tan", "Novena Avenue 10", "JaneTan@gmail.com",
            'F', 92547629, new Timestamp(System.currentTimeMillis()), new Date(), UserTypeEnum.EMPLOYEE), baseCompany.getCompanyId());
            baseCompany.getProfessionalsList().add(professional4);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }    
    }
}
