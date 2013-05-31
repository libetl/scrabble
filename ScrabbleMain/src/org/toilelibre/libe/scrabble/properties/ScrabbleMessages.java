package org.toilelibre.libe.scrabble.properties;

public final class ScrabbleMessages
{
  private static Messages m;

  private ScrabbleMessages ()
  {

  }

  public static String getMessage (final String key)
  {
    return ScrabbleMessages.m.getString (key);
  }

  /**
   * @param m1
   *          m � d�finir
   */
  public static void setMessages (final Messages m1)
  {
    ScrabbleMessages.m = m1;
  }
}
