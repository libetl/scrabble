package org.toilelibre.libe.scrabble.model.dist;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.toilelibre.libe.scrabble.model.ModelElement;

public class BallotBox implements ModelElement {
    private final Map<String, Letter> letters;
    private String                    lettersString;
    private Locale                    locale;

    public BallotBox() {
        this.letters = new HashMap<String, Letter> ();
        this.locale = Locale.getDefault ();
        this.lettersString = "";
    }

    public BallotBox(final Locale l) {
        this.letters = new HashMap<String, Letter> ();
        this.locale = l;
        this.lettersString = "";
    }

    public final void addLetter (final Letter l) {
        final Letter l2 = this.letters.get (l);
        if (l2 == null) {
            this.setLetter (l);
        } else {
            l2.setNumber (l2.getNumber () + l.getNumber ());
        }
    }

    public final Locale getLocale () {
        return this.locale;
    }

    public final int getNumber (final char l) {
        return this.letters.get ("" + l).getNumber ();
    }

    public final int getValue (final char l) {
        return this.letters.get ("" + l).getValue ();
    }

    public final boolean isAvailable (final char l) {
        return this.getNumber (l) > 0;
    }

    public final Letter pick () {
        boolean found = false;
        Letter l = null;
        while (!found) {
            final int random = (int) (Math.random () * this.lettersString
                    .length ());
            final char charRandom = this.lettersString.charAt (random);
            if (this.letters.get (charRandom + "").isAvailable ()) {
                l = this.letters.get (charRandom + "");
                found = true;
            }
        }
        if (found && (l != null)) {
            final int letterIndex = this.lettersString.indexOf (l.getName ());
            l.descrNumber ();
            this.lettersString = this.lettersString.substring (0, letterIndex)
                    + this.lettersString.substring (letterIndex + 1);
        }
        return l;
    }

    public final void setLetter (final Letter l) {
        this.letters.put ("" + l.getName (), l);
        for (int i = 0 ; i < l.getNumber () ; i++) {
            this.lettersString += l.getName ();
        }
    }

    public final void setLocale (final Locale locale2) {
        this.locale = locale2;
    }
}
