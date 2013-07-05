/**
 * 
 */
package org.toilelibre.libe.scrabble.s3d.loader;

/**
 * @author lionel
 * 
 */
public class S3DLoaderException extends Exception {

    /**
   * 
   */
    private static final long serialVersionUID = -7184604413601705595L;

    public S3DLoaderException() {
        super ();
    }

    public S3DLoaderException(final String message) {
        super (message);
    }

    public S3DLoaderException(final String message, final Throwable cause) {
        super (message, cause);
    }

    public S3DLoaderException(final Throwable cause) {
        super (cause);
    }
}
