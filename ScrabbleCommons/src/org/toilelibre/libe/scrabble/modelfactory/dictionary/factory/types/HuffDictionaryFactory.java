package org.toilelibre.libe.scrabble.modelfactory.dictionary.factory.types;

import org.toilelibre.libe.scrabble.modelfactory.dictionary.factory.AbstractDictionaryFactory;

public class HuffDictionaryFactory extends AbstractDictionaryFactory {

    private static final String DEFAULT_BUILDER = "Tree";

    public HuffDictionaryFactory (final String filename) {
        super.setType (HuffDictionaryFactory.DEFAULT_BUILDER);
        super.setFile (filename);
    }

    public HuffDictionaryFactory (final String filename, final String dictType1) {
        super.setType (dictType1);
        super.setFile (filename);
    }

    @Override
    public final String getExtension () {
        return "huff";
    }

}
