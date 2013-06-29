package org.toilelibre.libe.scrabble.modelfactory.dictionary.loader.types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;
import org.toilelibre.libe.scrabble.modelfactory.dictionary.loader.Loader;

public class TxtToSetLoader implements Loader {

    private static final Logger LOG = Logger.getLogger (TxtToSetLoader.class);

    public TxtToSetLoader () {

    }

    public final void load (final Dictionary d, final String fileName) {
        BufferedReader bfr = null;
        try {
            String line;
            final ClassLoader cld = Thread.currentThread ()
                    .getContextClassLoader ();
            final URL url = cld.getResource (fileName);
            final File f = new File (url.toURI ());
            bfr = new BufferedReader (new InputStreamReader (
                    new FileInputStream (f)));
            line = bfr.readLine ();
            while (line != null) {
                d.add (line);
                line = bfr.readLine ();
            }
        } catch (final FileNotFoundException e) {
            TxtToSetLoader.LOG.error (e.getMessage ());
        } catch (final IOException e) {
            TxtToSetLoader.LOG.error (e.getMessage ());
        } catch (final URISyntaxException e) {
            TxtToSetLoader.LOG.error (e.getMessage ());
        } finally {
            if (bfr != null) {
                try {
                    bfr.close ();
                } catch (IOException e) {
                    TxtToSetLoader.LOG.error (e.getMessage ());
                }
            }
        }
    }

}
