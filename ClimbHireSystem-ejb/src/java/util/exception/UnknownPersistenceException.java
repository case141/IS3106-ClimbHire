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
public class UnknownPersistenceException extends Exception {

    /**
     * Creates a new instance of <code>UnknownPersistanceException</code>
     * without detail message.
     */
    public UnknownPersistenceException() {
    }

    /**
     * Constructs an instance of <code>UnknownPersistanceException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public UnknownPersistenceException(String msg) {
        super(msg);
    }
}
