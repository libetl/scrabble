package org.toilelibre.libe.scrabble.model.exception;

public class ScrabbleModelException extends Exception {

    /**
   * 
   */
    private static final long serialVersionUID = 7782957821016096051L;

    public ScrabbleModelException () {
        super ();
    }

    public ScrabbleModelException (final String message) {
        super (message);
    }

    public ScrabbleModelException (final String message, final Throwable cause) {
        super (message, cause);
    }

    public ScrabbleModelException (final Throwable cause) {
        super (cause);
    }

}
