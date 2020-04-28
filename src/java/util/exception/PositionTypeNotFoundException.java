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
public class PositionTypeNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>PositionTypeNotFoundException</code>
     * without detail message.
     */
    public PositionTypeNotFoundException() {
    }

    /**
     * Constructs an instance of <code>PositionTypeNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public PositionTypeNotFoundException(String msg) {
        super(msg);
    }
}
