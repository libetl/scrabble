package org.toilelibre.libe.scrabble.j3d.model;

import javax.media.j3d.BadTransformException;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import org.toilelibre.libe.scrabble.s3d.exception.S3DException;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class J3DTransformGroup implements ITransformGroup {
    private final TransformGroup tg;

    public J3DTransformGroup() {
        this.tg = new TransformGroup ();
    }

    public J3DTransformGroup(final Object su) {
        this.tg = ((SimpleUniverse) su).getViewingPlatform ()
                .getViewPlatformTransform ();
    }

    public J3DTransformGroup(final Object su,
            final boolean doNotGetViewingPlatform) {
        this.tg = (TransformGroup) su;
    }

    public final Object getImpl () {
        return this.tg;
    }

    @Override
    public final void getTransform (final ITransform st) {
        this.tg.getTransform ((Transform3D) ((J3DTransform) st).getImpl ());
    }

    @Override
    public final boolean isTheSame (final ITransformGroup stg1) {
        return this.tg == ((J3DTransformGroup) stg1).getImpl ();
    }

    @Override
    public final void setTransform (final ITransform st) throws S3DException {
        try {
            this.tg.setTransform ((Transform3D) ((J3DTransform) st).getImpl ());
        } catch (final BadTransformException bte) {
            throw new S3DException (bte);
        }
    }
}
