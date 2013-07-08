package org.toilelibre.libe.scrabble.gui;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.userinteractions.model.UserInteractions;

public final class ScrabbleGUI
{
  private static final String CREATING_GUI_ERROR = "Erreur lors de la création de l'interface";
  private static final Logger LOG                = Logger
                                                     .getLogger (ScrabbleGUI.class);

  private static ScrabbleGUI  sgui;

  public static ScrabbleGUI getInstance ()
  {
    return ScrabbleGUI.sgui;
  }

  public static void redirect (final String beanName, final URL url)
  {
    ScrabbleGUI.getInstance ().display (beanName, url);
  }

  private final Object           engine = ScrabbleBeansHelper
                                            .getBean ("guiEngine");

  private final UserInteractions ui     = (UserInteractions) ScrabbleBeansHelper
                                            .getBean ("userInteractions");

  public ScrabbleGUI ()
  {
    ScrabbleGUI.sgui = this;
  }

  public void display (final String beanName, final URL url)
  {
    try
    {
      ScrabbleGUI.LOG.info ("Redirection ("
          + url.toString ().substring (url.toString ().lastIndexOf ('/') + 1)
          + ", " + beanName + ")");
      Class<?> c = this.engine.getClass ();

      c.getMethod ("setClient", Object.class).invoke (this.engine,
          this.ui.getBean (beanName));
      c.getMethod ("render", URL.class).invoke (this.engine, url);
      this.ui.updateBindings ();

    } catch (final IllegalAccessException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);

    } catch (final InvocationTargetException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (IllegalArgumentException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (NoSuchMethodException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (SecurityException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    }
  }

  /**
   * @return engine
   */
  public Object getEngine ()
  {
    return this.engine;
  }

  public void load ()
  {
    ScrabbleBeansHelper.launchMethod ("fireLoadRunnable");
  }
}
