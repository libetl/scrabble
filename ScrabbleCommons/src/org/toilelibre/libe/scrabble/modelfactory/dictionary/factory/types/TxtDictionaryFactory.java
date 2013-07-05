package org.toilelibre.libe.scrabble.modelfactory.dictionary.factory.types;

import org.toilelibre.libe.scrabble.modelfactory.dictionary.factory.AbstractDictionaryFactory;

public class TxtDictionaryFactory extends AbstractDictionaryFactory {

    private static final String DEFAULT_BUILDER = "Tree";

    public TxtDictionaryFactory(final String filename) {
        super.setType(TxtDictionaryFactory.DEFAULT_BUILDER);
        super.setFile(filename);
    }

    public TxtDictionaryFactory(final String filename, final String dictType1) {
        super.setType(dictType1);
        super.setFile(filename);
    }

    @Override
    public final String getExtension () {
        return "txt";
    }

}
