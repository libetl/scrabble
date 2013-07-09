package org.toilelibre.libe.scrabble.s3d.model;

public interface IBranchGroup {

	void addBranchGraph(IBranchGroup isbg);

	ITransformGroup getTransformGroup();

}
