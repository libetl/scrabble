/**
 * 
 */
package org.toilelibre.libe.scrabble.gui;

import java.net.URL;

import org.toilelibre.libe.scrabble.properties.ScrabbleMessages;

/**
 * @author lionel
 * 
 */
public final class LoadRunnable implements Runnable
{
  public LoadRunnable ()
  {

  }

  @Override
  public void run ()
  {
    final URL url = Thread.currentThread ().getContextClassLoader ()
        .getResource (ScrabbleMessages.getMessage ("locations.xul.mainWindow"));
    ScrabbleGUI.getInstance ().display ("gameBean", url);
  }

}
