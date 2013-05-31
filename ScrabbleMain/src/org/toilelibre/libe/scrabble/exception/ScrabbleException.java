/**
 * 
 */
package org.toilelibre.libe.scrabble.exception;

/**
 * @author lionel
 * 
 */
public class ScrabbleException extends Exception
{

  /**
   * 
   */
  private static final long serialVersionUID = 6392437823598381366L;

  /**
   * 
   */
  public ScrabbleException ()
  {
  }

  /**
   * @param message
   */
  public ScrabbleException (final String message)
  {
    super (message);
  }

  /**
   * @param message
   * @param cause
   */
  public ScrabbleException (final String message, final Throwable cause)
  {
    super (message, cause);
  }

  /**
   * @param cause
   */
  public ScrabbleException (final Throwable cause)
  {
    super (cause);
  }

}
