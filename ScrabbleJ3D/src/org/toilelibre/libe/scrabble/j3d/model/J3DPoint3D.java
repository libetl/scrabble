package org.toilelibre.libe.scrabble.j3d.model;

import javax.vecmath.Point3d;

import org.toilelibre.libe.scrabble.s3d.model.AbstractPoint3D;

public class J3DPoint3D extends AbstractPoint3D
{

  private final Point3d point;

  public J3DPoint3D ()
  {
    super ();
    this.point = new Point3d ();
  }

  public J3DPoint3D (final Double xa, final Double ya, final Double za)
  {
    super (xa.doubleValue (), ya.doubleValue (), za.doubleValue ());
    this.point = new Point3d (xa.doubleValue (), ya.doubleValue (), za
        .doubleValue ());
  }

  public J3DPoint3D (final double xa, final double ya, final double za)
  {
    super (xa, ya, za);
    this.point = new Point3d (xa, ya, za);
  }

  public J3DPoint3D (final Object p)
  {
    this.point = (Point3d) p;
    this.setX (this.point.x);
    this.setY (this.point.y);
    this.setZ (this.point.z);
  }

  @Override
  public final Object getObj ()
  {
    return this.point;
  }
}
