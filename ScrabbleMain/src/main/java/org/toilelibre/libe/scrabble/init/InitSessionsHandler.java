/**
 * 
 */
package org.toilelibre.libe.scrabble.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.sessions.ScrabbleSessionsHandler;

public final class InitSessionsHandler implements IScrabbleInit
{

  private static final Logger LOG = LogManager.getLogger (InitSessionsHandler.class);

  public InitSessionsHandler ()
  {

  }

  @Override
  public void init ()
  {
    InitSessionsHandler.LOG.info ("Gestionnaire de sessions");
    ScrabbleBeansHelper.getBean ("sessionsHandler");
    ScrabbleSessionsHandler.create ();
  }

}
