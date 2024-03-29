package org.toilelibre.libe.scrabble.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.properties.ScrabbleMessages;

public final class InitBeans implements IScrabbleInit
{
  private static final Logger LOG = LogManager.getLogger (InitBeans.class);

  public InitBeans ()
  {

  }

  @Override
  public void init ()
  {
    InitBeans.LOG.info ("Beans Spring");
    ScrabbleBeansHelper
        .setImpl (new org.springframework.context.support.ClassPathXmlApplicationContext (
            ScrabbleMessages.getMessage ("locations.applicationcontext")));
  }
}
