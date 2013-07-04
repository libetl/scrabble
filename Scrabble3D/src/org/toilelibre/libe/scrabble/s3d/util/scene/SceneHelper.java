package org.toilelibre.libe.scrabble.s3d.util.scene;

import org.toilelibre.libe.scrabble.s3d.exception.S3DException;
import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;

public final class SceneHelper {
	public static void rotate (final ITransformGroup stg, final double x,
	        final double y, final double z) {
		final ITransform st = S3DHelper.newTransform ((Object []) null);
		final ITransform xr = S3DHelper.newTransform ((Object []) null);
		final ITransform yr = S3DHelper.newTransform ((Object []) null);
		final ITransform zr = S3DHelper.newTransform ((Object []) null);

		stg.getTransform (st);

		xr.setIdentity ();
		yr.setIdentity ();
		zr.setIdentity ();
		xr.rotX (x);
		yr.rotY (y);
		zr.rotZ (z);
		st.mul (xr);
		st.mul (yr);
		st.mul (zr);

		st.normalize ();
		try {
			stg.setTransform (st);
		} catch (final S3DException e) {
			e.hashCode ();
		}
	}

	public static void translate (final ITransformGroup stg, final double x,
	        final double y, final double z) {
		final ITransform st = S3DHelper.newTransform ((Object []) null);

		stg.getTransform (st);

		st.setTranslation (st.getX () + x, st.getY () + y, st.getZ () + z);
		st.normalize ();
		try {
			stg.setTransform (st);
		} catch (final S3DException e) {
			e.hashCode ();
		}
	}

	private SceneHelper () {

	}
}
