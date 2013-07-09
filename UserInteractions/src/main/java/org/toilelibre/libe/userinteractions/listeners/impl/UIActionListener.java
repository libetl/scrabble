package org.toilelibre.libe.userinteractions.listeners.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TimerTask;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.listeners.ListenerHelper;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;
import org.toilelibre.libe.userinteractions.timers.impl.UITimerEvent;

public class UIActionListener extends TimerTask implements ActionListener {
    private static final String       ACTION_PERFORMED = "actionPerformed";
    private static final UITimerEvent UI_TIMER_EVENT   = new UITimerEvent ();
    private final Action              action;
    private final IUIBean             bean;
    private final Map<String, String> methods;

    public UIActionListener (final Action action2,
            final Map<String, String> methods2, final IUIBean bean2) {
        this.action = action2;
        this.methods = methods2;
        this.bean = bean2;
    }

    @Override
    public final void actionPerformed (final ActionEvent e) {
        ListenerHelper.fireAction (UIConstants.ACTIONLISTENER_TYPE,
                UIActionListener.ACTION_PERFORMED, this.bean, this.methods,
                this.action, e);
    }

    /**
     * @return the action
     */
    public final Action getAction () {
        return this.action;
    }

    /**
     * @return the bean
     */
    public final IUIBean getBean () {
        return this.bean;
    }

    /**
     * @return the methods
     */
    public final Map<String, String> getMethods () {
        return this.methods;
    }

    /**
     * @see java.util.TimerTask#run()
     */
    @Override
    public final void run () {
        ListenerHelper.fireAction (UIConstants.ACTIONLISTENER_TYPE,
                UIActionListener.ACTION_PERFORMED, this.bean, this.methods,
                this.action, UIActionListener.UI_TIMER_EVENT);
    }

}
