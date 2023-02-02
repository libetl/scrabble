package org.toilelibre.libe.scrabble.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.model.ScrabbleData;
import org.toilelibre.libe.scrabble.modelfactory.ScrabbleDataLoader;

public final class InitData implements IScrabbleInit
{
  private static final Logger LOG = LogManager.getLogger (InitData.class);

  public InitData ()
  {
    super ();
  }

  @Override
  public void init ()
  {
    InitData.LOG.info ("Données du jeu");
    final ScrabbleDataLoader sdl = (ScrabbleDataLoader) ScrabbleBeansHelper
        .getBean ("dataLoader");
    final ScrabbleData sd = (ScrabbleData) ScrabbleBeansHelper
        .getBean ("scrabbleData");
    sdl.load (sd);
  }

}
