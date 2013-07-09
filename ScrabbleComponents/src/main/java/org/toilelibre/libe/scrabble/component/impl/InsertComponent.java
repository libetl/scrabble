/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.IInsertComponent;
import org.toilelibre.libe.scrabble.model.board.Board;
import org.toilelibre.libe.scrabble.model.board.placements.Insertion;
import org.toilelibre.libe.scrabble.model.board.placements.Placement;

public class InsertComponent extends AbstractComponent implements
		IInsertComponent {

	/**
   * 
   */
	public InsertComponent() {
	}

	/**
	 * @see org.toilelibre.libe.scrabble.component.iface.IInsertComponent#insert
	 *      (org.toilelibre.libe.scrabble.model.board.placements.Insertion)
	 */
	@Override
	public final void insert(final Insertion i) {

		final Board b = this.getData().getBoards().get(0);

		for (final Placement p : i) {
			b.setCellLetter(p.getX(), p.getY(), p.getLetter());
		}
	}

}
