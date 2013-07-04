package org.toilelibre.libe.scrabble.modelfactory.dictionary.factory;

import java.util.Locale;

import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;

public interface IDictionaryFactory {
	Dictionary getDictionary ();

	Locale getLocale ();

	void load ();
}
