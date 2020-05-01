/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.PaymentEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewPaymentRecordException;
import util.exception.InputDataValidationException;
import util.exception.PaymentCompanyExistException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Casse
 */
@Local
public interface PaymentEntitySessionBeanLocal {

    public PaymentEntity createNewPayment(PaymentEntity newPaymentRecord, Long companyId) throws PaymentCompanyExistException, UnknownPersistenceException, InputDataValidationException, CreateNewPaymentRecordException;
    
    public List<PaymentEntity> retrieveAllPaymentRecords();
    
}
