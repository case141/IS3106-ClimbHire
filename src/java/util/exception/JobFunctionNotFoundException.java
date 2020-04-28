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
public class JobFunctionNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>JobFunctionNotFoundException</code>
     * without detail message.
     */
    public JobFunctionNotFoundException() {
    }

    /**
     * Constructs an instance of <code>JobFunctionNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public JobFunctionNotFoundException(String msg) {
        super(msg);
    }
}
