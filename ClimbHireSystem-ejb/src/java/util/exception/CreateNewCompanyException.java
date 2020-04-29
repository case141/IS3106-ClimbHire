/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author Casse
 */
public class CreateNewCompanyException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewCompanyException</code> without
     * detail message.
     */
    public CreateNewCompanyException() {
    }

    /**
     * Constructs an instance of <code>CreateNewCompanyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewCompanyException(String msg) {
        super(msg);
    }
}
