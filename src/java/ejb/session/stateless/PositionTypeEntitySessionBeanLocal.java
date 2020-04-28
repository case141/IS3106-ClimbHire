package ejb.session.stateless;

import entity.PositionTypeEntity;
import java.util.List;
import util.exception.PositionTypeNotFoundException;
import util.exception.CreateNewPositionTypeException;
import util.exception.DeletePositionTypeException;
import util.exception.InputDataValidationException;
import util.exception.UpdatePositionTypeException;

public interface PositionTypeEntitySessionBeanLocal 
{
    PositionTypeEntity createNewPositionTypeEntity(PositionTypeEntity newPositionTypeEntity, Long parentPositionTypeId) throws InputDataValidationException, CreateNewPositionTypeException;
    
    List<PositionTypeEntity> retrieveAllPositionTypes();
    
    List<PositionTypeEntity> retrieveAllRootPositionTypes();
    
    List<PositionTypeEntity> retrieveAllLeafPositionTypes();
    
    List<PositionTypeEntity> retrieveAllPositionTypesWithoutJob();
    
    PositionTypeEntity retrievePositionTypeByPositionTypeId(Long categoryId) throws PositionTypeNotFoundException;
    
    void updatePositionType(PositionTypeEntity positionTypeEntity, Long parentPositionTypeId) throws InputDataValidationException, PositionTypeNotFoundException, UpdatePositionTypeException;
    
    void deletePositionType(Long positionTypeId) throws PositionTypeNotFoundException, DeletePositionTypeException;
}
