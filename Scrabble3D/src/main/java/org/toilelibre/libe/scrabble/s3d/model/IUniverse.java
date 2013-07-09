package org.toilelibre.libe.scrabble.s3d.model;

public interface IUniverse {

	void addBranchGraph(final IBranchGroup bg);

	ITransformGroup getTransformGroup();

	void setAdditionalParameters();

	void setClipDistances(final double d1, final double d2);

}
