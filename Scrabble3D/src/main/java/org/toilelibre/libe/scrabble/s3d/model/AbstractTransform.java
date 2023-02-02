package org.toilelibre.libe.scrabble.s3d.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTransform implements ITransform {

    private static final String DURING_TRANSFORM_EXCEPTION = "During Transform";
    private static final Object LOG                        = LogManager.getLogger (AbstractTransform.class);

    public AbstractTransform () {
    }

    public AbstractTransform (final double [][] ds) {
        this.set (ds);
    }

    protected final void displayException (final Exception e) {
        ((Logger) AbstractTransform.LOG).error (
                AbstractTransform.DURING_TRANSFORM_EXCEPTION, e);
    }

    public abstract double [][] get ();

    public abstract Object getImpl ();

    @Override
    public abstract double getRotX ();

    @Override
    public abstract double getRotY ();

    @Override
    public abstract double getRotZ ();

    @Override
    public abstract double getX ();

    @Override
    public abstract double getY ();

    @Override
    public abstract double getZ ();

    @Override
    public abstract void mul (final ITransform arg0);

    @Override
    public abstract void normalize ();

    @Override
    public abstract void rotX (final double arg0);

    @Override
    public abstract void rotY (final double arg0);

    @Override
    public abstract void rotZ (final double arg0);

    protected abstract void set (final double [][] matrix);

    @Override
    public abstract void setIdentity ();

    @Override
    public abstract void setTranslation (final double x, final double y,
            final double z);

    @Override
    public final String toString () {

        final String openingBrace = "{";
        final String closingBrace = "}";
        final String comma = ", ";
        final double [][] get = this.get ();
        String res = openingBrace;
        for (int i = 0 ; i < get.length ; i += 1) {
            res += openingBrace;
            final double [] element = get [i];
            for (int j = 0 ; j < element.length ; j += 1) {
                res += element [j];
                if ( (j + 1) < element.length) {
                    res += comma;
                }
            }
            res += closingBrace;
            if ( (i + 1) < element.length) {
                res += comma;
            }
        }
        res += closingBrace;
        return res;
    }

}
