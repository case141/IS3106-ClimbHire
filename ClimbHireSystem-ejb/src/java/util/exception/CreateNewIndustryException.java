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
public class CreateNewIndustryException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewIndustryException</code> without
     * detail message.
     */
    public CreateNewIndustryException() {
    }

    /**
     * Constructs an instance of <code>CreateNewIndustryException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewIndustryException(String msg) {
        super(msg);
    }
}
