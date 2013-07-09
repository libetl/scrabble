package org.toilelibre.libe.userinteractions.listeners.impl;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Map;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.listeners.ListenerHelper;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;

public class UIMouseWheelListener implements MouseWheelListener {
    private final Action              action;
    private final IUIBean             bean;
    private final Map<String, String> methods;

    public UIMouseWheelListener(final Action action2,
            final Map<String, String> methods2, final IUIBean bean2) {
        this.action = action2;
        this.methods = methods2;
        this.bean = bean2;
    }

    @Override
    public final void mouseWheelMoved (final MouseWheelEvent e) {
        ListenerHelper.fireAction (UIConstants.MOUSEWHEELLISTENER_TYPE,
                "wheelMoved", this.bean, this.methods, this.action, e);
    }

}
