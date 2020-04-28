/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author jezzl
 */
public class JobCompanyNameExistException extends Exception {

    /**
     * Creates a new instance of <code>JobCompanyNameExistException</code>
     * without detail message.
     */
    public JobCompanyNameExistException() {
    }

    /**
     * Constructs an instance of <code>JobCompanyNameExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public JobCompanyNameExistException(String msg) {
        super(msg);
    }
}
