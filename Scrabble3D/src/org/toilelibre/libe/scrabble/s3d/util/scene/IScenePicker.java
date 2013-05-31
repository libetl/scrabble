package org.toilelibre.libe.scrabble.s3d.util.scene;


import org.toilelibre.libe.scrabble.s3d.model.IBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;

public interface IScenePicker
{
  
  IBranchGroup getNameAt (final ICanvas3D sc3d,
      final String name, final int x, final int y);

  ITransformGroup getTransformGroupAt (
     final ICanvas3D sc3d, final String name, final int x, final int y);

}
