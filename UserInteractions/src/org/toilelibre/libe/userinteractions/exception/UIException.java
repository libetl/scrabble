/**
 * 
 */
package org.toilelibre.libe.userinteractions.exception;

/**
 * @author lionel
 * 
 */
public class UIException extends Exception {

    /**
   * 
   */
    private static final long serialVersionUID = -5870510507773593373L;

    /**
   * 
   */
    public UIException () {
    }

    /**
     * @param message
     */
    public UIException (final String message) {
        super (message);
    }

    /**
     * @param message
     * @param cause
     */
    public UIException (final String message, final Throwable cause) {
        super (message, cause);
    }

    /**
     * @param cause
     */
    public UIException (final Throwable cause) {
        super (cause);
    }

}
