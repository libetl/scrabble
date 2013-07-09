package org.toilelibre.libe.scrabble.s3d.model;

import org.toilelibre.libe.scrabble.s3d.exception.S3DException;

public abstract class AbstractLetterBranchGroup implements ILetterBranchGroup {
	private static Object fontLetter;
	private static ILetterBranchGroup lbg;

	public static ILetterBranchGroup cloneLetter() {
		return ((AbstractLetterBranchGroup) AbstractLetterBranchGroup.lbg)
				.cloneImpl();
	}

	/**
	 * @return the fontLetter
	 */
	public static Object getFontLetter() {
		return AbstractLetterBranchGroup.fontLetter;
	}

	/**
	 * @return the lbg
	 */
	public static ILetterBranchGroup getLbg() {
		return AbstractLetterBranchGroup.lbg;
	}

	/**
	 * @param fontLetter1
	 *            the fontLetter to set
	 */
	public static void setFontLetter(final Object fontLetter1) {
		AbstractLetterBranchGroup.fontLetter = fontLetter1;
	}

	/**
	 * @param lbg1
	 *            the lbg to set
	 */
	public static void setLbg(final ILetterBranchGroup lbg1) {
		AbstractLetterBranchGroup.lbg = lbg1;
	}

	private int idTray;

	private Object impl;

	private char letter;

	private ITransformGroup stg;

	@Override
	public final void addBranchGraph(final IBranchGroup o) {
		// Implemented, but empty
	}

	@Override
	public abstract void affectLetter(char letter1);

	protected abstract ILetterBranchGroup cloneImpl();

	/**
	 * @return the idTray
	 */
	@Override
	public final int getIdTray() {
		return this.idTray;
	}

	/**
	 * @return the impl
	 */
	public final Object getImpl() {
		return this.impl;
	}

	/**
	 * @return the letter
	 */
	@Override
	public final char getLetter() {
		return this.letter;
	}

	@Override
	public final ITransformGroup getTransformGroup() {
		return this.stg;
	}

	public final void setComponent(final IBranchGroup sbg) {
		sbg.addBranchGraph(this);
	}

	@Override
	public final void setIdTray(final int idTray1) {
		this.idTray = idTray1;
	}

	/**
	 * @param impl1
	 *            the impl to set
	 */
	public final void setImpl(final Object impl1) {
		this.impl = impl1;
	}

	public final void setLetter(final char letter1) {
		this.letter = letter1;
	}

	@Override
	public abstract void setOld();

	@Override
	public abstract void setPoints(final int points);

	@Override
	public final void setTransform(final ITransform ist) {
		try {
			this.stg.setTransform(ist);
		} catch (final S3DException e) {
			e.hashCode();
		}
	}

	/**
	 * @param stg
	 *            the stg to set
	 */
	public final void setTransformGroup(final ITransformGroup stg1) {
		this.stg = stg1;
	}

}
