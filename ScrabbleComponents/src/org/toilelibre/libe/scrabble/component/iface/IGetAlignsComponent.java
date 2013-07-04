/**
 * 
 */
package org.toilelibre.libe.scrabble.component.iface;

import org.toilelibre.libe.scrabble.component.IComponent;
import org.toilelibre.libe.scrabble.s3d.model.ILetterBranchGroup;

public interface IGetAlignsComponent extends IComponent {
	void get (ILetterBranchGroup [] lbgs, int [][] alignment, char [] letters);
}
