package org.toilelibre.libe.scrabble.modelfactory.board.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.model.board.Board;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public final class BoardXmlLoader {
    private static final Logger LOG = Logger.getLogger (BoardXmlLoader.class);

    public static void load (final Board board, final String xmlFileName) {
        try {
            final URL u = Thread.currentThread ().getContextClassLoader ()
                    .getResource (xmlFileName);
            final XMLReader xr = XMLReaderFactory.createXMLReader ();
            xr.setContentHandler (new BoardXmlContentHandler (board));
            xr.parse (new InputSource (u.openStream ()));
        } catch (final SAXException e) {
            BoardXmlLoader.LOG.error (e.getMessage ());
        } catch (final FileNotFoundException e) {
            BoardXmlLoader.LOG.error (e.getMessage ());
        } catch (final IOException e) {
            BoardXmlLoader.LOG.error (e.getMessage ());
        }
    }

    private BoardXmlLoader() {

    }
}
