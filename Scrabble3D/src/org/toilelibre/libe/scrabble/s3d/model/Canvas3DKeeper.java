/**
 * 
 */
package org.toilelibre.libe.scrabble.s3d.model;

/**
 * @author lionel
 * 
 */
public final class Canvas3DKeeper {
    private static ICanvas3D canvas;

    private Canvas3DKeeper () {

    }

    /**
     * @return the canvas
     */
    public static ICanvas3D getCanvas () {
        return Canvas3DKeeper.canvas;
    }

    /**
     * @param canvas1
     *            the canvas to set
     */
    public static void setCanvas (final ICanvas3D canvas1) {
        Canvas3DKeeper.canvas = canvas1;
    }

}
