package org.toilelibre.libe.userinteractions.timers;

import java.util.HashMap;
import java.util.Map;

import org.toilelibre.libe.userinteractions.listeners.impl.UIActionListener;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;
import org.toilelibre.libe.userinteractions.timers.impl.IUITimer;

public final class TimerBinder {

    private TimerBinder () {
    }

    public static IUITimer bind (final Action action, final IUIBean bean,
            final String delay, final String method) {
        final Map<String, String> methods = new HashMap<String, String> ();
        methods.put ("actionPerformed", method);
        final UIActionListener uial = new UIActionListener (action, methods,
                bean);
        return TimerHelper.newTimer ((int) Long.parseLong (delay), uial);
    }

}
