package org.toilelibre.libe.scrabble.j3d.model;

import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.PickConeRay;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.toilelibre.libe.scrabble.s3d.model.Canvas3DKeeper;
import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;
import org.toilelibre.libe.scrabble.s3d.model.IPoint3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;

public class J3DCanvas3D extends Canvas3D implements ICanvas3D {

    /**
   * 
   */
    private static final long    serialVersionUID = -988165494640861951L;

    private static J3DCanvas3D   instance;
    private final J3DBranchGroup sbg;

    public J3DCanvas3D () {
        super (SimpleUniverse.getPreferredConfiguration ());
        this.sbg = new J3DBranchGroup ();
        this.sbg.setComponent (this);
        Canvas3DKeeper.setCanvas (this);
    }

    /**
     * @return the instance
     */
    public static J3DCanvas3D getCurrentInstance () {
        return J3DCanvas3D.instance;
    }

    public final ITransformGroup getCameraTransformGroup () {
        return this.sbg.getUnivers ().getTransformGroup ();
    }

    public final IPoint3D getPickedCoordinate (final int x, final int y) {

        final Locale locale = ((TransformGroup) ((J3DTransformGroup) this.sbg
                .getUnivers ().getTransformGroup ()).getImpl ()).getLocale ();
        final PickCanvas po = new PickCanvas (this, locale);
        po.setShapeLocation (x, y);
        final PickConeRay pr = (PickConeRay) po.getPickShape ();
        final SceneGraphPath sgp = locale.pickClosest (pr);
        if (sgp != null) {
            final double [] dist = new double [1];
            final Shape3D shape = (Shape3D) sgp.getObject ();

            shape.intersect (sgp, pr, dist);
            final Point3d org = new Point3d ();
            final Vector3d dir = new Vector3d ();
            pr.getDirection (dir);
            pr.getOrigin (org);
            dir.scaleAdd (dist [0], org);
            org.set (dir);
            return new J3DPoint3D (org);
        }
        return null;
    }

    public final J3DBranchGroup getScrabbleBranchGroup () {
        return this.sbg;
    }

    public final ITransformGroup getSceneTransformGroup () {
        return this.sbg.getTransformGroup ();
    }
}
