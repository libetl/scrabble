/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.IStoreScoreComponent;
import org.toilelibre.libe.scrabble.model.ScrabbleData;

public class StoreScoreComponent extends AbstractComponent implements
        IStoreScoreComponent {
    private static final Logger LOG = Logger.getLogger (StoreScoreComponent.class);

    /**
   * 
   */
    public StoreScoreComponent () {
    }

    /**
     * @see org.toilelibre.libe.scrabble.component.iface.IStoreScoreComponent
     *      #store(int)
     */
    public final void store (final int turn, final String word, final int score) {

        final ScrabbleData sd = this.getData ();
        sd.getPlayers ().get (turn).addTurn (word, score);
        StoreScoreComponent.LOG.info (sd.getPlayers ().get (turn).getName ()
                + " : {'" + word + "', " + score + " point(s)}");
    }

}
