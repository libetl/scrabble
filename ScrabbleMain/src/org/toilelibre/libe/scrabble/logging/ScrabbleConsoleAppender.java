/**
 * 
 */
package org.toilelibre.libe.scrabble.logging;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.toilelibre.libe.userinteractions.util.BeansComponents;
import org.toilelibre.libe.userinteractions.util.ListModel;

/**
 * @author lionel
 * 
 */
public final class ScrabbleConsoleAppender extends AppenderSkeleton
{

  private static ListModel<String> dlm;

  /**
   * @param console1
   */
  public static void setImpl (final Object console1)
  {
    BeansComponents.setListModel (console1, ScrabbleConsoleAppender.dlm);
  }

  public ScrabbleConsoleAppender ()
  {
    ScrabbleConsoleAppender.dlm = new ListModel<String> ();
  }

  /**
   * @see org.apache.log4j.AppenderSkeleton
   *      #append(org.apache.log4j.spi.LoggingEvent)
   */
  @Override
  protected void append (final LoggingEvent le)
  {
    if (ScrabbleConsoleAppender.dlm != null)
    {
      ScrabbleConsoleAppender.dlm.addElement (this.layout.format (le));
    }
  }

  /**
   * @see org.apache.log4j.Appender#close()
   */
  public void close ()
  {
    if (ScrabbleConsoleAppender.dlm != null)
    {
      ScrabbleConsoleAppender.dlm.clear ();
    }
  }

  /**
   * @see org.apache.log4j.Appender#requiresLayout()
   */
  public boolean requiresLayout ()
  {
    return false;
  }

}
