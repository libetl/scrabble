/**
 * 
 */
package org.toilelibre.libe.scrabble.component.iface;

import org.toilelibre.libe.scrabble.component.IComponent;

public interface IStoreScoreComponent extends IComponent {

	void store(int turn, String word, int score);
}
