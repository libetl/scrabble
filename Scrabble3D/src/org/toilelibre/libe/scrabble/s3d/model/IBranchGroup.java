package org.toilelibre.libe.scrabble.s3d.model;

public interface IBranchGroup
{

  ITransformGroup getTransformGroup ();

  void addBranchGraph (IBranchGroup isbg);

}
