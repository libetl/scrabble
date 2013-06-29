/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.ICountScoreComponent;
import org.toilelibre.libe.scrabble.model.board.placements.Insertion;

public final class CountScoreComponent extends AbstractComponent implements
        ICountScoreComponent {

    public CountScoreComponent () {

    }

    /**
     * @see org.toilelibre.libe.scrabble.component.iface.ICountScoreComponent
     *      #count(org.toilelibre.libe.scrabble.model.board.placements.Insertion)
     */
    public int count (final Insertion i1) {
        return 0;
    }

}
