package org.toilelibre.libe.userinteractions.listeners.callback;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.toilelibre.libe.userinteractions.exception.UIException;

public final class Callback {
    private static Method m;

    public static void fireCallback (final String string, final URL url)
            throws UIException {
        try {
            Callback.m.invoke (null, new Object [] { string, url });
        } catch (final IllegalArgumentException e) {
            throw new UIException (e);
        } catch (final IllegalAccessException e) {
            throw new UIException (e);
        } catch (final InvocationTargetException e) {
            throw new UIException (e);
        }
    }

    public static void setMethod (final String impl, final String method)
            throws UIException {
        Class<?> c;
        try {
            c = Class.forName (impl);
            final Method temp = c.getDeclaredMethod (method, new Class [] {
                    String.class, URL.class, });
            Callback.m = temp;
        } catch (final ClassNotFoundException e) {
            throw new UIException (e);
        } catch (final SecurityException e) {
            throw new UIException (e);
        } catch (final NoSuchMethodException e) {
            throw new UIException (e);
        }
    }

    private Callback() {

    }

}
