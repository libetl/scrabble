package org.toilelibre.libe.scrabble.s3d.util.scene;

import java.util.HashMap;
import java.util.Map;

import org.toilelibre.libe.scrabble.s3d.exception.S3DException;
import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.AbstractTransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;
import org.toilelibre.libe.scrabble.s3d.util.expression.Expression;

public final class SceneStepper {

    private static final double [] STEPPING = new double [] { 0.001, 0.001,
            0.001, 0.05,                   };

    public static boolean stepToViewPoint (final ITransformGroup tg,
            final double [][] matrix) {
        final int quatre = 4;
        final ITransform st = S3DHelper.newTransform ((Object []) null);

        tg.getTransform (st);
        final double [][] actual = ((AbstractTransform) st).get ();
        return SceneStepper.stepToViewPoint (tg, actual, matrix, 0, quatre, 0,
                quatre);
    }

    private static boolean stepToViewPoint (final ITransformGroup tg,
            final double [][] actual, final double [][] target,
            final int firstI, final int lastI, final int firstJ, final int lastJ) {
        boolean notFinished = false;

        for (int i = firstI ; i < lastI ; i += 1) {
            for (int j = firstJ ; j < lastJ ; j += 1) {
                if (target [i] [j] > (actual [i] [j] + SceneStepper.STEPPING [j])) {
                    actual [i] [j] += SceneStepper.STEPPING [j];
                    notFinished = true;
                }
                if (target [i] [j] < (actual [i] [j] - SceneStepper.STEPPING [j])) {
                    actual [i] [j] -= SceneStepper.STEPPING [j];
                    notFinished = true;
                }
            }
        }
        final ITransform st = S3DHelper
                .newTransform (new Object [] { actual, });
        st.normalize ();
        try {
            tg.setTransform (st);
        } catch (final S3DException sje) {
            sje.hashCode ();
        }
        return notFinished;
    }

    public static void stepWithFunction (final ITransformGroup tg,
            final double t, final String fx, final String fy, final String fz) {
        final ITransform st = S3DHelper.newTransform ((Object []) null);

        tg.getTransform (st);
        final Map<String, Double> map = new HashMap<String, Double> ();
        map.put ("t", new Double (t));
        final double newX = Expression.valueOfExpr (fx, map);
        final double newY = Expression.valueOfExpr (fy, map);
        final double newZ = Expression.valueOfExpr (fz, map);
        st.setTranslation (newX, newY, newZ);
        st.normalize ();
        try {
            tg.setTransform (st);
        } catch (final S3DException sje) {
            sje.hashCode ();
        }
    }

    private SceneStepper() {

    }
}
