package org.toilelibre.libe.scrabble.modelfactory.dictionary.loader.types;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;
import org.toilelibre.libe.scrabble.modelfactory.dictionary.loader.Loader;

public class HuffToSetLoader implements Loader
{

  private static final Logger LOG = Logger.getLogger (HuffToSetLoader.class);

  public HuffToSetLoader ()
  {

  }

  private void load (final Dictionary d, final Reader r)
  {
    try
    {
      String debutMot = "";
      int val = 0;

      do
      {
        val = r.read ();
        switch (val)
        {
          case '\0':
            break;
          case '*':
            d.add (debutMot);
            debutMot = debutMot.substring (0, debutMot.length () - 2);
            break;
          case '$':
            d.add (debutMot);
            break;
          case '/':
            if (debutMot.length () > 0)
            {
              debutMot = debutMot.substring (0, debutMot.length () - 1);
            } else
            {
              return;
            }
            break;
          default:
            debutMot += (char) val;
        }

      } while (val != '\0');
    } catch (final IOException e)
    {
      HuffToSetLoader.LOG.error (e.getMessage ());
    }

  }

  public final void load (final Dictionary d, final String fileName)
  {
    InputStreamReader isr;
    try
    {
      final ClassLoader cld = Thread.currentThread ().getContextClassLoader ();
      isr = new InputStreamReader (new FileInputStream (new File (cld
          .getResource (fileName).toURI ())));
      this.load (d, isr);
    } catch (final FileNotFoundException e)
    {
      HuffToSetLoader.LOG.error (e.getMessage ());
    } catch (final URISyntaxException e)
    {
      HuffToSetLoader.LOG.error (e.getMessage ());
    }
  }

}
