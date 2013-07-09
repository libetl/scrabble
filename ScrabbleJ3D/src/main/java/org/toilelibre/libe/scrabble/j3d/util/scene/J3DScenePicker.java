package org.toilelibre.libe.scrabble.j3d.util.scene;

import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.PickCylinderRay;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.toilelibre.libe.scrabble.j3d.model.J3DBranchGroup;
import org.toilelibre.libe.scrabble.j3d.model.J3DPoint3D;
import org.toilelibre.libe.scrabble.j3d.model.J3DTransformGroup;
import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.IBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;
import org.toilelibre.libe.scrabble.s3d.model.IPoint3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;
import org.toilelibre.libe.scrabble.s3d.util.scene.IScenePicker;
import org.web3d.vrml.renderer.j3d.nodes.J3DUserData;

public final class J3DScenePicker implements IScenePicker {

	private static IBranchGroup findBranchGroup(final J3DUserData jud,
			final String name, final SceneGraphPath element, final int j) {
		if ((jud.linkReference.getUrl().length > 0)
				&& (jud.linkReference.getUrl()[0].length() > 1)
				&& name.equals(jud.linkReference.getUrl()[0].substring(1))
				&& (element.getNode(j) instanceof Group)) {
			final Object[] o = new Object[element.nodeCount()];
			for (int i = 0; i < element.nodeCount(); i++) {
				o[i] = element.getNode(i);
			}
			return new J3DBranchGroup(o, j);
		}
		return null;
	}

	private static IBranchGroup lookForElement(final SceneGraphPath[] sgp,
			final String name) {
		IBranchGroup sbg = null;
		if (sgp != null) {
			int i = 0;
			while ((sbg == null) && (i < sgp.length)) {
				int j = 0;
				final SceneGraphPath element = sgp[i];
				while ((sbg == null) && (j < element.nodeCount())) {
					final J3DUserData jud = (J3DUserData) element.getNode(j)
							.getUserData();
					if (name.equals(element.getNode(j).getName())) {
						sbg = new J3DBranchGroup(element.getNode(j), true);
					} else if ((jud != null) && (jud.linkReference != null)
							&& (jud.linkReference.getUrl() != null)) {
						sbg = J3DScenePicker.findBranchGroup(jud, name,
								element, j);
					}
					j++;
				}
				i++;
			}
		}
		return sbg;
	}

	public J3DScenePicker() {

	}

	@Override
	public IBranchGroup getNameAt(final ICanvas3D sc3d, final String name,
			final int x, final int y) {

		final IPoint3D p = sc3d.getPickedCoordinate(x, y);
		SceneGraphPath[] sgp;
		if (p == null) {
			return null;
		}
		final ITransform st = S3DHelper.newTransform((Object[]) null);

		sc3d.getCameraTransformGroup().getTransform(st);
		final PickCylinderRay pcr = new PickCylinderRay(
				(Point3d) ((J3DPoint3D) p).getObj(),
				new Vector3d(0.0, 0.0, 0.0), 0.05);

		final Locale bg = ((TransformGroup) ((J3DTransformGroup) sc3d
				.getCameraTransformGroup()).getImpl()).getLocale();
		sgp = bg.pickAllSorted(pcr);

		return J3DScenePicker.lookForElement(sgp, name);
	}

	@Override
	public ITransformGroup getTransformGroupAt(final ICanvas3D sc3d,
			final String name, final int x, final int y) {
		final IBranchGroup sbg = this.getNameAt(sc3d, name, x, y);
		if (sbg != null) {
			return sbg.getTransformGroup();
		}
		return null;
	}

}
