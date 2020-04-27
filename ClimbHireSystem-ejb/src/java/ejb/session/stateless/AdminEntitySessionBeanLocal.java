/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Casse
 */
@Local
public interface AdminEntitySessionBeanLocal {

    public Long createNewAdmin(AdminEntity newAdmin);

    public List<AdminEntity> retrieveAllAdmins();
    
}
