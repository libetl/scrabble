package org.toilelibre.libe.scrabble.model.dictionary.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;
import org.toilelibre.libe.scrabble.model.dictionary.impl.tree.Node;

public class TreeDictionary implements Dictionary {
	private Locale locale;

	private final Node root;

	public TreeDictionary() {
		this.root = new Node('\0');
	}

	private void add(final Node n, final String word) {
		if (word.length() > 0) {
			final int index = n.getChildNum(word.charAt(0));
			if (index != -1) {
				this.add(n.getChildNum(index), word.substring(1));
			} else {
				final Node sn = new Node(word.charAt(0));
				n.addChild(sn);
				this.callIfWordLengthGtOne(word, sn);
			}
		}
	}

	@Override
	public final void add(final String word) {
		if (word != null) {
			this.add(this.root, word);
		}
	}

	private void callIfWordLengthGtOne(final String word, final Node sn) {
		if (word.length() > 1) {
			this.add(sn, word.substring(1));
		} else {
			sn.setTerm(true);
		}

	}

	private boolean contains(final Node n, final String word) {
		return (word == null)
				|| (word.length() == 0)
				|| ((n.getChildNum(word.charAt(0)) != -1) && this.contains(
						n.getChild(word.charAt(0)), word.substring(1)));
	}

	@Override
	public final boolean contains(final String word) {
		return this.contains(this.root, word);
	}

	private List<String> findMatches(final String word, final Node n,
			final int numLettersMissing, final String debutMot) {
		final List<String> res = new LinkedList<String>();
		if (n.isTerm() && (debutMot.length() > 1)) {
			res.add(debutMot);
		}
		for (final Node cN : n.getChildNodes()) {
			if (word.indexOf(cN.getValue()) != -1) {
				final String word2 = word.substring(0,
						word.indexOf(cN.getValue()))
						+ word.substring(word.indexOf(cN.getValue()) + 1);
				res.addAll(this.findMatches(word2, cN, numLettersMissing,
						debutMot + cN.getValue()));
			} else if (numLettersMissing > 0) {
				res.addAll(this.findMatches(word, cN, numLettersMissing - 1,
						debutMot + cN.getValue()));
			}
		}
		return res;
	}

	@Override
	public final Object getImpl() {
		return this.root;
	}

	@Override
	public final Locale getLocale() {
		return this.locale;
	}

	@Override
	public final void setLocale(final Locale locale1) {
		this.locale = locale1;
	}

	@Override
	public final List<String> similarWords(final String word) {
		int numLettersMissing = 1;
		String word2 = word;
		while (word2.indexOf(' ') != -1) {
			numLettersMissing += 1;
			word2 = word2.substring(0, word.indexOf(' '))
					+ word2.substring(word.indexOf(' ') + 1);
		}

		return this.findMatches(word, this.root, numLettersMissing, "");
	}
}
