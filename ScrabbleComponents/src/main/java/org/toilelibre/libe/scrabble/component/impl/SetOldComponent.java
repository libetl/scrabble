/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.ISetOldComponent;

public final class SetOldComponent extends AbstractComponent implements
        ISetOldComponent {

    /**
   * 
   */
    public SetOldComponent () {
    }

    /**
     * @see org.toilelibre.libe.scrabble.component.iface.ISetOldComponent
     *      #setOld(int,int)
     */
    @Override
    public void setOld (final int turn, final int position1) {
        this.getData ().getPlayers ().get (turn).getTray ()
                .setLetter (position1, (char) 0);
    }

}
