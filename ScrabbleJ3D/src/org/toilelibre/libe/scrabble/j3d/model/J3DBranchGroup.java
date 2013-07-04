package org.toilelibre.libe.scrabble.j3d.model;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Node;
import javax.media.j3d.SharedGroup;
import javax.media.j3d.TransformGroup;

import org.toilelibre.libe.scrabble.s3d.model.AbstractBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.IBranchGroup;

public class J3DBranchGroup extends AbstractBranchGroup {
	private Group	       bg;
	private TransformGroup	tg;

	public J3DBranchGroup () {
		this.bg = new BranchGroup ();
		this.setImpl (this.bg);
	}

	public J3DBranchGroup (final Object bg1) {
		this.set (bg1);
	}

	public J3DBranchGroup (final Object bg1, final boolean lookForParentTg) {
		this.bg = (Group) bg1;
		this.setImpl (this.bg);
		if (lookForParentTg) {
			Node n = (Node) bg1;
			while ( (n != null) && ! (n instanceof TransformGroup)) {
				if (n instanceof SharedGroup) {
					n = ((SharedGroup) n).getLinks () [0];
				} else {
					n = n.getParent ();
				}
			}
			this.setStg (new J3DTransformGroup (n, true));
		} else {
			this.setStg (new J3DTransformGroup (this.bg.getParent (), true));
		}
		this.tg = (TransformGroup) ((J3DTransformGroup) this.getStg ())
		        .getImpl ();
	}

	public J3DBranchGroup (final Object [] bg1, final int i) {
		// Expected : Node [];

		this.bg = (Group) bg1 [i];
		this.setImpl (this.bg);

		int j = 0;
		while ( (j < bg1.length) && (this.tg == null)) {
			if (bg1 [j] instanceof TransformGroup) {
				this.tg = (TransformGroup) bg1 [j];
				this.setStg (new J3DTransformGroup (bg1 [j], true));
			}
			j++;
		}
	}

	@Override
	public final void addBranchGraph (final IBranchGroup isbg) {
		try {
			final Object impl;

			if (isbg instanceof J3DLetterBranchGroup) {
				impl = ((J3DLetterBranchGroup) isbg).getImpl ();
			} else {
				impl = ((J3DBranchGroup) isbg).getImpl ();
			}
			this.tg.addChild ((Node) impl);
		} catch (final javax.media.j3d.MultipleParentException mpe) {
			mpe.hashCode ();
		} catch (final javax.media.j3d.RestrictedAccessException rae) {
			rae.hashCode ();
		}
	}

	@Override
	protected final void set (final Object bg1) {
		this.bg = new BranchGroup ();
		this.setImpl (this.bg);
		this.bg.setCapability (Group.ALLOW_CHILDREN_READ);
		this.bg.setCapability (Group.ALLOW_CHILDREN_WRITE);
		this.bg.setCapability (Group.ALLOW_CHILDREN_EXTEND);
		this.bg.setCapability (Node.ALLOW_LOCAL_TO_VWORLD_READ);
		this.setStg (new J3DTransformGroup ());
		this.tg = (TransformGroup) ((J3DTransformGroup) this.getStg ())
		        .getImpl ();
		this.tg.setCapability (Group.ALLOW_CHILDREN_READ);
		this.tg.setCapability (Group.ALLOW_CHILDREN_WRITE);
		this.tg.setCapability (Group.ALLOW_CHILDREN_EXTEND);
		this.tg.setCapability (TransformGroup.ALLOW_TRANSFORM_READ);
		this.tg.setCapability (TransformGroup.ALLOW_TRANSFORM_WRITE);
		this.tg.setCapability (Node.ALLOW_LOCAL_TO_VWORLD_READ);
		this.bg.addChild (this.tg);
		((BranchGroup) bg1).setCapability (BranchGroup.ALLOW_DETACH);
		this.tg.addChild ((Node) bg1);
	}
}
