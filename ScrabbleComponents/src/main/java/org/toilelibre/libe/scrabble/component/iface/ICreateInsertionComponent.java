/**
 * 
 */
package org.toilelibre.libe.scrabble.component.iface;

import org.toilelibre.libe.scrabble.component.IComponent;
import org.toilelibre.libe.scrabble.model.board.placements.Insertion;

public interface ICreateInsertionComponent extends IComponent {

    Insertion createObject (int [][] alignment, char [] letters);
}
