/**
 * 
 */
package org.toilelibre.libe.userinteractions.timers.impl;

/**
 * @author lionel
 * 
 */
public interface IUITimer {

	/**
	 * @param delay1
	 *            the delay to set
	 */
	void setDelay(final long delay1);

	/**
	 * @param initialDelay1
	 *            the initialDelay to set
	 */
	void setInitialDelay(final long initialDelay1);

	/**
	 * Start the timer
	 */
	void start();

	/**
	 * Stop the timer
	 */
	void stop();

}
