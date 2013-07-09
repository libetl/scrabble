package org.toilelibre.libe.scrabble.s3d.util.move;

import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.IBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;
import org.toilelibre.libe.scrabble.s3d.model.IPoint3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;
import org.toilelibre.libe.scrabble.s3d.util.bounds.AlignOnChevalet;
import org.toilelibre.libe.scrabble.s3d.util.bounds.BoundsChecker;
import org.toilelibre.libe.scrabble.s3d.util.bounds.TurnSpecificAlign;
import org.toilelibre.libe.scrabble.s3d.util.scene.SceneHelper;

public class MoveLetter implements IMoveObject {

    public MoveLetter() {

    }

    private void alignOverBoard (final ICanvas3D sc3d,
            final ITransformGroup stg, final IPoint3D sp3dPointer,
            final int turn) {
        final ITransform stCamera = S3DHelper.newTransform ((Object []) null);

        final ITransform st = S3DHelper.newTransform ((Object []) null);
        sc3d.getCameraTransformGroup ().getTransform (stCamera);
        stg.getTransform (st);

        final IPoint3D sp3dCamera = S3DHelper.newPoint3d (
                new Double (stCamera.getX ()), new Double (stCamera.getY ()),
                new Double (stCamera.getZ ()));

        final IPoint3D sp3dLetter = S3DHelper.newPoint3d (
                new Double (st.getX ()), new Double (st.getY ()), new Double (
                        st.getZ ()));

        double diffX = sp3dPointer.getX () - sp3dLetter.getX ();
        final double point1 = 0.1;
        final double diffY = point1 - st.getY ();
        double diffZ = sp3dPointer.getZ () - sp3dLetter.getZ ();

        final double [] fZOfY = this.computeFunction (sp3dPointer.getZ (),
                sp3dCamera.getZ (), sp3dPointer.getY (), sp3dCamera.getY ());
        final double [] fXOfY = this.computeFunction (sp3dPointer.getX (),
                sp3dCamera.getX (), sp3dPointer.getY (), sp3dCamera.getY ());

        final double newX = (point1 * fXOfY [0]) + fXOfY [1];
        final double newZ = (point1 * fZOfY [0]) + fZOfY [1];
        final double oldX = sp3dLetter.getX ();
        final double oldZ = sp3dLetter.getZ ();

        diffX = newX - oldX;
        diffZ = newZ - oldZ;

        TurnSpecificAlign.alignOverBoard (stg, turn);
        SceneHelper.translate (stg, diffX, diffY, diffZ);
    }

    private double [] computeFunction (final double yEnd, final double yStart,
            final double xEnd, final double xStart) {
        final double coef = (yEnd - yStart) / (xEnd - xStart);
        final double offset = yEnd - (coef * xEnd);
        return new double [] { coef, offset };
    }

    @Override
    public final void move (final ICanvas3D sc3d, final ITransformGroup stg,
            final int... params) {
        final int trois = 3;
        final int cinq = 5;
        final int ax = params [1];
        final int ay = params [trois];
        final int turn = params [cinq];
        final IBranchGroup sbgOtherLetter = S3DHelper.getScenePicker ()
                .getNameAt (sc3d, "LetterBranchGroup", ax, ay);
        final IPoint3D sp3dPointer = sc3d.getPickedCoordinate (ax, ay);
        final boolean overBoard = BoundsChecker.isOverBoard (sp3dPointer);
        final boolean overChevalet = TurnSpecificAlign.isOverChevalet (
                sp3dPointer, turn);
        if ( (sbgOtherLetter != null)
                && !stg.isTheSame (sbgOtherLetter.getTransformGroup ())) {
            final ITransform stOtherLetter = S3DHelper
                    .newTransform ((Object []) null);

            final ITransformGroup stgOtherLetter = sbgOtherLetter
                    .getTransformGroup ();
            stgOtherLetter.getTransform (stOtherLetter);
            final boolean otherLetterChevalet = TurnSpecificAlign
                    .isOverChevalet (stOtherLetter.getX (),
                            stOtherLetter.getY (), stOtherLetter.getZ (), turn);
            if (otherLetterChevalet) {
                AlignOnChevalet.swap (stg, stgOtherLetter, turn);
            }
        } else if (overChevalet) {
            AlignOnChevalet.align (stg, turn);
        } else if (overBoard) {
            this.alignOverBoard (sc3d, stg, sp3dPointer, turn);
        }
    }

}
