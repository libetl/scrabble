package org.toilelibre.libe.scrabble.modelfactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public final class FieldAccessor {

	private static final Logger	LOG	= Logger.getLogger (FieldAccessor.class);

	public static Object get (final Object o, final String field) {
		final Class<?> c = o.getClass ();
		Object res = null;

		try {
			final Field f = c.getDeclaredField (field);
			f.setAccessible (true);
			final Object tmp = f.get (o);
			if (tmp != null) {
				res = tmp;
			}
		} catch (final SecurityException e) {
			FieldAccessor.LOG.error (e.getMessage ());
		} catch (final NoSuchFieldException e) {
			FieldAccessor.LOG.error (e.getMessage ());
		} catch (final IllegalArgumentException e) {
			FieldAccessor.LOG.error (e.getMessage ());
		} catch (final IllegalAccessException e) {
			FieldAccessor.LOG.error (e.getMessage ());
		}
		if (res == null) {

			try {
				final String methodName = "get"
				        + field.substring (0, 1).toUpperCase ()
				        + field.substring (1);
				final Method m = c.getMethod (methodName, new Class [0]);
				m.setAccessible (true);
				res = m.invoke (o, new Object [0]);
			} catch (final SecurityException e) {
				FieldAccessor.LOG.error (e.getMessage ());
			} catch (final NoSuchMethodException e) {
				FieldAccessor.LOG.error (e.getMessage ());
			} catch (final IllegalArgumentException e) {
				FieldAccessor.LOG.error (e.getMessage ());
			} catch (final IllegalAccessException e) {
				FieldAccessor.LOG.error (e.getMessage ());
			} catch (final InvocationTargetException e) {
				FieldAccessor.LOG.error (e.getMessage ());
			}

		}
		return res;
	}

	private FieldAccessor () {

	}
}
