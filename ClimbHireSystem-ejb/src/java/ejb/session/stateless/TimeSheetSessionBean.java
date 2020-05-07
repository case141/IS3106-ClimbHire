/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.TimeSheetEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rycan
 */
@Stateless
public class TimeSheetSessionBean implements TimeSheetSessionBeanLocal {

    @PersistenceContext(unitName = "ClimbHireSystem-ejbPU")
    private EntityManager em;
    
    @Override
    public List<TimeSheetEntity> retrieveAllTimeSheets()
    {
        Query query = em.createQuery("SELECT t FROM TimeSheetEntity t");
        
        return query.getResultList();
    }
}