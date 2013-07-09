package org.toilelibre.libe.scrabble.modelfactory.dictionary.factory;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;
import org.toilelibre.libe.scrabble.modelfactory.BuildProcessException;
import org.toilelibre.libe.scrabble.modelfactory.dictionary.DictionaryHelper;
import org.toilelibre.libe.scrabble.modelfactory.dictionary.loader.Loader;

public abstract class AbstractDictionaryFactory implements IDictionaryFactory {

	private static final Logger LOG = Logger

	.getLogger(AbstractDictionaryFactory.class);

	private Dictionary dict;
	private String fileName;
	private Locale l;
	private String name;
	private String type;

	@Override
	public final Dictionary getDictionary() {
		return this.dict;
	}

	public abstract String getExtension();

	@Override
	public final Locale getLocale() {
		return this.l;
	}

	public final String getName() {
		return this.name;
	}

	@Override
	public final void load() {
		final String extension2 = this.getExtension().substring(0, 1)
				.toUpperCase()
				+ this.getExtension().substring(1);
		final String type2 = this.type.substring(0, 1).toUpperCase()
				+ this.type.substring(1);
		try {
			final Class<?> c = Class.forName(Loader.class.getPackage()
					.getName()
					+ ".types."
					+ extension2
					+ "To"
					+ type2
					+ "Loader");
			final Loader loader = (Loader) c.newInstance();
			loader.load(this.dict, this.fileName);

		} catch (final ClassNotFoundException e) {
			AbstractDictionaryFactory.LOG.error(e.getMessage());
		} catch (final InstantiationException e) {
			AbstractDictionaryFactory.LOG.error(e.getMessage());
		} catch (final IllegalAccessException e) {
			AbstractDictionaryFactory.LOG.error(e.getMessage());
		}
	}

	public final void setFile(final String filename) {
		final int indFinFolder = filename.lastIndexOf('/');
		final int indCodePays = filename.indexOf('-');
		final int indCodeLangue = filename.indexOf('_');
		final int indExtension = filename.lastIndexOf('.') + 1;
		try {
			if (!this.getExtension().toUpperCase()
					.equals(filename.substring(indExtension).toUpperCase())) {
				throw new IllegalArgumentException();
			}
			this.fileName = filename;
			this.l = new Locale(filename.substring(indCodePays + 1,
					indCodeLangue), filename.substring(indCodeLangue + 1,
					indExtension - 1));
			this.name = filename.substring(indFinFolder + 1, indCodePays);
			this.dict = DictionaryHelper.instantiateDictionary(this.type,
					this.l);
		} catch (final BuildProcessException e) {
			AbstractDictionaryFactory.LOG.error(e.getMessage());
		}
	}

	protected final void setType(final String t) {
		this.type = t;
	}
}
