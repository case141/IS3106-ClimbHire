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
public class SetCompanySubscriptionException extends Exception {

    /**
     * Creates a new instance of <code>SetCompanySubscriptionException</code>
     * without detail message.
     */
    public SetCompanySubscriptionException() {
    }

    /**
     * Constructs an instance of <code>SetCompanySubscriptionException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public SetCompanySubscriptionException(String msg) {
        super(msg);
    }
}
