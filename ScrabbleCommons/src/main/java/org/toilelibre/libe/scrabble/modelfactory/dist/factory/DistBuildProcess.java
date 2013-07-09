package org.toilelibre.libe.scrabble.modelfactory.dist.factory;

import java.util.Locale;

import org.toilelibre.libe.scrabble.model.ModelElement;
import org.toilelibre.libe.scrabble.model.dist.BallotBox;
import org.toilelibre.libe.scrabble.modelfactory.BuildProcess;
import org.toilelibre.libe.scrabble.modelfactory.LocaleChecker;
import org.toilelibre.libe.scrabble.modelfactory.dist.loader.LettersXmlLoader;

public final class DistBuildProcess implements BuildProcess {

	private Locale locale;
	private String xmlFileName;

	private DistBuildProcess() {
	}

	@Override
	public String getFile() {
		return this.xmlFileName;
	}

	@Override
	public Locale getLocale() {
		return this.locale;
	}

	@Override
	public ModelElement process() {
		final BallotBox bb = new BallotBox();
		final Locale l = LocaleChecker.check(this.locale, this.xmlFileName);
		if (l == null) {
			return null;
		}
		bb.setLocale(l);
		LettersXmlLoader.load(bb, this.xmlFileName);
		return bb;
	}

	@Override
	public void setFile(final String file) {
		this.xmlFileName = file;
	}

	@Override
	public void setLocale(final Locale l) {
		this.locale = l;
	}

}
