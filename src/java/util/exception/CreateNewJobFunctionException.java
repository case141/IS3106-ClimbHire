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
public class CreateNewJobFunctionException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewJobFunctionException</code>
     * without detail message.
     */
    public CreateNewJobFunctionException() {
    }

    /**
     * Constructs an instance of <code>CreateNewJobFunctionException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewJobFunctionException(String msg) {
        super(msg);
    }
}
