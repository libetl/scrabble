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
import org.toilelibre.libe.scrabble.model.dictionary.impl.tree.Node;
import org.toilelibre.libe.scrabble.modelfactory.dictionary.loader.Loader;

public final class HuffToTreeLoader implements Loader {

	private static final Logger LOG = Logger.getLogger(HuffToTreeLoader.class);

	public HuffToTreeLoader() {
		super();
	}

	@Override
	public void load(final Dictionary d, final String fileName) {

		InputStreamReader isr;
		try {
			final ClassLoader cld = Thread.currentThread()
					.getContextClassLoader();
			isr = new InputStreamReader(new FileInputStream(new File(cld
					.getResource(fileName).toURI())));
			final Node root = (Node) d.getImpl();
			this.load(root, isr, 0);
		} catch (final FileNotFoundException e) {
			HuffToTreeLoader.LOG.error(e.getMessage());
		} catch (final URISyntaxException e) {
			HuffToTreeLoader.LOG.error(e.getMessage());
		}
	}

	private Node load(final Node n, final Reader r, final int lastChar) {
		int val;
		try {
			val = r.read();
		} catch (final IOException e) {
			return n;
		}
		switch (val) {
		case '\0':
		case '*':
			n.setTerm(true);
			break;
		case '$':
			n.setTerm(true);
			this.load(n, r, val);
			break;
		case '/':
			break;
		default:
			Node n2 = n.getChild((char) val);
			if (n2 != null) {
				this.load(n2, r, val);
			} else {
				n2 = new Node((char) val);
				n.addChild(n2);
			}
		}
		return n;
	}

}
