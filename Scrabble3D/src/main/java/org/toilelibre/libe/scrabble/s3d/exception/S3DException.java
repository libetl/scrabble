/**
 * 
 */
package org.toilelibre.libe.scrabble.s3d.exception;

/**
 * @author lionel
 * 
 */
public class S3DException extends Exception {

    /**
   * 
   */
    private static final long serialVersionUID = -4626175292772391561L;

    /**
   * 
   */
    public S3DException () {
    }

    /**
     * @param message
     *            message
     */
    public S3DException (final String message) {
        super (message);
    }

    /**
     * @param message
     *            message
     * @param cause
     *            cause
     */
    public S3DException (final String message, final Throwable cause) {
        super (message, cause);
    }

    /**
     * @param cause
     *            cause
     */
    public S3DException (final Throwable cause) {
        super (cause);
    }

}
