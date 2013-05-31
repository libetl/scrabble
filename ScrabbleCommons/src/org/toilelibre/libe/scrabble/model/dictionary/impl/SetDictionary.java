package org.toilelibre.libe.scrabble.model.dictionary.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;
import org.toilelibre.libe.scrabble.model.dictionary.combinations.Combinations;

public class SetDictionary implements Dictionary
{
  private Locale                          locale;
  private final Map<String, List<String>> sortedLettersHm;

  public SetDictionary ()
  {
    this.sortedLettersHm = new HashMap<String, List<String>> ();
  }

  public final void add (final String word)
  {
    final char [] table = new char [word.length ()];
    for (int i = 0; i < table.length; i += 1)
    {
      table[i] = word.charAt (i);
    }
    Arrays.sort (table);
    final String sTable = new String (table);
    List<String> lHomonymes = this.sortedLettersHm.get (sTable);
    if (lHomonymes == null)
    {
      lHomonymes = new LinkedList<String> ();
      this.sortedLettersHm.put (new String (table), lHomonymes);
    }
    lHomonymes.add (word);
  }

  public final boolean contains (final String word)
  {
    final char [] sortedMatch = word.toCharArray ();
    String sorted;
    Arrays.sort (sortedMatch);
    sorted = new String (sortedMatch);
    final List<String> listeResultats = this.sortedLettersHm.get (sorted);
    return (listeResultats != null) && listeResultats.contains (word);
  }

  public final Object getImpl ()
  {
    return this.sortedLettersHm;
  }

  public final Locale getLocale ()
  {
    return this.locale;
  }

  public final void setLocale (final Locale locale1)
  {
    this.locale = locale1;
  }

  public final List<String> similarWords (final String word)
  {
    final Set<String> similarWords = new HashSet<String> ();
    final Set<String> sortedMatches = Combinations.listMatches (word);

    for (final String string : sortedMatches)
    {
      final List<String> ls = this.sortedLettersHm.get (string);
      if (ls != null)
      {
        similarWords.addAll (ls);
      }
    }
    final String [] sortedWords = new String [similarWords.size ()];
    similarWords.toArray (sortedWords);
    Arrays.sort (sortedWords);

    return Arrays.asList (sortedWords);
  }
}
