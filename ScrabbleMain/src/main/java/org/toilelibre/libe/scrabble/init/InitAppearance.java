package org.toilelibre.libe.scrabble.init;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.Scrabble;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.exception.ScrabbleException;
import org.toilelibre.libe.scrabble.properties.ScrabbleMessages;

public final class InitAppearance implements IScrabbleInit
{
  private static final double FIVE           = 1.5;
  private static final String JAVA_VERSION   = "java.version";
  private static double       javaVersionFromVM;
  private static final Logger LOG            = Logger
                                                 .getLogger (InitAppearance.class);
  private static final String SET_LAF_METHOD = "setSexyLookAndFeel";
  private static final double SIX            = 1.6;

  static
  {

    final String version = System.getProperty (InitAppearance.JAVA_VERSION);
    final int majorIndex = version.indexOf ('.');
    final int minorIndex = majorIndex + 1
        + version.substring (majorIndex + 1).indexOf ('.');
    InitAppearance.javaVersionFromVM = Double.parseDouble (version.substring (
        0, minorIndex));
  }

  public static void changeLaf60 (final Class<?> clazz)
  {
    if (InitAppearance.javaVersionFromVM >= InitAppearance.SIX)
    {
      InitAppearance.LOG.info ("Changement d'apparence graphique");
      ScrabbleBeansHelper.modifyMethodParameters (
          InitAppearance.SET_LAF_METHOD, new Object [] {clazz.getName () });
      ScrabbleBeansHelper.launchMethod (InitAppearance.SET_LAF_METHOD);
    }
  }

  public static void init50 () throws ScrabbleException
  {
    try
    {
      InitAppearance.LOG
          .info ("Apparence graphique v1.0 (v2.0 disponible en Java 6)");
      final Object laf = ScrabbleBeansHelper.getBean ("synthLaf");
      final Class<?> uimc = ScrabbleBeansHelper.getType ("uiManager");
      final Method load = laf.getClass ().getMethod ("load",
          new Class<?> [] {InputStream.class, Class.class });
      final Method setLookAndFeel = uimc.getMethod ("setLookAndFeel",
          new Class<?> [] {Class.forName ((String) ScrabbleBeansHelper
              .getBean ("laf")), });

      final String filename = ScrabbleMessages
          .getMessage ("locations.synth.plaf");
      final InputStream is = Thread.currentThread ().getContextClassLoader ()
          .getResourceAsStream (filename);
      load.invoke (laf, new Object [] {is, Scrabble.class });
      setLookAndFeel.invoke (null, new Object [] {laf });
    } catch (final SecurityException e)
    {
      throw new ScrabbleException (e);
    } catch (final NoSuchMethodException e)
    {
      throw new ScrabbleException (e);
    } catch (final IllegalArgumentException e)
    {
      throw new ScrabbleException (e);
    } catch (final IllegalAccessException e)
    {
      throw new ScrabbleException (e);
    } catch (final InvocationTargetException e)
    {
      throw new ScrabbleException (e);
    } catch (final ClassNotFoundException e)
    {
      throw new ScrabbleException (e);
    }
  }

  public static void init60 ()
  {
    InitAppearance.LOG.info ("Apparence graphique Java >= 6");
    ScrabbleBeansHelper.launchMethod (InitAppearance.SET_LAF_METHOD);
    ScrabbleBeansHelper.launchMethod ("setDefaultLookAndFeelDecorated");
  }

  public InitAppearance ()
  {

  }

  @Override
  public void init () throws ScrabbleException
  {
    if (InitAppearance.javaVersionFromVM >= InitAppearance.SIX)
    {
      InitAppearance.init50 ();
    } else if (InitAppearance.javaVersionFromVM >= InitAppearance.FIVE)
    {
      InitAppearance.init50 ();
    }
  }
}
