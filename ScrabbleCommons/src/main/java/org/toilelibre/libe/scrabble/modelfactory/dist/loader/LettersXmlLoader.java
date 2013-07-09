package org.toilelibre.libe.scrabble.modelfactory.dist.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.model.dist.BallotBox;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public final class LettersXmlLoader {

	private static final Logger LOG = Logger.getLogger(LettersXmlLoader.class);

	public static void load(final BallotBox bb, final String xmlFileName) {
		try {
			final URL u = Thread.currentThread().getContextClassLoader()
					.getResource(xmlFileName);
			final XMLReader xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(new LettersXmlContentHandler(bb));
			xr.parse(new InputSource(u.openStream()));
		} catch (final SAXException e) {
			LettersXmlLoader.LOG.error(e.getMessage());
		} catch (final FileNotFoundException e) {
			LettersXmlLoader.LOG.error(e.getMessage());
		} catch (final IOException e) {
			LettersXmlLoader.LOG.error(e.getMessage());
		}
	}

	private LettersXmlLoader() {

	}
}
