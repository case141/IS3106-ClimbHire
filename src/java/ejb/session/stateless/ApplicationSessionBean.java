package ejb.session.stateless;

import entity.ApplicationEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CreateNewApplicationException;

@Stateless
@Local(ApplicationSessionBeanLocal.class)

public class ApplicationSessionBean implements ApplicationSessionBeanLocal 
{
    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager entityManager;

    @Override
    public Long createNewApplication(ApplicationEntity newApplication)
    {
        entityManager.persist(newApplication);
        entityManager.flush();
        
        return newApplication.getApplicationId();
    }
    
    @Override
    public List<ApplicationEntity> retrieveAllApplications()
    {
        Query query = entityManager.createQuery("SELECT app FROM Application app");
        List<ApplicationEntity> applications = query.getResultList();
        
        return applications;
    }
}
