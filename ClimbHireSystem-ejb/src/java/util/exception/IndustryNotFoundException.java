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
public class IndustryNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>IndustryNotFoundException</code> without
     * detail message.
     */
    public IndustryNotFoundException() {
    }

    /**
     * Constructs an instance of <code>IndustryNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IndustryNotFoundException(String msg) {
        super(msg);
    }
}
