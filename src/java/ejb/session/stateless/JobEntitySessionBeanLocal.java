package ejb.session.stateless;

import entity.JobEntity;
import java.util.List;
import util.exception.PositionTypeNotFoundException;
import util.exception.IndustryNotFoundException;
import util.exception.JobFunctionNotFoundException;
import util.exception.CreateNewJobException;
import util.exception.DeleteJobException;
import util.exception.InputDataValidationException;
import util.exception.JobNotFoundException;
import util.exception.JobCompanyNameExistException;
import util.exception.TagNotFoundException;
import util.exception.UnknownPersistenceException;
import util.exception.UpdateJobException;

public interface JobEntitySessionBeanLocal
{
    JobEntity createNewJob(JobEntity newJobEntity, Long positionTypeId, Long industryId, Long jobFunctionId, List<Long> tagIds) throws JobCompanyNameExistException, UnknownPersistenceException, InputDataValidationException, CreateNewJobException;
  
    List<JobEntity> retrieveAllJobs();
    
    List<JobEntity> searchJobsByCompanyName(String searchString);
    
    List<JobEntity> filterJobsByPositionType(Long positionTypeId) throws PositionTypeNotFoundException;
    
    List<JobEntity> filterJobsByIndustry(Long industryId) throws IndustryNotFoundException;
    
    List<JobEntity> filterJobsByJobFunction(Long jobFunctionId) throws JobFunctionNotFoundException;
    
    List<JobEntity> filterJobsByTags(List<Long> tagIds, String condition);

    JobEntity retrieveJobByJobId(Long jobId) throws JobNotFoundException;

    JobEntity retrieveJobByJobCompanyName(String companyName) throws JobNotFoundException;

    void updateJob(JobEntity jobEntity, Long positionTypeId, Long industryId, Long jobFunctionId, List<Long> tagIds) throws JobNotFoundException, PositionTypeNotFoundException, IndustryNotFoundException, JobFunctionNotFoundException, TagNotFoundException, UpdateJobException, InputDataValidationException;
    
    void deleteJob(Long jobId) throws JobNotFoundException, DeleteJobException;
}
