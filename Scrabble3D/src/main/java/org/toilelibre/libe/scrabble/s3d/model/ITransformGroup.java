package org.toilelibre.libe.scrabble.s3d.model;

import org.toilelibre.libe.scrabble.s3d.exception.S3DException;

public interface ITransformGroup {
    void getTransform (final ITransform st);

    boolean isTheSame (final ITransformGroup stg1);

    void setTransform (final ITransform st) throws S3DException;
}
