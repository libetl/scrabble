package org.toilelibre.libe.scrabble.model.dictionary;

import java.util.List;
import java.util.Locale;

import org.toilelibre.libe.scrabble.model.ModelElement;

public interface Dictionary extends ModelElement {

    void add (String word);

    boolean contains (String word);

    Object getImpl ();

    @Override
    Locale getLocale ();

    void setLocale (Locale l);

    List<String> similarWords (String word);

}
