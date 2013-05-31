package org.toilelibre.libe.scrabble.j3d.model;

import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;

import org.toilelibre.libe.scrabble.s3d.model.IBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;
import org.toilelibre.libe.scrabble.s3d.model.IUniverse;

public class J3DUniverse implements IUniverse
{
  private final SimpleUniverse su;

  public J3DUniverse (final ICanvas3D c)
  {
    this.su = new SimpleUniverse ((Canvas3D) c);
  }

  public final void addBranchGraph (final IBranchGroup bg)
  {
    this.su.addBranchGraph ((BranchGroup) ((J3DBranchGroup) bg)
        .getImpl ());
  }

  public final J3DTransformGroup getTransformGroup ()
  {
    return new J3DTransformGroup (this.su);
  }

  public final void setClipDistances (final double d1, final double d2)
  {
    this.su.getViewer ().getView ().setFrontClipDistance (d1);
    this.su.getViewer ().getView ().setBackClipDistance (d2);
  }

  public final void setAdditionalParameters ()
  {
    this.su.getViewingPlatform ().setNominalViewingTransform ();
  }

}
