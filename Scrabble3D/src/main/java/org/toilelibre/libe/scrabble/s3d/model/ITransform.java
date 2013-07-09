package org.toilelibre.libe.scrabble.s3d.model;

public interface ITransform {

	double getRotX();

	double getRotY();

	double getRotZ();

	double getX();

	double getY();

	double getZ();

	void mul(ITransform r);

	void normalize();

	void rotX(double x);

	void rotY(double y);

	void rotZ(double z);

	void setIdentity();

	void setTranslation(double d, double e, double f);

}
