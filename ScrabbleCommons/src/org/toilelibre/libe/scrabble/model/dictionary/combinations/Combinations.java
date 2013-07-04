package org.toilelibre.libe.scrabble.model.dictionary.combinations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class Combinations {

	private static List<String> getCombinaisons (final String word) {
		final List<String> combinaisons = new LinkedList<String> ();
		for (int i = word.length (); i > 1; i -= 1) {
			combinaisons.addAll (Combinations.getCombinaisons (word, i));
		}
		return combinaisons;
	}

	private static List<String> getCombinaisons (final String word,
	        final int num) {
		return Combinations.getCombinaisons (word, num, "");
	}

	private static List<String> getCombinaisons (final String word,
	        final int num, final String debutMot) {
		List<String> combinaison = null;
		if (num == 0) {
			combinaison = new LinkedList<String> ();
			combinaison.add (debutMot);
		} else if (word.length () >= num) {
			combinaison = new LinkedList<String> ();
			final List<String> la = Combinations.getCombinaisons (
			        word.substring (1), num - 1, debutMot + word.charAt (0));
			final List<String> lb = Combinations.getCombinaisons (
			        word.substring (1), num, debutMot);
			if (la != null) {
				combinaison.addAll (la);
			}
			if (lb != null) {
				combinaison.addAll (lb);
			}
		}
		return combinaison;
	}

	public static Set<String> listMatches (final String word) {
		final Set<String> sortedMatches = new HashSet<String> ();
		final Set<String> matches = new HashSet<String> ();
		int numBlanks = 0;
		int i = 0;
		final char [] charArray = word.toCharArray ();
		Arrays.sort (charArray);
		final String word2 = new String (charArray);
		matches.add (word2);
		while ( (i < word.length ()) && (word2.charAt (i) == ' ')) {
			numBlanks += 1;
			i += 1;
		}

		matches.addAll (Combinations.listMatches (word2, numBlanks + 1));

		for (final String string : matches) {
			final char [] sortedMatch = string.toCharArray ();
			Arrays.sort (sortedMatch);
			sortedMatches.add (new String (sortedMatch));
		}

		return sortedMatches;
	}

	private static Set<String> listMatches (final String word,
	        final int numLettersMissing) {
		final Set<String> matches = new HashSet<String> ();
		if (numLettersMissing == 0) {
			matches.add (word);
			matches.addAll (Combinations.getCombinaisons (word));
		} else {
			final int indexBlank = word.indexOf (' ');
			for (char ctmp = 'a'; ctmp <= 'z'; ctmp += 1) {
				String wordtemp = "";
				if (indexBlank == -1) {
					wordtemp = ctmp + word;
				} else if (indexBlank == 0) {
					wordtemp = ctmp + word.substring (indexBlank + 1);
				} else if (word.length () == indexBlank) {
					wordtemp = word.substring (0, indexBlank) + ctmp;
				} else {
					wordtemp = word.substring (0, indexBlank) + ctmp
					        + word.substring (indexBlank + 1);
				}
				matches.add (wordtemp);
				matches.addAll (Combinations.getCombinaisons (wordtemp));
				matches.addAll (Combinations.listMatches (wordtemp,
				        numLettersMissing - 1));
			}
		}
		return matches;
	}

	private Combinations () {

	}
}
