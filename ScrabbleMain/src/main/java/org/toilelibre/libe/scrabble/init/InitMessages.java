/**
 * 
 */
package org.toilelibre.libe.scrabble.init;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.exception.ScrabbleException;
import org.toilelibre.libe.scrabble.properties.Messages;
import org.toilelibre.libe.scrabble.properties.ScrabbleMessages;

public final class InitMessages implements IScrabbleInit
{

  private static final Logger LOG = Logger.getLogger (InitMessages.class);

  public InitMessages ()
  {

  }

  /**
   * 
   * 
   * @see org.toilelibre.libe.scrabble.init.IScrabbleInit#init()
   */
  @Override
  public void init () throws ScrabbleException
  {
    InitMessages.LOG.info ("Propriétés d'application");
    ScrabbleMessages.setMessages (new Messages ("properties.scrabble"));
  }

}
