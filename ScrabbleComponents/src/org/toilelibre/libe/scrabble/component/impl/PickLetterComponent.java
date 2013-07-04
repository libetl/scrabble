/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.IPickLetterComponent;
import org.toilelibre.libe.scrabble.model.ScrabbleData;
import org.toilelibre.libe.scrabble.model.dist.Letter;
import org.toilelibre.libe.scrabble.model.tray.Tray;

public class PickLetterComponent extends AbstractComponent implements
        IPickLetterComponent {

	/**
   * 
   */
	public PickLetterComponent () {
	}

	/**
	 * @see org.toilelibre.libe.scrabble.component.iface.IPickLetterComponent
	 *      #pick()
	 */
	public final Character pick (final int turn, final int position) {
		final ScrabbleData sd = this.getData ();
		final Tray t = sd.getPlayers ().get (turn).getTray ();
		if (t.getLetter (position) == 0) {
			final Letter l = sd.getBallotBoxes ().get (0).pick ();
			t.setLetter (position, l.getName ());
			return new Character (l.getName ());
		}
		return null;
	}

}
