/**
 * 
 */
package org.toilelibre.libe.scrabble.init;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.exception.ScrabbleException;
import org.toilelibre.libe.scrabble.gui.ScrabbleGUI;

/**
 * @author E5184343
 * 
 */
public class InitGUI implements IScrabbleInit
{

  private static final Logger LOG = Logger.getLogger (InitGUI.class);

  public InitGUI ()
  {

  }

  /**
   * @see org.toilelibre.libe.scrabble.init.IScrabbleInit#init()
   */
  @Override
  public final void init () throws ScrabbleException
  {
    InitGUI.LOG.info ("Contrôles graphiques");
    final ScrabbleGUI sgui = new ScrabbleGUI ();
    ScrabbleBeansHelper.getBean ("canvas3d");

    sgui.load ();

  }

}
