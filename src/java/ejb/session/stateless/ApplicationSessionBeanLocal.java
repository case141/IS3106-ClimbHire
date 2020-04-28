package ejb.session.stateless;

import entity.ApplicationEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewApplicationException;

@Local
public interface ApplicationSessionBeanLocal 
{

    public List<ApplicationEntity> retrieveAllApplications();

    public Long createNewApplication(ApplicationEntity newApplication);

}
