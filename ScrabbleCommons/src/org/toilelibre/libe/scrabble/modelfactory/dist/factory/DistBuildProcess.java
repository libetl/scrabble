package org.toilelibre.libe.scrabble.modelfactory.dist.factory;

import java.util.Locale;

import org.toilelibre.libe.scrabble.model.ModelElement;
import org.toilelibre.libe.scrabble.model.dist.BallotBox;
import org.toilelibre.libe.scrabble.modelfactory.BuildProcess;
import org.toilelibre.libe.scrabble.modelfactory.LocaleChecker;
import org.toilelibre.libe.scrabble.modelfactory.dist.loader.LettersXmlLoader;

public final class DistBuildProcess implements BuildProcess {

	private Locale	locale;
	private String	xmlFileName;

	private DistBuildProcess () {
	}

	public String getFile () {
		return this.xmlFileName;
	}

	public Locale getLocale () {
		return this.locale;
	}

	public ModelElement process () {
		final BallotBox bb = new BallotBox ();
		final Locale l = LocaleChecker.check (this.locale, this.xmlFileName);
		if (l == null) {
			return null;
		}
		bb.setLocale (l);
		LettersXmlLoader.load (bb, this.xmlFileName);
		return bb;
	}

	public void setFile (final String file) {
		this.xmlFileName = file;
	}

	public void setLocale (final Locale l) {
		this.locale = l;
	}

}
