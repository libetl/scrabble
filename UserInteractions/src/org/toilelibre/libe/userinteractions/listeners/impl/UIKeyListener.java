package org.toilelibre.libe.userinteractions.listeners.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.listeners.ListenerHelper;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;

public class UIKeyListener implements KeyListener {
    private final Action              action;
    private final IUIBean             bean;
    private final Map<String, String> methods;

    public UIKeyListener(final Action action2,
            final Map<String, String> methods2, final IUIBean bean2) {
        this.action = action2;
        this.methods = methods2;
        this.bean = bean2;
    }

    public final void keyPressed (final KeyEvent e) {
        ListenerHelper.fireAction (UIConstants.ACTIONLISTENER_TYPE, "pressed",
                this.bean, this.methods, this.action, e);
    }

    public final void keyReleased (final KeyEvent e) {
        ListenerHelper.fireAction (UIConstants.ACTIONLISTENER_TYPE, "released",
                this.bean, this.methods, this.action, e);
    }

    public final void keyTyped (final KeyEvent e) {
        ListenerHelper.fireAction (UIConstants.ACTIONLISTENER_TYPE, "typed",
                this.bean, this.methods, this.action, e);
    }

}
