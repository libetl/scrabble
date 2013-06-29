package org.toilelibre.libe.userinteractions.timers;

import java.util.HashMap;
import java.util.Map;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.listeners.impl.UIActionListener;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.timers.impl.IUITimer;
import org.toilelibre.libe.userinteractions.timers.impl.UtilUITimer;

public final class TimerHelper {

    private static Map<String, IUITimer> timers;

    static {
        TimerHelper.timers = new HashMap<String, IUITimer> ();
    }

    private TimerHelper () {

    }

    public static IUITimer newTimer (final long delay,
            final UIActionListener uial) {
        return new UtilUITimer (delay, uial);
    }

    public static void add (final Action action, final String name,
            final IUITimer timer) {
        final String key = action.getClass ().getName ()
                + UIConstants.FILE_SEPARATOR + name;
        TimerHelper.timers.put (key, timer);
        action.getTimers ().put (name, timer);
    }

    public static void setTimeout (final Action action, final String name,
            final int millis) {
        final String key = action.getClass ().getName ()
                + UIConstants.FILE_SEPARATOR + name;

        TimerHelper.timers.get (key).setInitialDelay (millis);
        TimerHelper.timers.get (key).start ();

    }

    public static void start (final Action action, final String name) {
        final String key = action.getClass ().getName ()
                + UIConstants.FILE_SEPARATOR + name;
        TimerHelper.timers.get (key).start ();
    }

    public static void stop (final Action action, final String name) {
        final String key = action.getClass ().getName ()
                + UIConstants.FILE_SEPARATOR + name;
        TimerHelper.timers.get (key).stop ();
    }
}
