/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.IGetAlignsComponent;
import org.toilelibre.libe.scrabble.s3d.model.ILetterBranchGroup;
import org.toilelibre.libe.scrabble.s3d.util.bounds.AlignOnBoard;

public final class GetAlignsComponent extends AbstractComponent implements
		IGetAlignsComponent {

	/**
   * 
   */
	public GetAlignsComponent() {
	}

	@Override
	public void get(final ILetterBranchGroup[] lbgs, final int[][] alignment,
			final char[] letters) {
		for (int j = 0; j < lbgs.length; j++) {
			final ILetterBranchGroup lbg = lbgs[j];
			alignment[j] = AlignOnBoard.align(lbg);
			letters[j] = lbg.getLetter();
		}
	}
}
