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
public class CompanyEmailExistException extends Exception {

    /**
     * Creates a new instance of <code>CompanyEmailExistException</code> without
     * detail message.
     */
    public CompanyEmailExistException() {
    }

    /**
     * Constructs an instance of <code>CompanyEmailExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CompanyEmailExistException(String msg) {
        super(msg);
    }
}
