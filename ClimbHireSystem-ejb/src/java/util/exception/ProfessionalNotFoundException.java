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
public class ProfessionalNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ProfessionalNotFoundException</code>
     * without detail message.
     */
    public ProfessionalNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ProfessionalNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ProfessionalNotFoundException(String msg) {
        super(msg);
    }
}
