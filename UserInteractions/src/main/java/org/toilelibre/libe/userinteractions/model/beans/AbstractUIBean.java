package org.toilelibre.libe.userinteractions.model.beans;

import javax.swing.JFrame;
import java.util.EventObject;

public abstract class AbstractUIBean extends JFrame implements IUIBean {
    private EventObject event;
    private String      name;

    @Override
    public final EventObject getEvent () {
        return this.event;
    }

    @Override
    public final String getName () {
        return this.name;
    }

    @Override
    public final void setEvent (final EventObject event1) {
        this.event = event1;
    }

    @Override
    public final void setName (final String name1) {
        this.name = name1;
    }

}
