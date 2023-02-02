package org.toilelibre.libe.scrabble.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.properties.ScrabbleMessages;
import org.toilelibre.libe.userinteractions.loader.UILoader;
import org.toilelibre.libe.userinteractions.model.UserInteractions;

public final class InitUserInteractions implements IScrabbleInit
{
  private static final Logger LOG = LogManager.getLogger (InitUserInteractions.class);

  public InitUserInteractions ()
  {
    super ();
  }

  @Override
  public void init ()
  {
    InitUserInteractions.LOG.info ("Interactions");
    final UserInteractions ui = (UserInteractions) ScrabbleBeansHelper
        .getBean ("userInteractions");
    UILoader.load (ui,
        ScrabbleMessages.getMessage ("locations.userinteractions"));

  }
}
