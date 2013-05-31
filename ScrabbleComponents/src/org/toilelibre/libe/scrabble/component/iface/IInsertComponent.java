/**
 * 
 */
package org.toilelibre.libe.scrabble.component.iface;

import org.toilelibre.libe.scrabble.component.IComponent;
import org.toilelibre.libe.scrabble.model.board.placements.Insertion;

/**
 * @author E5184343
 * 
 */
public interface IInsertComponent extends IComponent
{

  void insert (Insertion i);
}
