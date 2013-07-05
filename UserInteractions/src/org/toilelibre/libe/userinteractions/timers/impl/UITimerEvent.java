/**
 * 
 */
package org.toilelibre.libe.userinteractions.timers.impl;

import java.util.EventObject;

/**
 * @author lionel
 * 
 */
public class UITimerEvent extends EventObject {
    /**
   * 
   */
    private static final long serialVersionUID = 3655612585681626892L;

    /**
     * @param source1
     */
    public UITimerEvent() {
        super(new Object());
    }

    /**
     * @param source1
     */
    public UITimerEvent(final Object source1) {
        super(source1);
    }

}
