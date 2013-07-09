package org.toilelibre.libe.scrabble.modelfactory.dictionary.loader;

import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;

public interface Loader {

	void load(Dictionary d, String fileName);
}
