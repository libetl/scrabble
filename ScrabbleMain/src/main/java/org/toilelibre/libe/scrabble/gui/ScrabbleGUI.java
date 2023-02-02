package org.toilelibre.libe.scrabble.gui;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.userinteractions.model.UserInteractions;

public final class ScrabbleGUI
{
  private static final String CREATING_GUI_ERROR = "Erreur lors de la création de l'interface";
  private static final Logger LOG                = LogManager.getLogger (ScrabbleGUI.class);

  private static ScrabbleGUI  sgui;

  public static ScrabbleGUI getInstance ()
  {
    return ScrabbleGUI.sgui;
  }

  public static void redirect (final String beanName, final URL url)
  {
    ScrabbleGUI.getInstance ().display (beanName, url);
  }

  private final Class<?>         clazz  = (Class<?>) ScrabbleBeansHelper
                                            .getBean ("guiEngineClass");

  private Object                 engine = new Object ();

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

      final Constructor<?> constr = this.getGoodGuiEngineConstructor ();
      this.engine = constr.newInstance (this.ui.getBean (beanName));
      this.registerCanvas3D (Class.forName ((String) ScrabbleBeansHelper
          .getBean ("canvas3d")));

      this.clazz.getMethod ("render", URL.class).invoke (this.engine, url);
      this.ui.updateBindings ();

    } catch (final IllegalAccessException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);

    } catch (final InvocationTargetException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (final IllegalArgumentException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (final NoSuchMethodException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (final SecurityException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (final InstantiationException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (final ClassNotFoundException e)
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

  private Constructor<?> getGoodGuiEngineConstructor ()
  {
    final Constructor<?> [] constrs = this.clazz.getDeclaredConstructors();
    for (final Constructor<?> constr : constrs)
    {
      if ((constr.getParameterTypes ().length == 1)
          && constr.getParameterTypes ()[0].equals (Container.class))
      {
        return constr;
      }
    }
    return null;
  }

  public void load ()
  {
    ScrabbleBeansHelper.launchMethod ("fireLoadRunnable");
  }

  public void registerCanvas3D (final Object canvas3D)
      throws IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException,
      ClassNotFoundException
  {
    final Object taglib = this.engine.getClass ().getMethod ("getTaglib")
        .invoke (this.getEngine ());
    final Class<?> c = taglib.getClass ();
    c.getMethod ("registerTag", String.class, Class.class).invoke (taglib,
        "Canvas3D",
        Class.forName ((String) ScrabbleBeansHelper.getBean ("canvas3d")));

  }
}
