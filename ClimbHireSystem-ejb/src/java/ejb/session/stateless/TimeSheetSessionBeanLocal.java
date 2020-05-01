/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.TimeSheetEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rycan
 */
@Local
public interface TimeSheetSessionBeanLocal {
    
    public List<TimeSheetEntity> retrieveAllTimeSheets();
    
}
