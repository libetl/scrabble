package org.toilelibre.libe.scrabble.s3d.util.expression;

public class Node {
	private Node	left;
	private String	letter;
	private Node	right;

	public Node () {

	}

	public final Node getLeft () {
		return this.left;
	}

	public final String getLetter () {
		return this.letter;
	}

	public final Node getRight () {
		return this.right;
	}

	public final void setLeft (final Node left1) {
		this.left = left1;
	}

	public final void setLetter (final String letter1) {
		this.letter = letter1;
	}

	public final void setRight (final Node right1) {
		this.right = right1;
	}
}
