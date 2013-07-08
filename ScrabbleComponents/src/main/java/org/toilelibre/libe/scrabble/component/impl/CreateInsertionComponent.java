/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.ICreateInsertionComponent;
import org.toilelibre.libe.scrabble.model.board.placements.Insertion;
import org.toilelibre.libe.scrabble.model.board.placements.Placement;

public final class CreateInsertionComponent extends AbstractComponent implements
        ICreateInsertionComponent {

    /**
   * 
   */
    public CreateInsertionComponent() {
    }

    /**
     * @see org.toilelibre.libe.scrabble.component.iface.ICreateInsertionComponent
     *      #createObject(int[][],char[])
     */
    public Insertion createObject (final int [][] alignment,
            final char [] letters) {

        final Insertion i = new Insertion ();
        for (int j = 0 ; j < alignment.length ; j++) {
            final int [] res = alignment [j];
            if (res != null) {
                i.addPlacement (new Placement (letters [j], res [0], res [1]));
            }
        }
        return i;
    }

}
