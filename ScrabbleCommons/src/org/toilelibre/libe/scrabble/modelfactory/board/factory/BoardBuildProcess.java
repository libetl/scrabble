package org.toilelibre.libe.scrabble.modelfactory.board.factory;

import java.util.Locale;

import org.toilelibre.libe.scrabble.model.ModelElement;
import org.toilelibre.libe.scrabble.model.board.Board;
import org.toilelibre.libe.scrabble.modelfactory.BuildProcess;
import org.toilelibre.libe.scrabble.modelfactory.LocaleChecker;
import org.toilelibre.libe.scrabble.modelfactory.board.loader.BoardXmlLoader;

public class BoardBuildProcess implements BuildProcess {

	private Locale	locale;
	private String	xmlFileName;

	public BoardBuildProcess () {

	}

	public final String getFile () {
		return this.xmlFileName;
	}

	public final Locale getLocale () {
		return this.locale;
	}

	public final ModelElement process () {
		final Board board = new Board ();
		final Locale l = LocaleChecker.check (this.locale, this.xmlFileName);
		if (l == null) {
			return null;
		}
		board.setLocale (l);
		BoardXmlLoader.load (board, this.xmlFileName);
		return board;
	}

	public final void setFile (final String file) {
		this.xmlFileName = file;
	}

	public final void setLocale (final Locale l) {
		this.locale = l;
	}

}
