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
public class CreateNewJobListingException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewJobListingException</code>
     * without detail message.
     */
    public CreateNewJobListingException() {
    }

    /**
     * Constructs an instance of <code>CreateNewJobListingException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewJobListingException(String msg) {
        super(msg);
    }
}
