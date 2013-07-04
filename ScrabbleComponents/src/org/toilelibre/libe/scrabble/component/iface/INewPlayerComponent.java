/**
 * 
 */
package org.toilelibre.libe.scrabble.component.iface;

import org.toilelibre.libe.scrabble.component.IComponent;

public interface INewPlayerComponent extends IComponent {

	int newPlayers (String [] players, Boolean [] computers);
}
