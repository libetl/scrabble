/**
 * 
 */
package org.toilelibre.libe.scrabble.s3d.impl;

import org.toilelibre.libe.scrabble.s3d.model.ITransform;

/**
 * @author lionel
 * 
 */
public final class ScaleViewPointUtility
{

  private static final double [][] M = 
  {{0.0, -0.734, 0.68, 39.874,}, 
    {0.0, 0.68, 0.734, 34.3,}
    , {-1.0, 0.0, 0.0, -0.26,}, 
    {0.0, 0.0, 0.0, 1.0,},};


  private ScaleViewPointUtility ()
  {

  }
  
  public static void main (final String [] args)
  {
    final double [][] matrix = ScaleViewPointUtility.M;
    new S3DHelper ();
    S3DHelper.getInstance ()
        .setImplPackage ("org.toilelibre.libe.scrabble.j3d");
    S3DHelper.getInstance ().setImplPrefix ("J3D");
    final ITransform st = S3DHelper
        .newTransform (new Object [] {matrix });
    System.out.println (st);
    st.normalize ();
    System.out.println (st);
  }
}
