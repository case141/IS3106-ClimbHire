/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ApplicationEntity;
import javax.ejb.Local;
import util.exception.ApplicationNotFoundException;

/**
 *
 * @author Casse
 */
@Local
public interface ApplicationEntitySessionBeanLocal {

    public ApplicationEntity retrieveApplicationByApplicationId(Long applicationId) throws ApplicationNotFoundException;
    
}
