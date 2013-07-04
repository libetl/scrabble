package org.toilelibre.libe.userinteractions.model.actions;

import java.util.EventListener;
import java.util.List;
import java.util.Map;

import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.toilelibre.libe.userinteractions.timers.impl.IUITimer;

public class Action {
	private List<EventListener>	  listeners;
	private Map<String, IUITimer>	timers;
	private UserInteractions	  uiOwner;

	public Action () {
		super ();
	}

	public Action (final UserInteractions uiOwner1) {
		super ();
		this.uiOwner = uiOwner1;
	}

	/**
	 * @return the listeners
	 */
	public final List<EventListener> getListeners () {
		return this.listeners;
	}

	/**
	 * @return the timers
	 */
	public final Map<String, IUITimer> getTimers () {
		return this.timers;
	}

	/**
	 * @return the uiOwner
	 */
	public final UserInteractions getUiOwner () {
		return this.uiOwner;
	}

	/**
	 * @param listeners1
	 *            the listeners to set
	 */
	public final void setListeners (final List<EventListener> listeners1) {
		this.listeners = listeners1;
	}

	/**
	 * @param timers1
	 *            the timers to set
	 */
	public final void setTimers (final Map<String, IUITimer> timers1) {
		this.timers = timers1;
	}

	/**
	 * @param uiOwner1
	 *            the uiOwner to set
	 */
	public final void setUiOwner (final UserInteractions uiOwner1) {
		this.uiOwner = uiOwner1;
	}

}
