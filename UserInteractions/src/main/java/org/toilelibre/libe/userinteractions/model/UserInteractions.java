package org.toilelibre.libe.userinteractions.model;

import java.util.HashMap;
import java.util.Map;

import org.toilelibre.libe.userinteractions.exception.UIException;
import org.toilelibre.libe.userinteractions.listeners.callback.Callback;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;
import org.toilelibre.libe.userinteractions.model.interactions.Interaction;

public class UserInteractions {
	private final Map<String, IUIBean> beans;

	private final Map<String, Interaction> interactions;

	public UserInteractions() {
		this.interactions = new HashMap<String, Interaction>();
		this.beans = new HashMap<String, IUIBean>();
	}

	public final void addBean(final String key, final IUIBean value) {
		this.beans.put(key, value);
		value.setName(key);
	}

	public final void addInteraction(final String key, final Interaction value) {
		this.interactions.put(key, value);
	}

	public final Action getAction(final String key) {
		return this.interactions.get(key).getAction();
	}

	public final IUIBean getBean(final String key) {
		return this.beans.get(key);
	}

	public final Map<String, IUIBean> getBeans() {
		return this.beans;
	}

	public final Interaction getInteraction(final String key) {
		return this.interactions.get(key);
	}

	public final void setCallback(final String impl, final String method)
			throws UIException {
		Callback.setMethod(impl, method);
	}

	public final void updateBindings() {
		for (final String key : this.interactions.keySet()) {
			this.interactions.get(key).updateBindings();
		}
	}
}
