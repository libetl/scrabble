package org.toilelibre.libe.userinteractions.listeners.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.listeners.ListenerHelper;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;

public class UIMouseListener implements MouseListener {
    private final Action              action;
    private final IUIBean             bean;
    private final Map<String, String> methods;

    public UIMouseListener(final Action action2,
            final Map<String, String> methods2, final IUIBean bean2) {
        this.action = action2;
        this.methods = methods2;
        this.bean = bean2;
    }

    public final void mouseClicked (final MouseEvent e) {
        ListenerHelper.fireAction (UIConstants.MOUSELISTENER_TYPE, "clicked",
                this.bean, this.methods, this.action, e);
    }

    public final void mouseEntered (final MouseEvent e) {
        ListenerHelper.fireAction (UIConstants.MOUSELISTENER_TYPE, "entered",
                this.bean, this.methods, this.action, e);
    }

    public final void mouseExited (final MouseEvent e) {
        ListenerHelper.fireAction (UIConstants.MOUSELISTENER_TYPE, "exited",
                this.bean, this.methods, this.action, e);
    }

    public final void mousePressed (final MouseEvent e) {
        ListenerHelper.fireAction (UIConstants.MOUSELISTENER_TYPE, "pressed",
                this.bean, this.methods, this.action, e);
    }

    public final void mouseReleased (final MouseEvent e) {
        ListenerHelper.fireAction (UIConstants.MOUSELISTENER_TYPE, "released",
                this.bean, this.methods, this.action, e);
    }

}
