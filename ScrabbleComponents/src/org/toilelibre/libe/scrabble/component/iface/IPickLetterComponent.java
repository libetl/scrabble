/**
 * 
 */
package org.toilelibre.libe.scrabble.component.iface;

import org.toilelibre.libe.scrabble.component.IComponent;

public interface IPickLetterComponent extends IComponent {

	Character pick (int turn, int position);
}
