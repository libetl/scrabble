package org.toilelibre.libe.scrabble.s3d.util.move;

import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.AbstractTransform;
import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;
import org.toilelibre.libe.scrabble.s3d.util.scene.SceneHelper;

public final class MoveCamera implements IMoveObject {
    public MoveCamera() {

    }

    public void move (final ICanvas3D sc3d, final ITransformGroup stg,
            final int... params) {
        final int trois = 3;
        final int quatre = 4;
        final int dix = 10;
        final double dixMille = 10000.0;
        final double centMille = 100000.0;
        final double unSixM = 1600000.;
        final int bp = params[0];
        final int ax = params[1];
        final int px = params[2];
        final int ay = params[trois];
        final int py = params[quatre];
        final ITransform st = S3DHelper.newTransform((Object[]) null);

        stg.getTransform(st);
        final double[][] mat = ((AbstractTransform) st).get();
        final int x = ax - px;
        final int y = ay - py;
        if (bp == 1) {
            SceneHelper.translate(stg, (x / dixMille) * mat[0][0], -y
                    / dixMille, (x / dixMille) * mat[2][0]);
        } else if (bp == 2) {
            final int wheel = ax * ay;
            SceneHelper.translate(stg, wheel * mat[0][2], 0, wheel * mat[0][0]);
        } else if (bp == trois) {
            int roty = 0;
            if ((y > dix) || (y < -dix)) {
                roty = y;
            }
            SceneHelper.rotate(stg, -roty / centMille, -x / centMille, -x
                    / unSixM);
        }
        // System.out.println (st);
    }

    public void moveScene (final ICanvas3D sc3d, final ITransformGroup stg,
            final int bp, final int ax, final int px, final int ay, final int py) {
        final int dix = 10;
        final int trois = 3;
        final double dixMille = 10000.0;
        final double centMille = 100000.0;
        final int x = ax - px;
        final int y = ay - py;
        if (bp == 1) {
            SceneHelper.translate(stg, x / dixMille, -y / dixMille, .0);
        } else if (bp == 2) {
            SceneHelper.translate(stg, 0, 0, ax * ay);
        } else if (bp == trois) {
            int roty = 0;
            if ((y > dix) || (y < -dix)) {
                roty = y;
            }
            final ITransform st = S3DHelper.newTransform((Object[]) null);

            stg.getTransform(st);
            final double[][] mat = ((AbstractTransform) st).get();
            SceneHelper.rotate(stg, (-roty / centMille) * mat[0][0], -x
                    / centMille, (-roty / centMille) * mat[0][2]);
        }
    }

}
