package org.toilelibre.libe.userinteractions.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.toilelibre.libe.userinteractions.loader.parser.UIXmlContentHandler;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public final class UILoader {
	private static final Logger LOG = Logger.getLogger(UILoader.class);

	public static void load(final UserInteractions ui, final String xmlFileName) {
		try {
			final URL url = Thread.currentThread().getContextClassLoader()
					.getResource(xmlFileName);
			final XMLReader xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(new UIXmlContentHandler(ui));
			xr.parse(new InputSource(new FileInputStream(new File(url.toURI()))));
		} catch (final SAXException e) {
			UILoader.LOG.error(e.getMessage());
		} catch (final FileNotFoundException e) {
			UILoader.LOG.error(e.getMessage());
		} catch (final IOException e) {
			UILoader.LOG.error(e.getMessage());
		} catch (final URISyntaxException e) {
			UILoader.LOG.error(e.getMessage());
		}
	}

	private UILoader() {

	}
}
