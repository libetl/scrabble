package org.toilelibre.libe.scrabble.model.board.placements;

public class Placement {
	private final char	letter;
	private final int	x;
	private final int	y;

	public Placement (final char letter1, final int x1, final int y1) {
		super ();
		this.letter = letter1;
		this.x = x1;
		this.y = y1;
	}

	public final char getLetter () {
		return this.letter;
	}

	public final int getX () {
		return this.x;
	}

	public final int getY () {
		return this.y;
	}

	@Override
	public final String toString () {
		return "[" + this.letter + "] : {" + this.x + ", " + this.y + "}";
	}

}
