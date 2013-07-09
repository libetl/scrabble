package org.toilelibre.libe.scrabble.applicationlayer.beans.party;

import org.toilelibre.libe.scrabble.s3d.model.ILetterBranchGroup;
import org.toilelibre.libe.userinteractions.model.beans.AbstractUIBean;

public final class ChangeTurnBean extends AbstractUIBean {

	public static final String ID_BEAN = "changeTurnBean";
	public static final int INITIAL_DELAY_MOVEMENT = 2000;
	public static final String MOVEMENT = "movement";
	public static final String MOVEMENT_LETTER = "movementLetter";
	public static final String NB_PLAYERS = "nbPlayers";
	public static final int TRAY_LENGTH = 7;
	public static final String TURN = "turn";
	private ILetterBranchGroup[][] letterBranchGroups;
	private double[] letterPositions;

	public ChangeTurnBean() {

	}

	/**
	 * @return the letterBranchGroups
	 */
	public ILetterBranchGroup[][] getLetterBranchGroups() {
		return this.letterBranchGroups;
	}

	/**
	 * @return the letterPositions
	 */
	public double[] getLetterPositions() {
		return this.letterPositions;
	}

	/**
	 * @param letterBranchGroups1
	 *            the letterBranchGroups to set
	 */
	public void setLetterBranchGroups(
			final ILetterBranchGroup[][] letterBranchGroups1) {
		this.letterBranchGroups = letterBranchGroups1;
	}

	/**
	 * @param letterPositions1
	 *            the letterPositions to set
	 */
	public void setLetterPositions(final double[] letterPositions1) {
		this.letterPositions = letterPositions1;
	}
}
