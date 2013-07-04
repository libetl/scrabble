package org.toilelibre.libe.scrabble.model.board;

import java.util.Locale;

import org.toilelibre.libe.scrabble.model.ModelElement;

public class Board implements ModelElement {
	public static final int	   COLS	          = 15;
	public static final char	DL	          = 1;
	public static final char	DW	          = 3;
	public static final char	EM	          = 0;
	public static final String	NOT_ALIGNED	  = "Placement non aligné ";
	public static final String	NOT_CONTINUED	= "Placement discontinu";
	public static final int	   ROWS	          = 15;
	public static final char	TL	          = 2;
	public static final char	TW	          = 4;
	private final char [][]	   letters;
	private Locale	           locale;
	private final char [][]	   points;

	public Board () {
		this.points = new char [Board.COLS] [Board.ROWS];
		this.letters = new char [Board.COLS] [Board.ROWS];
		this.locale = Locale.getDefault ();
	}

	public Board (final Locale l) {
		this.points = new char [Board.COLS] [Board.ROWS];
		this.letters = new char [Board.COLS] [Board.ROWS];
		this.locale = l;
	}

	public final char getCellLetter (final int col, final int row) {
		return this.letters [col] [row];
	}

	public final char getCellPoints (final int col, final int row) {
		return this.points [col] [row];
	}

	public final Locale getLocale () {
		return this.locale;
	}

	public final void setCellLetter (final int col, final int row,
	        final char value) {
		this.letters [col] [row] = value;
	}

	public final void setCellPoints (final int col, final int row,
	        final int value) {
		this.points [col] [row] = (char) value;
	}

	public final void setLocale (final Locale locale1) {
		this.locale = locale1;
	}

	@Override
	public final String toString () {
		String res = "Board :\n";
		for (final char [] row : this.letters) {
			res += "[";
			for (final char cell : row) {
				if ( (cell < 'A') || (cell > 'Z')) {
					res += "_";
				} else {
					res += cell + "";
				}
			}
			res += "]\n";
		}
		return res;
	}
}
