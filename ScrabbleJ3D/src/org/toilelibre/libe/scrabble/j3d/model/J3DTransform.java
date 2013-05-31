package org.toilelibre.libe.scrabble.j3d.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.media.j3d.Transform3D;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.toilelibre.libe.scrabble.s3d.model.AbstractTransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.util.expression.FieldAccessor;

public class J3DTransform extends AbstractTransform
{

  private static final int  COLS = 4;

  private static final int  ROWS = 4;

  private final Transform3D t;

  private Matrix4d          matrix4d;
  private AxisAngle4d       aa4d;
  private Vector3d          vector3d;

  public J3DTransform ()
  {
    super ();
    this.t = new Transform3D ();
    this.matrix4d = new Matrix4d ();
    this.aa4d = new AxisAngle4d ();
    this.vector3d = new Vector3d ();
  }

  public J3DTransform (final double [][] ds)
  {
    this ();
    this.set (ds);
  }

  @Override
  public final double [][] get ()
  {
    this.matrix4d.setIdentity ();
    final double [][] res = 
      new double [J3DTransform.COLS] [J3DTransform.ROWS];
    this.t.get (this.matrix4d);
    for (int i = 0; i < J3DTransform.COLS; i += 1)
    {
      for (int j = 0; j < J3DTransform.ROWS; j += 1)
      {
        final String field = "m" + i + j;
        res[i][j] = ((Double) FieldAccessor.get (this.matrix4d, field))
            .doubleValue ();
      }
    }

    return res;
  }

  @Override
  public final Object getImpl ()
  {
    return this.t;
  }

  @Override
  public final double getRotX ()
  {
    this.matrix4d.setIdentity ();
    this.t.get (this.matrix4d);
    this.aa4d.set (0, 0, 0, 0);
    this.aa4d.set (this.matrix4d);
    return this.aa4d.getX ();
  }

  @Override
  public final double getRotY ()
  {
    this.matrix4d.setIdentity ();
    this.t.get (this.matrix4d);
    this.aa4d.set (0, 0, 0, 0);
    this.aa4d.set (this.matrix4d);
    return this.aa4d.getY ();
  }

  @Override
  public final double getRotZ ()
  {
    this.matrix4d.setIdentity ();
    this.t.get (this.matrix4d);
    this.aa4d.set (0, 0, 0, 0);
    this.aa4d.set (this.matrix4d);
    return this.aa4d.getZ ();
  }

  @Override
  public final double getX ()
  {
    this.matrix4d.setIdentity ();
    this.t.get (this.matrix4d);
    return this.matrix4d.m03;
  }

  @Override
  public final double getY ()
  {
    this.matrix4d.setIdentity ();
    this.t.get (this.matrix4d);
    return this.matrix4d.m13;
  }

  @Override
  public final double getZ ()
  {
    this.matrix4d.setIdentity ();
    this.t.get (this.matrix4d);
    return this.matrix4d.m23;

  }

  public final void lookAt (final Point3d arg0, final Point3d arg1,
      final Vector3d arg2)
  {
    this.t.lookAt (arg0, arg1, arg2);
  }

  @Override
  public final void mul (final ITransform arg0)
  {
    this.t.mul ((Transform3D) ((AbstractTransform) arg0).getImpl ());
  }

  @Override
  public final void normalize ()
  {
    this.t.setScale (1.0);
    this.t.normalize ();
    this.t.setScale (1.0);
  }

  @Override
  public final void rotX (final double arg0)
  {
    this.t.rotX (arg0);
  }

  @Override
  public final void rotY (final double arg0)
  {
    this.t.rotY (arg0);
  }

  @Override
  public final void rotZ (final double arg0)
  {
    this.t.rotZ (arg0);
  }

  @Override
  public final void set (final double [][] matrix)
  {
    this.matrix4d.setIdentity ();
    final String methodName = "setM";
    for (int i = 0; i < J3DTransform.COLS; i += 1)
    {
      for (int j = 0; j < J3DTransform.ROWS; j += 1)
      {
        Method method;
        try
        {
          method = this.matrix4d.getClass ().getMethod (methodName + i + j,
              new Class [] {double.class });
          method.invoke (this.matrix4d,
              new Object [] {new Double (matrix[i][j]) });
        } catch (final SecurityException e)
        {
          this.displayException (e);
        } catch (final NoSuchMethodException e)
        {
          this.displayException (e);
        } catch (final IllegalArgumentException e)
        {
          this.displayException (e);
        } catch (final IllegalAccessException e)
        {
          this.displayException (e);
        } catch (final InvocationTargetException e)
        {
          this.displayException (e);
        }
      }
    }
    this.t.set (this.matrix4d);
  }

  @Override
  public final void setIdentity ()
  {
    this.t.setIdentity ();
  }

  @Override
  public final void setTranslation (final double x, final double y,
      final double z)
  {
    this.vector3d.set (x, y, z);
    this.t.setTranslation (this.vector3d);
  }

}
