/**
 * 
 */
package org.toilelibre.libe.userinteractions.timers.impl;

import java.util.Date;
import java.util.Timer;

import org.toilelibre.libe.userinteractions.listeners.impl.UIActionListener;

/**
 * @author lionel
 * 
 */
public class UtilUITimer implements IUITimer {
    private long             delay;
    private long             initialDelay;
    private Timer            timer;
    private UIActionListener uial;

    public UtilUITimer() {
    }

    public UtilUITimer(final long delay1, final UIActionListener uial1) {
        this();
        this.initialDelay = 0;
        this.delay = delay1;
        if (delay1 == 0) {
            this.delay = 1;
        }
        this.uial = uial1;
    }

    /**
     * @param delay1
     *            the delay to set
     */
    public final void setDelay (final long delay1) {
        this.delay = delay1;
    }

    /**
     * @param initialDelay1
     *            the initialDelay to set
     */
    public final void setInitialDelay (final long initialDelay1) {
        this.initialDelay = initialDelay1;
    }

    public final void start () {
        this.timer = new Timer();
        this.uial = new UIActionListener(this.uial.getAction(),
                this.uial.getMethods(), this.uial.getBean());
        if (this.initialDelay == 0) {
            this.timer.scheduleAtFixedRate(this.uial, new Date(), this.delay);
        } else {
            final Date firstTimeDate = new Date(new Date().getTime()
                    + this.initialDelay);
            this.timer
                    .scheduleAtFixedRate(this.uial, firstTimeDate, this.delay);
        }
    }

    public final void stop () {
        this.timer.cancel();
        this.uial.cancel();
    }

}
