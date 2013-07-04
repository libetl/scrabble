package org.toilelibre.libe.scrabble.s3d.model;

public interface ICanvas3D {
	ITransformGroup getCameraTransformGroup ();

	IPoint3D getPickedCoordinate (final int x, final int y);

	ITransformGroup getSceneTransformGroup ();

	IBranchGroup getScrabbleBranchGroup ();
}
