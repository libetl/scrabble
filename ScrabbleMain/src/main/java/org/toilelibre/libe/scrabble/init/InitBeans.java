package org.toilelibre.libe.scrabble.init;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.properties.ScrabbleMessages;

public final class InitBeans implements IScrabbleInit
{
  private static final Logger LOG = Logger.getLogger (InitBeans.class);

  public InitBeans ()
  {

  }

  @Override
  public void init ()
  {
    InitBeans.LOG.info ("Beans Spring");
    final org.springframework.core.io.Resource res = new org.springframework.core.io.ClassPathResource (
        ScrabbleMessages.getMessage ("locations.applicationcontext"));
    ScrabbleBeansHelper
        .setImpl (new org.springframework.beans.factory.xml.XmlBeanFactory (res));
  }
}
