package org.toilelibre.libe.scrabble.s3d.model;

import org.toilelibre.libe.scrabble.s3d.exception.S3DException;

public abstract class AbstractLetterBranchGroup implements ILetterBranchGroup {
    private static Object             fontLetter;
    private static ILetterBranchGroup lbg;

    private int                       idTray;

    private Object                    impl;

    private char                      letter;

    private ITransformGroup           stg;

    /**
     * @return the fontLetter
     */
    public static Object getFontLetter () {
        return AbstractLetterBranchGroup.fontLetter;
    }

    /**
     * @return the lbg
     */
    public static ILetterBranchGroup getLbg () {
        return AbstractLetterBranchGroup.lbg;
    }

    /**
     * @param fontLetter1
     *            the fontLetter to set
     */
    public static void setFontLetter (final Object fontLetter1) {
        AbstractLetterBranchGroup.fontLetter = fontLetter1;
    }

    /**
     * @param lbg1
     *            the lbg to set
     */
    public static void setLbg (final ILetterBranchGroup lbg1) {
        AbstractLetterBranchGroup.lbg = lbg1;
    }

    public static ILetterBranchGroup cloneLetter () {
        return ((AbstractLetterBranchGroup) AbstractLetterBranchGroup.lbg)
                .cloneImpl ();
    }

    /**
     * @return the idTray
     */
    public final int getIdTray () {
        return this.idTray;
    }

    /**
     * @return the impl
     */
    public final Object getImpl () {
        return this.impl;
    }

    /**
     * @return the letter
     */
    public final char getLetter () {
        return this.letter;
    }

    public final void setComponent (final IBranchGroup sbg) {
        sbg.addBranchGraph (this);
    }

    public final void setIdTray (final int idTray1) {
        this.idTray = idTray1;
    }

    /**
     * @param impl1
     *            the impl to set
     */
    public final void setImpl (final Object impl1) {
        this.impl = impl1;
    }

    public final void setLetter (final char letter1) {
        this.letter = letter1;
    }

    /**
     * @param stg
     *            the stg to set
     */
    public final void setTransformGroup (final ITransformGroup stg1) {
        this.stg = stg1;
    }

    public final ITransformGroup getTransformGroup () {
        return this.stg;
    }

    public final void addBranchGraph (final IBranchGroup o) {
        // Implemented, but empty
    }

    public final void setTransform (final ITransform ist) {
        try {
            this.stg.setTransform (ist);
        } catch (final S3DException e) {
            e.hashCode ();
        }
    }

    protected abstract ILetterBranchGroup cloneImpl ();

    public abstract void affectLetter (char letter1);

    public abstract void setOld ();

    public abstract void setPoints (final int points);

}
