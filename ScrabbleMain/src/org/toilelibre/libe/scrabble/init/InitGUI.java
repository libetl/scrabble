/**
 * 
 */
package org.toilelibre.libe.scrabble.init;

import java.lang.reflect.InvocationTargetException;

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
  public final void init () throws ScrabbleException
  {
    InitGUI.LOG.info ("Contrôles graphiques");
    final ScrabbleGUI sgui = new ScrabbleGUI ();
    try
    {
      Object taglib = sgui.getEngine ().getClass ().getMethod ("getTaglib").invoke (sgui.getEngine ());
      Class<?> c = taglib.getClass ();
      c.getMethod ("registerTag", String.class, Class.class).invoke (
          taglib, "Canvas3D", Class.forName ((String) ScrabbleBeansHelper.getBean ("canvas3d")));
    } catch (final ClassNotFoundException e)
    {
      throw new ScrabbleException (e);
    } catch (IllegalAccessException e)
    {
      throw new ScrabbleException (e);
    } catch (IllegalArgumentException e)
    {
      throw new ScrabbleException (e);
    } catch (InvocationTargetException e)
    {
      throw new ScrabbleException (e);
    } catch (NoSuchMethodException e)
    {
      throw new ScrabbleException (e);
    } catch (SecurityException e)
    {
      throw new ScrabbleException (e);
    }
    sgui.load ();

  }

}
