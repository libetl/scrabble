package org.toilelibre.libe.userinteractions.listeners.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Map;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.listeners.ListenerHelper;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;

public class UIMouseMotionListener implements MouseMotionListener {
	private final Action	          action;
	private final IUIBean	          bean;
	private final Map<String, String>	methods;

	public UIMouseMotionListener (final Action action2,
	        final Map<String, String> methods2, final IUIBean bean2) {
		this.action = action2;
		this.methods = methods2;
		this.bean = bean2;
	}

	public final void mouseDragged (final MouseEvent e) {
		ListenerHelper.fireAction (UIConstants.MOUSEMOTIONLISTENER_TYPE,
		        "dragged", this.bean, this.methods, this.action, e);
	}

	public final void mouseMoved (final MouseEvent e) {
		ListenerHelper.fireAction (UIConstants.MOUSEMOTIONLISTENER_TYPE,
		        "moved", this.bean, this.methods, this.action, e);
	}

}
