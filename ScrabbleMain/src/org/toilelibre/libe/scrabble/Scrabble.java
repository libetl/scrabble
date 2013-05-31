package org.toilelibre.libe.scrabble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.exception.ScrabbleException;
import org.toilelibre.libe.scrabble.init.IScrabbleInit;

public final class Scrabble
{
  private static final Logger LOG = Logger.getLogger (Scrabble.class);

  private Scrabble ()
  {
  }

  public static void exit (final int i)
  {
    Scrabble.LOG.info ("Sortie de Scrabble - code " + i + "");
    System.exit (i);
  }

  public static void init ()
  {
    final String errorComment = "Erreur lors de l'initialisation";
    final InputStream is = Scrabble.class.getClassLoader ()
        .getResourceAsStream ("init/init.lst");
    final BufferedReader br = new BufferedReader (new InputStreamReader (is));
    String className;
    Scrabble.LOG.info ("Début Initialisation");
    try
    {
      while ((className = br.readLine ()) != null)
      {
        if (!className.startsWith ("#") && (className.length () > 0))
        {
          final IScrabbleInit isi = (IScrabbleInit) Class.forName (className)
              .newInstance ();
          isi.init ();
        }
      }
    } catch (final IOException e)
    {
      Scrabble.LOG.error (errorComment, e);
    } catch (final ClassNotFoundException e)
    {
      Scrabble.LOG.error (errorComment, e);
    } catch (final IllegalAccessException e)
    {
      Scrabble.LOG.error (errorComment, e);
    } catch (InstantiationException e)
    {
      Scrabble.LOG.error (errorComment, e);
    } catch (ScrabbleException e)
    {
      Scrabble.LOG.error (errorComment, e);
    }
    Scrabble.LOG.info ("Fin Initialisation");
  }
}
