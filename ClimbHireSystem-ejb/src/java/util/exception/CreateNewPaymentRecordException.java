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
public class CreateNewPaymentRecordException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewPaymentRecordException</code>
     * without detail message.
     */
    public CreateNewPaymentRecordException() {
    }

    /**
     * Constructs an instance of <code>CreateNewPaymentRecordException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewPaymentRecordException(String msg) {
        super(msg);
    }
}
