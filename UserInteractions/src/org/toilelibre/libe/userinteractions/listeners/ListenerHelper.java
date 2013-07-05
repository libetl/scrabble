package org.toilelibre.libe.userinteractions.listeners;

import java.awt.Component;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventObject;
import java.util.Map;

import org.apache.log4j.Logger;
import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.exception.UIException;
import org.toilelibre.libe.userinteractions.listeners.callback.Callback;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.actions.ActionRedirect;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;

public final class ListenerHelper {
    private static final Logger LOG = Logger.getLogger (ListenerHelper.class);

    private static void doRedirect (final ActionRedirect ar,
            final IUIBean bean, final EventObject e) throws UIException {
        String beanName = ar.getBeanName ();
        if (beanName == null) {
            beanName = bean.getName ();
        }
        if (ar.getRedirect () != null) {
            Callback.fireCallback (beanName, Thread.currentThread ()
                    .getContextClassLoader ().getResource (ar.getRedirect ()));
        }
        if (ar.isClosePrevious () && (e.getSource () instanceof Component)) {
            Component c = (Component) e.getSource ();
            while ( (c != null) && ! (c instanceof Window)) {
                c = c.getParent ();
            }
            if (c instanceof Window) {
                ((Window) c).removeAll ();
                c.setVisible (false);
            }
        }

    }

    public static void fireAction (final String type, final String actionName,
            final IUIBean bean, final Map<String, String> methods,
            final Action action, final EventObject e) {
        final Class<?> [] parameterTypes = new Class<?> [] { IUIBean.class,
                String.class, String.class, EventObject.class, };
        Method m;
        try {
            bean.setEvent (e);
            if (methods.get (actionName) != null) {
                m = action.getClass ().getMethod (methods.get (actionName),
                        parameterTypes);
                ListenerHelper.logEvent (e, type, actionName, action, m);
                final Object o = m.invoke (action, bean, type, actionName, e);

                if ( (o != null) && (o instanceof ActionRedirect)) {
                    final ActionRedirect ar = (ActionRedirect) o;
                    ListenerHelper.doRedirect (ar, bean, e);
                }
            }
        } catch (final UIException e1) {
            if (e1.getCause () instanceof InvocationTargetException) {
                ListenerHelper.LOG.error (UIConstants.CALL_ACTION_ERROR, e1);
            }
        } catch (final SecurityException e1) {
            ListenerHelper.LOG.error (UIConstants.CALL_ACTION_ERROR, e1);
        } catch (final NoSuchMethodException e1) {
            ListenerHelper.LOG.error (UIConstants.CALL_ACTION_ERROR, e1);
        } catch (final IllegalArgumentException e1) {
            ListenerHelper.LOG.error (UIConstants.CALL_ACTION_ERROR, e1);
        } catch (final IllegalAccessException e1) {
            ListenerHelper.LOG.error (UIConstants.CALL_ACTION_ERROR, e1);
        } catch (final InvocationTargetException e1) {
            ListenerHelper.LOG.error (e1.getTargetException ().getMessage ());
        }
    }

    private static void logEvent (final EventObject e, final String type,
            final String event, final Action action, final Method m) {
        String sourceName;
        if (e.getSource () instanceof Component) {
            sourceName = ((Component) e.getSource ()).getName ();
        } else {
            sourceName = e.getSource ().toString ();
        }

        ListenerHelper.LOG.debug (e.getSource ().getClass ().getSimpleName ()
                + " \"" + sourceName + "\" {" + type
                + UIConstants.PACKAGE_SEPARATOR + event + " () => "
                + action.getClass ().getSimpleName ()
                + UIConstants.PACKAGE_SEPARATOR + m.getName () + "()}");
    }

    private ListenerHelper() {

    }
}
