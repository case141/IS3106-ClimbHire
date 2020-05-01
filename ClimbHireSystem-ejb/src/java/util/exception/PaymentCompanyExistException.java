/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author rycan
 */
public class PaymentCompanyExistException extends Exception {

    /**
     * Creates a new instance of <code>PaymentCompanyExistException</code>
     * without detail message.
     */
    public PaymentCompanyExistException() {
    }

    /**
     * Constructs an instance of <code>PaymentCompanyExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public PaymentCompanyExistException(String msg) {
        super(msg);
    }
}
