package org.toilelibre.libe.scrabble.s3d.util.bounds;

import org.toilelibre.libe.scrabble.s3d.exception.S3DException;
import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.ILetterBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;

public final class AlignOnBoard {

    public static int [] align (final ILetterBranchGroup lbg) {
        final int sept = 7;
        final double zeroSept = 0.7;
        final double zeroCinq = 0.5;
        boolean align = true;
        final int [] res = new int [2];
        final ITransform st = S3DHelper.newTransform ((Object []) null);
        if (lbg != null) {
            lbg.getTransformGroup ().getTransform (st);
            align = BoundsChecker.isOverBoard (st.getX (), st.getY (),
                    st.getZ ());
        }
        if ( (lbg == null) || !align) {
            return null;
        }

        double offsetX = (st.getX () - BoundsChecker.BOARD_OVER_X1)
                + (BoundsChecker.BOARD_CELL_WIDTH / 2);
        final double offsetZ = (st.getZ () - BoundsChecker.BOARD_OVER_Z1)
                + (BoundsChecker.BOARD_CELL_HEIGHT / 2);

        res [0] = (int) (offsetX / BoundsChecker.BOARD_CELL_WIDTH);
        res [1] = (int) (offsetZ / BoundsChecker.BOARD_CELL_HEIGHT);

        if (res [0] == sept) {
            offsetX += zeroCinq;
            res [0] = (int) (offsetX / BoundsChecker.BOARD_CELL_WIDTH);
        }
        if (res [0] > sept) {
            offsetX += zeroSept;
            res [0] = (int) (offsetX / BoundsChecker.BOARD_CELL_WIDTH);
        }

        double newOffsetX = res [0] * BoundsChecker.BOARD_CELL_WIDTH;
        final double newOffsetZ = res [1] * BoundsChecker.BOARD_CELL_HEIGHT;
        if (res [0] == sept) {
            newOffsetX -= zeroCinq;
        }
        if (res [0] > sept) {
            newOffsetX -= zeroSept;
        }

        final double newX = newOffsetX + BoundsChecker.BOARD_OVER_X1;
        final double newZ = newOffsetZ + BoundsChecker.BOARD_OVER_Z1;

        st.setTranslation (newX, st.getY (), newZ);
        try {
            lbg.getTransformGroup ().setTransform (st);
        } catch (final S3DException e) {
            e.hashCode ();
        }

        return res;
    }

    private AlignOnBoard () {

    }

}
