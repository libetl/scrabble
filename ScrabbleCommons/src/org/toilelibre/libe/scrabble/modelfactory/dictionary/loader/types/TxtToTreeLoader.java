package org.toilelibre.libe.scrabble.modelfactory.dictionary.loader.types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;
import org.toilelibre.libe.scrabble.modelfactory.dictionary.loader.Loader;

public class TxtToTreeLoader implements Loader {
	private static final Logger LOG = Logger.getLogger(TxtToTreeLoader.class);

	public TxtToTreeLoader() {

	}

	public final void load(final Dictionary d, final String fileName) {
		BufferedReader bfr = null;
		try {
			String line;
			final ClassLoader cld = Thread.currentThread()
					.getContextClassLoader();
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(cld.getResource(fileName).toURI()))));
			do {
				line = bfr.readLine();
				d.add(line);
			} while (line != null);
		} catch (final FileNotFoundException e) {
			TxtToTreeLoader.LOG.error(e.getMessage());
		} catch (final IOException e) {
			TxtToTreeLoader.LOG.error(e.getMessage());
		} catch (final URISyntaxException e) {
			TxtToTreeLoader.LOG.error(e.getMessage());
		} finally {
			if (bfr != null) {
				try {
					bfr.close();
				} catch (final IOException e) {
					TxtToTreeLoader.LOG.error(e.getMessage());
				}
			}
		}
	}
}
