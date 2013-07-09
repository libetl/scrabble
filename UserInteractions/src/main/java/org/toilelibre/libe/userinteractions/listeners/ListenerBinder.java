package org.toilelibre.libe.userinteractions.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventListener;
import java.util.Map;

import org.apache.log4j.Logger;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;

public final class ListenerBinder {
	private static final Logger LOG = Logger.getLogger(ListenerBinder.class);

	@SuppressWarnings("unchecked")
	public static EventListener bind(final Object component, final String type,
			final Action action, final IUIBean bean,
			final Map<String, String> methods) {
		try {
			final Class<?> listenerClass = Class.forName("java.awt.event."
					+ type);
			final Class<?> listenerImplClass = Class
					.forName(ListenerBinder.class.getPackage().getName()
							+ ".impl.UI" + type);
			final Constructor<EventListener> listenerConstructor = (Constructor<EventListener>) listenerImplClass
					.getConstructor(new Class[] { Action.class, Map.class,
							IUIBean.class, });
			final Method mGet = component.getClass().getMethod(
					"get" + type + "s", new Class[0]);
			final Method mAdd = component.getClass().getMethod("add" + type,
					new Class[] { listenerClass });
			final Method mRemove = component.getClass().getMethod(
					"remove" + type, new Class[] { listenerClass });
			final EventListener el = listenerConstructor.newInstance(action,
					methods, bean);
			try {
				final EventListener[] elList = (EventListener[]) mGet
						.invoke(component);
				for (final EventListener elIt : elList) {
					action.getListeners().remove(elIt);
					mRemove.invoke(component, listenerClass.cast(elIt));
				}
			} catch (final InvocationTargetException e) {
				ListenerBinder.LOG.debug("New ActionListener created : " + e);
			}
			mAdd.invoke(component, listenerClass.cast(el));
			action.getListeners().add(el);
			return el;

		} catch (final SecurityException e) {
			ListenerBinder.LOG.error(e.getMessage());
		} catch (final NoSuchMethodException e) {
			ListenerBinder.LOG.error(e.getMessage());
		} catch (final ClassNotFoundException e) {
			ListenerBinder.LOG.error(e.getMessage());
		} catch (final IllegalArgumentException e) {
			ListenerBinder.LOG.error(e.getMessage());
		} catch (final InstantiationException e) {
			ListenerBinder.LOG.error(e.getMessage());
		} catch (final IllegalAccessException e) {
			ListenerBinder.LOG.error(e.getMessage());
		} catch (final InvocationTargetException e) {
			ListenerBinder.LOG.error(e.getMessage());
		}
		return null;
	}

	private ListenerBinder() {

	}
}
