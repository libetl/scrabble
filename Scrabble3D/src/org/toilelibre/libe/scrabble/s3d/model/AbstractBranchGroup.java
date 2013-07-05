package org.toilelibre.libe.scrabble.s3d.model;

import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;

public abstract class AbstractBranchGroup implements IBranchGroup {

    private Object          impl;
    private ITransformGroup stg;
    private IUniverse       univers;

    protected AbstractBranchGroup() {
    }

    protected AbstractBranchGroup(final Object bg1) {
        this.set(bg1);
    }

    protected AbstractBranchGroup(final Object bg1,
            final boolean lookForParentTg) {
    }

    public AbstractBranchGroup(final Object[] bg1, final int i) {
    }

    public abstract void addBranchGraph (final IBranchGroup brgr);

    public final Object getImpl () {
        return this.impl;
    }

    /**
     * @return the stg
     */
    protected final ITransformGroup getStg () {
        return this.stg;
    }

    public final ITransformGroup getTransformGroup () {
        return this.stg;
    }

    public final IUniverse getUnivers () {
        return this.univers;
    }

    protected abstract void set (final Object bg1);

    public final void setBranchGraph (final Object brgr) {
        this.set(brgr);
        this.univers.addBranchGraph(this);
    }

    public final void setComponent (final ICanvas3D c) {
        final double near = 0.001;
        final double far = 1000.0;
        this.univers = S3DHelper.newUniverse(c);
        this.univers.addBranchGraph(this);
        this.univers.setAdditionalParameters();
        this.univers.setClipDistances(near, far);
        this.stg = this.univers.getTransformGroup();
    }

    /**
     * @param impl1
     *            the impl to set
     */
    protected final void setImpl (final Object impl1) {
        this.impl = impl1;
    }

    /**
     * @param stg1
     *            the stg to set
     */
    protected final void setStg (final ITransformGroup stg1) {
        this.stg = stg1;
    }
}
