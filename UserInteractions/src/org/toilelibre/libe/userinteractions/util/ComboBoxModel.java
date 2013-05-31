/**
 * 
 */
package org.toilelibre.libe.userinteractions.util;


import javax.swing.DefaultComboBoxModel;

/**
 * @author lionel
 *
 */
public class ComboBoxModel
{

  private DefaultComboBoxModel dcbm;

  public ComboBoxModel ()
  {
    this.dcbm = new DefaultComboBoxModel ();
  }
  
  /**
   * @param obj1
   * @see DefaultComboBoxModel#addElement(java.lang.Object)
   */
  public final void addElement (final Object obj1)
  {
    this.dcbm.addElement (obj1);
  }

  /**
   * @return the dcbm
   */
  public final Object getImpl ()
  {
    return this.dcbm;
  }

  /**
   * 
   * @see DefaultComboBoxModel#clear()
   */
  public final void clear ()
  {
    this.dcbm.removeAllElements ();
  }
  
}
