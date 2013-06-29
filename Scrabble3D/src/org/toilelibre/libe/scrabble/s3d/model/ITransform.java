package org.toilelibre.libe.scrabble.s3d.model;

public interface ITransform {

    double getY ();

    double getX ();

    double getZ ();

    double getRotX ();

    double getRotY ();

    double getRotZ ();

    void setIdentity ();

    void rotX (double x);

    void rotY (double y);

    void rotZ (double z);

    void mul (ITransform r);

    void normalize ();

    void setTranslation (double d, double e, double f);

}
