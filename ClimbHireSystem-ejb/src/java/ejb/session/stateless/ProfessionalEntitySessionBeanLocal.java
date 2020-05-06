/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ProfessionalEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewProfessionalException;
import util.exception.InputDataValidationException;
import util.exception.ProfessionalNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Casse
 */
@Local
public interface ProfessionalEntitySessionBeanLocal {

    public List<ProfessionalEntity> retrieveAllProfessionals();

    public ProfessionalEntity retrieveProfessionalByEmail(String professionalEmail) throws ProfessionalNotFoundException;
    
    public ProfessionalEntity createNewProfessional(ProfessionalEntity newProfessional, Long companyId) throws UnknownPersistenceException, InputDataValidationException, CreateNewProfessionalException, ProfessionalNotFoundException;
    
}
