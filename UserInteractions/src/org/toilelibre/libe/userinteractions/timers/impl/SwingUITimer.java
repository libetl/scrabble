/**
 * 
 */
package org.toilelibre.libe.userinteractions.timers.impl;

import javax.swing.Timer;

import org.toilelibre.libe.userinteractions.listeners.impl.UIActionListener;

/**
 * @author lionel
 *
 */
public class SwingUITimer implements IUITimer
{

  private Timer t;

  public SwingUITimer ()
  {
    this.t = new Timer (0, null);
  }

  public SwingUITimer (final long delay1, final UIActionListener uial1)
  {
    this.t = new Timer ((int)delay1, uial1);
  }

  /**
   * @param delay1
   * @see Timer#setDelay(int)
   */
  public final void setDelay (final long delay1)
  {
    this.t.setDelay ((int)delay1);
  }

  /**
   * @param initialDelay1
   * @see Timer#setInitialDelay(int)
   */
  public final void setInitialDelay (final long initialDelay1)
  {
    this.t.setInitialDelay ((int)initialDelay1);
  }

  /**
   * 
   * @see Timer#start()
   */
  public final void start ()
  {
    this.t.start ();
  }

  /**
   * 
   * @see Timer#stop()
   */
  public final void stop ()
  {
    this.t.stop ();
  }
  
}
