package org.toilelibre.libe.scrabble.applicationlayer.beans.party;

import org.toilelibre.libe.userinteractions.model.beans.AbstractUIBean;
import org.toilelibre.libe.userinteractions.util.BeansComponents;

public final class NewPartyBean extends AbstractUIBean {
	public static final String MOVEMENT = "movement";

	public static final double[][] VIEWPOINT_NEW_PARTY = new double[][] {
			{ 1, 0, 0, 0 }, { 0, 0, 1, 46.5 }, { 0, -1, 0, 1.35 },
			{ 0, 0, 0, 1, }, };
	private Object jCS1;
	private Object jCS2;
	private Object jCS3;
	private Object jCS4;
	private Object jN1;
	private Object jN2;
	private Object jN3;
	private Object jN4;

	private Object startNewPartyButton;

	public NewPartyBean() {
	}

	public String getJN1Text() {
		return BeansComponents.getText(this.jN1);
	}

	public String getJN2Text() {
		return BeansComponents.getText(this.jN2);
	}

	public String getJN3Text() {
		return BeansComponents.getText(this.jN3);
	}

	public String getJN4Text() {
		return BeansComponents.getText(this.jN4);
	}

	public Object getStartNewPartyButton() {
		return this.startNewPartyButton;
	}

	public Boolean isJ1CSelected() {
		return BeansComponents.isChecked(this.jCS1);
	}

	public Boolean isJ2CSelected() {
		return BeansComponents.isChecked(this.jCS2);
	}

	public Boolean isJ3CSelected() {
		return BeansComponents.isChecked(this.jCS3);
	}

	public Boolean isJ4CSelected() {
		return BeansComponents.isChecked(this.jCS4);
	}

	public void setJCS1(final Object jcs11) {
		this.jCS1 = jcs11;
	}

	public void setJCS2(final Object jcs21) {
		this.jCS2 = jcs21;
	}

	public void setJCS3(final Object jcs31) {
		this.jCS3 = jcs31;
	}

	public void setJCS4(final Object jcs41) {
		this.jCS4 = jcs41;
	}

	public void setJN1(final Object jn11) {
		this.jN1 = jn11;
	}

	public void setJN2(final Object jn21) {
		this.jN2 = jn21;
	}

	public void setJN3(final Object jn31) {
		this.jN3 = jn31;
	}

	public void setJN4(final Object jn41) {
		this.jN4 = jn41;
	}

	public void setStartNewPartyButton(final Object startNewPartyButton1) {
		this.startNewPartyButton = startNewPartyButton1;
	}
}
