/**
 * 
 */
package org.toilelibre.libe.userinteractions.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.EventObject;

/**
 * @author lionel
 * 
 */
public final class EventMethods {

	public static int getButton (final EventObject eo) {
		return ((MouseEvent) eo).getButton ();
	}

	public static int getScrollAmount (final EventObject eo) {
		return ((MouseWheelEvent) eo).getScrollAmount ();
	}

	public static int getWheelRotation (final EventObject eo) {
		return ((MouseWheelEvent) eo).getWheelRotation ();
	}

	public static int getX (final EventObject eo) {
		return ((MouseEvent) eo).getX ();
	}

	public static int getY (final EventObject eo) {
		return ((MouseEvent) eo).getY ();
	}

	private EventMethods () {

	}

}
