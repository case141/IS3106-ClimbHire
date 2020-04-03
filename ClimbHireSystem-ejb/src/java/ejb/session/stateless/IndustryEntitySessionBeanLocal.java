package ejb.session.stateless;

import entity.IndustryEntity;
import java.util.List;
import util.exception.IndustryNotFoundException;
import util.exception.CreateNewIndustryException;
import util.exception.DeleteIndustryException;
import util.exception.InputDataValidationException;
import util.exception.UpdateIndustryException;

public interface IndustryEntitySessionBeanLocal 
{
    IndustryEntity createNewIndustryEntity(IndustryEntity newIndustryEntity, Long parentIndustryId) throws InputDataValidationException, CreateNewIndustryException;
    
    List<IndustryEntity> retrieveAllIndustries();
    
    List<IndustryEntity> retrieveAllRootIndustries();
    
    List<IndustryEntity> retrieveAllLeafIndustries();
    
    List<IndustryEntity> retrieveAllIndustriesWithoutJob();
    
    IndustryEntity retrieveIndustryByIndustryId(Long industryId) throws IndustryNotFoundException;
    
    void updateIndustry(IndustryEntity categoryEntity, Long parentIndustryId) throws InputDataValidationException, IndustryNotFoundException, UpdateIndustryException;
    
    void deleteIndustry(Long industryId) throws IndustryNotFoundException, DeleteIndustryException;
}
