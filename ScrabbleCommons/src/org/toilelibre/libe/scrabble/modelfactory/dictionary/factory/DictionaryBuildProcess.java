package org.toilelibre.libe.scrabble.modelfactory.dictionary.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.model.ModelElement;
import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;
import org.toilelibre.libe.scrabble.modelfactory.BuildProcess;

public final class DictionaryBuildProcess implements BuildProcess {

    private static final Logger LOG = Logger.getLogger (DictionaryBuildProcess.class);

    private String              filename;

    private Locale              locale;

    private DictionaryBuildProcess () {

    }

    @SuppressWarnings ("unchecked")
    private static Class<AbstractDictionaryFactory> getFactoryClass (
            final String filename) throws ClassNotFoundException {
        final int indExtension = filename.lastIndexOf ('.') + 1;
        final String extension = filename.substring (indExtension,
                indExtension + 1).toUpperCase ()
                + filename.substring (indExtension + 1);
        final Class<AbstractDictionaryFactory> cdf = (Class<AbstractDictionaryFactory>) Class
                .forName (AbstractDictionaryFactory.class.getPackage ()
                        .getName ()
                        + ".types."
                        + extension
                        + "DictionaryFactory");
        return cdf;
    }

    public String getFile () {
        return this.filename;
    }

    public Locale getLocale () {
        return this.locale;
    }

    private void load (final IDictionaryFactory df, final Object... o) {
        df.load ();
    }

    public ModelElement process () {
        Dictionary d = null;
        try {
            final Class<AbstractDictionaryFactory> cdf = DictionaryBuildProcess
                    .getFactoryClass (this.filename);
            final Constructor<AbstractDictionaryFactory> constr = cdf
                    .getConstructor (String.class);
            final IDictionaryFactory df = constr.newInstance (this.filename);
            if ("default".equals (this.locale.getLanguage ())) {
                this.locale = Locale.getDefault ();
            }
            if (!this.locale.equals (df.getLocale ())) {
                return null;
            }
            this.load (df);
            d = df.getDictionary ();
        } catch (final ClassNotFoundException e) {
            DictionaryBuildProcess.LOG.error (e.getMessage ());
        } catch (final SecurityException e) {
            DictionaryBuildProcess.LOG.error (e.getMessage ());
        } catch (final NoSuchMethodException e) {
            DictionaryBuildProcess.LOG.error (e.getMessage ());
        } catch (final IllegalArgumentException e) {
            DictionaryBuildProcess.LOG.error (e.getMessage ());
        } catch (final InstantiationException e) {
            DictionaryBuildProcess.LOG.error (e.getMessage ());
        } catch (final IllegalAccessException e) {
            DictionaryBuildProcess.LOG.error (e.getMessage ());
        } catch (final InvocationTargetException e) {
            DictionaryBuildProcess.LOG.error (e.getMessage ());
        }
        return d;
    }

    public void setFile (final String file) {
        this.filename = file;
    }

    public void setLocale (final Locale l) {
        this.locale = l;
    }
}
