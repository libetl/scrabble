/**
 * 
 */
package org.toilelibre.libe.scrabble.modelfactory;

/**
 * @author lionel
 * 
 */
public final class BuildProcessException extends Exception
{

  /**
   * 
   */
  private static final long serialVersionUID = -3064208074552158785L;

  /**
   * 
   */
  public BuildProcessException ()
  {
  }

  /**
   * @param message
   */
  public BuildProcessException (final String message)
  {
    super (message);
  }

  /**
   * @param message
   * @param cause
   */
  public BuildProcessException (final String message, final Throwable cause)
  {
    super (message, cause);
  }

  /**
   * @param cause
   */
  public BuildProcessException (final Throwable cause)
  {
    super (cause);
  }

}
