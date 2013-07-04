/**
 * 
 */
package org.toilelibre.libe.scrabble.component.iface;

import org.toilelibre.libe.scrabble.component.IComponent;
import org.toilelibre.libe.scrabble.model.board.placements.Insertion;

public interface ICountScoreComponent extends IComponent {

	int count (Insertion i);
}
