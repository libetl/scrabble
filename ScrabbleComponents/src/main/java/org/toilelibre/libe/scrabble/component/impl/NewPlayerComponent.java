/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.INewPlayerComponent;
import org.toilelibre.libe.scrabble.model.ScrabbleData;
import org.toilelibre.libe.scrabble.model.player.Player;

public final class NewPlayerComponent extends AbstractComponent implements
		INewPlayerComponent {
	private static final Logger LOG = Logger
			.getLogger(NewPlayerComponent.class);

	/**
   * 
   */
	public NewPlayerComponent() {
	}

	/**
	 * @see org.toilelibre.libe.scrabble.component.iface.INewPlayerComponent
	 *      #newPlayers(java.lang.String[], java.lang.Boolean[])
	 */
	@Override
	public int newPlayers(final String[] players1, final Boolean[] computers1) {
		String player1Name = players1[0];
		final Boolean computerPlayer1 = computers1[0];
		final ScrabbleData sd = this.getData();
		if ((player1Name == null) || (player1Name.length() == 0)) {
			player1Name = "???";
		}

		sd.getPlayers().add(
				new Player(player1Name, computerPlayer1.booleanValue()));
		for (int i = 1; i < players1.length; i++) {
			if ((players1[i] != null) && (players1[i].length() > 0)) {
				sd.getPlayers().add(
						new Player(players1[i], computers1[i].booleanValue()));
			}
		}
		NewPlayerComponent.LOG.info("Début de partie à "
				+ sd.getPlayers().size() + " joueur(s).");

		return sd.getPlayers().size();
	}

}
