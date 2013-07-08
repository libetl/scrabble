package org.toilelibre.libe.scrabble.s3d.util.move;

import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;

public interface IMoveObject {
    void move (ICanvas3D iScrabbleCanvas3D, ITransformGroup stg,
            final int... params);
    // final int bp, final int x, final int y,
    // final int ay, final int py, final int turn);
}
