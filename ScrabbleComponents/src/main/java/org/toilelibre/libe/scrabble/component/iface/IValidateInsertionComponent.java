/**
 * 
 */
package org.toilelibre.libe.scrabble.component.iface;

import org.toilelibre.libe.scrabble.component.IComponent;
import org.toilelibre.libe.scrabble.model.board.placements.Insertion;
import org.toilelibre.libe.scrabble.model.exception.ScrabbleModelException;

public interface IValidateInsertionComponent extends IComponent {

    String [] validate (Insertion i) throws ScrabbleModelException;
}
