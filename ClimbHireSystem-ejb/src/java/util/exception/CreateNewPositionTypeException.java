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
public class CreateNewPositionTypeException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewPositionTypeException</code>
     * without detail message.
     */
    public CreateNewPositionTypeException() {
    }

    /**
     * Constructs an instance of <code>CreateNewPositionTypeException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewPositionTypeException(String msg) {
        super(msg);
    }
}
