package org.toilelibre.libe.userinteractions.model.beans;

import java.util.EventObject;

public abstract class AbstractUIBean implements IUIBean {
	private EventObject	event;
	private String	    name;

	public final EventObject getEvent () {
		return this.event;
	}

	public final String getName () {
		return this.name;
	}

	public final void setEvent (final EventObject event1) {
		this.event = event1;
	}

	public final void setName (final String name1) {
		this.name = name1;
	}

}
