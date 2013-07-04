package org.toilelibre.libe.scrabble.s3d.util.bounds;

import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;

public final class AlignOnChevalet {
	public static void align (final ITransformGroup stg, final int turn) {
		final ITransform stLetter = S3DHelper.newTransform ((Object []) null);
		stg.getTransform (stLetter);
		TurnSpecificAlign.alignChevalet (stg, turn);
	}

	public static void swap (final ITransformGroup stg,
	        final ITransformGroup stgOtherLetter, final int turn) {

		final ITransform stLetter = S3DHelper.newTransform ((Object []) null);

		final ITransform stOtherLetter = S3DHelper
		        .newTransform ((Object []) null);
		stg.getTransform (stLetter);
		stgOtherLetter.getTransform (stOtherLetter);
		TurnSpecificAlign.alignChevalet (stg, turn);
		TurnSpecificAlign.swap (stg, stgOtherLetter, turn);
	}

	private AlignOnChevalet () {

	}

}
