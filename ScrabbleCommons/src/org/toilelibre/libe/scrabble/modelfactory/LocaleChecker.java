package org.toilelibre.libe.scrabble.modelfactory;

import java.util.Locale;

public final class LocaleChecker {

	public static Locale check (final Locale l, final String xmlFileName) {
		Locale res = null;
		Locale l2 = l;
		final int slashIndex = xmlFileName.lastIndexOf ('/');
		final int dotIndex = xmlFileName.indexOf ('.');
		if (dotIndex != -1) {
			final String localeName = xmlFileName.substring (slashIndex + 1,
			        dotIndex);
			final int underscoreIndex = localeName.indexOf ('_');
			if (underscoreIndex != -1) {
				final String language = localeName.substring (0,
				        underscoreIndex);
				final String country = localeName
				        .substring (underscoreIndex + 1);
				res = new Locale (language, country);
			} else {
				res = new Locale (localeName);
			}
		}
		if ("default".equals (l.getLanguage ())) {
			l2 = Locale.getDefault ();
		}
		if (!l2.equals (res)) {
			return null;
		}
		return res;
	}

	private LocaleChecker () {

	}
}
