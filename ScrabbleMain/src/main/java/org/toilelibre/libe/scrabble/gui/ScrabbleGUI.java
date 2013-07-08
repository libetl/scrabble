package org.toilelibre.libe.scrabble.gui;

import java.lang.reflect.Constructor;
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

  private final Class<?>         clazz  = (Class<?>) ScrabbleBeansHelper
                                            .getBean ("guiEngineClass");

  private Object                 engine = new Object ();

  private final UserInteractions ui     = (UserInteractions) ScrabbleBeansHelper
                                            .getBean ("userInteractions");

  public ScrabbleGUI ()
  {
    ScrabbleGUI.sgui = this;
  }

  public void registerCanvas3D (Object canvas3D) throws IllegalAccessException,
      IllegalArgumentException, InvocationTargetException,
      NoSuchMethodException, SecurityException, ClassNotFoundException
  {
    Object taglib = this.engine.getClass ().getMethod ("getTaglib")
        .invoke (this.getEngine ());
    Class<?> c = taglib.getClass ();
    c.getMethod ("registerTag", String.class, Class.class).invoke (taglib,
        "Canvas3D",
        Class.forName ((String) ScrabbleBeansHelper.getBean ("canvas3d")));

  }

  public void display (final String beanName, final URL url)
  {
    try
    {
      ScrabbleGUI.LOG.info ("Redirection ("
          + url.toString ().substring (url.toString ().lastIndexOf ('/') + 1)
          + ", " + beanName + ")");

      Constructor<?> constr = this.getGoodGuiEngineConstructor ();
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
    } catch (IllegalArgumentException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (NoSuchMethodException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (SecurityException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (InstantiationException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    } catch (ClassNotFoundException e)
    {
      ScrabbleGUI.LOG.error (ScrabbleGUI.CREATING_GUI_ERROR, e);
    }
  }

  private Constructor<?> getGoodGuiEngineConstructor ()
  {
    Constructor<?> [] constrs = this.clazz.getConstructors ();
    for (Constructor<?> constr : constrs)
    {
      if (constr.getParameterTypes ().length == 1)
      {
        return constr;
      }
    }
    return null;
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
