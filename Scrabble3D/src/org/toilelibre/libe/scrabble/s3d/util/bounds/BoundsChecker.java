package org.toilelibre.libe.scrabble.s3d.util.bounds;

import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.ILetterBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.IPoint3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;

public final class BoundsChecker {
    public static final double BOARD_CELL_HEIGHT = 1.67;
    public static final double BOARD_CELL_WIDTH  = 1.59;
    public static final double BOARD_OVER_X1     = -9.62;
    public static final double BOARD_OVER_X2     = 12;
    public static final double BOARD_OVER_Y1     = -4;
    public static final double BOARD_OVER_Y2     = 4;
    public static final double BOARD_OVER_Z1     = -9.62;
    public static final double BOARD_OVER_Z2     = 13.8;

    public static boolean isOverBoard (final double x, final double y,
            final double z) {
        final IPoint3D sp3d = S3DHelper.newPoint3d (new Double (x), new Double (
                y), new Double (z));
        return BoundsChecker.isOverBoard (sp3d);
    }

    public static boolean isOverBoard (final ILetterBranchGroup lbg) {
        final ITransform st = S3DHelper.newTransform ((Object []) null);
        if (lbg == null) {
            return false;
        }
        lbg.getTransformGroup ().getTransform (st);
        return BoundsChecker.isOverBoard (st.getX (), st.getY (), st.getZ ());
    }

    public static boolean isOverBoard (final IPoint3D sp3d) {
        if (sp3d == null) {
            return false;
        }
        return (sp3d.getX () >= BoundsChecker.BOARD_OVER_X1)
                && (sp3d.getX () <= BoundsChecker.BOARD_OVER_X2)
                && (sp3d.getZ () >= BoundsChecker.BOARD_OVER_Z1)
                && (sp3d.getZ () <= BoundsChecker.BOARD_OVER_Z2)
                && (sp3d.getY () >= BoundsChecker.BOARD_OVER_Y1)
                && (sp3d.getY () <= BoundsChecker.BOARD_OVER_Y2);
    }

    private BoundsChecker() {

    }

}
