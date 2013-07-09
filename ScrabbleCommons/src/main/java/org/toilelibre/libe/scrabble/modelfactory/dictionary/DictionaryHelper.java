package org.toilelibre.libe.scrabble.modelfactory.dictionary;

import java.util.Locale;

import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;
import org.toilelibre.libe.scrabble.modelfactory.BuildProcessException;

public final class DictionaryHelper {

	public static Dictionary instantiateDictionary(final String type,
			final Locale l) throws BuildProcessException {
		try {
			final String simpleName = type.substring(0, 1).toUpperCase()
					+ type.substring(1) + "Dictionary";
			final String className = Dictionary.class.getPackage().getName()
					+ ".impl." + simpleName;
			Dictionary dict;

			dict = (Dictionary) Class.forName(className).newInstance();
			dict.setLocale(l);
			return dict;
		} catch (final InstantiationException e) {
			throw new BuildProcessException(e);
		} catch (final IllegalAccessException e) {
			throw new BuildProcessException(e);
		} catch (final ClassNotFoundException e) {
			throw new BuildProcessException(e);
		}
	}

	private DictionaryHelper() {

	}

}
