package org.toilelibre.libe.userinteractions.model.beans;

import java.util.EventObject;

public interface IUIBean {
	EventObject getEvent();

	String getName();

	void setEvent(final EventObject event1);

	void setName(final String name1);
}
