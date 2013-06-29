package org.toilelibre.libe.userinteractions.model.interactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.listeners.ListenerBinder;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;
import org.toilelibre.libe.userinteractions.timers.TimerBinder;
import org.toilelibre.libe.userinteractions.timers.TimerHelper;
import org.toilelibre.libe.userinteractions.timers.impl.IUITimer;

public class Interaction {
    private Action                                  action;
    private IUIBean                                 bean;
    private final Map<Integer, Map<String, String>> events;
    private final List<String>                      instances;
    private final Map<String, List<String>>         timers;

    public Interaction () {
        this.events = new HashMap<Integer, Map<String, String>> ();
        this.instances = new ArrayList<String> ();
        this.timers = new HashMap<String, List<String>> ();
    }

    public final void addEvent (final int instanceId, final String event,
            final String method) {
        Map<String, String> listEvents = this.events.get (new Integer (
                instanceId));
        if (listEvents == null) {
            listEvents = new HashMap<String, String> ();
            this.events.put (new Integer (instanceId), listEvents);
        }
        listEvents.put (event, method);
    }

    public final void addTimer (final String name, final long delay,
            final String method) {
        final List<String> l = new LinkedList<String> ();
        l.add ("" + delay);
        l.add (method);
        this.timers.put (name, l);
    }

    public final int createInstance (final String component,
            final String listenerType) {
        final String key = component + UIConstants.FILE_SEPARATOR
                + listenerType;
        if (!this.instances.contains (key)) {
            this.instances.add (key);
        }
        return this.instances.indexOf (key);
    }

    public final Action getAction () {
        return this.action;
    }

    public final IUIBean getBean () {
        return this.bean;
    }

    public final Map<String, String> getEvents (final int instanceId1) {
        return this.events.get (new Integer (instanceId1));
    }

    public final void setAction (final Action action1) {
        this.action = action1;
    }

    public final void setBean (final IUIBean bean1) {
        this.bean = bean1;
    }

    public final void updateBindings () {
        for (int i = 0 ; i < this.instances.size () ; i += 1) {
            final String key = this.instances.get (i);
            final String [] entry = key.split ("/");
            final String field = entry [0];
            final String type = entry [1];

            final Object component = FieldAccessor.get (this.bean, field);

            final Map<String, String> eventsList = this.getEvents (i);
            if (component != null) {
                ListenerBinder.bind (component, type, this.action, this.bean,
                        eventsList);
            }
        }

        for (final String key : this.timers.keySet ()) {
            final IUITimer timer;
            timer = TimerBinder.bind (this.action, this.bean,
                    this.timers.get (key).get (0), this.timers.get (key)
                            .get (1));
            TimerHelper.add (this.action, key, timer);
        }
    }
}
