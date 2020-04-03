package ejb.session.stateless;

import entity.JobFunctionEntity;
import java.util.List;
import util.exception.JobFunctionNotFoundException;
import util.exception.CreateNewJobFunctionException;
import util.exception.DeleteJobFunctionException;
import util.exception.InputDataValidationException;
import util.exception.UpdateJobFunctionException;

public interface JobFunctionEntitySessionBeanLocal 
{
    JobFunctionEntity createNewJobFunctionEntity(JobFunctionEntity newJobFunctionEntity, Long parentJobFunctionId) throws InputDataValidationException, CreateNewJobFunctionException;
    
    List<JobFunctionEntity> retrieveAllJobFunctions();
    
    List<JobFunctionEntity> retrieveAllRootJobFunctions();
    
    List<JobFunctionEntity> retrieveAllLeafJobFunctions();
    
    List<JobFunctionEntity> retrieveAllJobFunctionsWithoutJob();
    
    JobFunctionEntity retrieveJobFunctionByJobFunctionId(Long jobFunctionId) throws JobFunctionNotFoundException;
    
    void updateJobFunction(JobFunctionEntity jobFunctionEntity, Long parentJobFunctionId) throws InputDataValidationException, JobFunctionNotFoundException, UpdateJobFunctionException;
    
    void deleteJobFunction(Long jobFunctionId) throws JobFunctionNotFoundException, DeleteJobFunctionException;
}
