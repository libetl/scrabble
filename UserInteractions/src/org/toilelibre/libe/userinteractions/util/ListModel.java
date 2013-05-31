/**
 * 
 */
package org.toilelibre.libe.userinteractions.util;


import javax.swing.DefaultListModel;

/**
 * @author lionel
 *
 */
public final class ListModel
{

  private DefaultListModel dlm;

  public ListModel ()
  {
    this.dlm = new DefaultListModel ();
  }
  
  /**
   * @param obj1
   * @see DefaultListModel#addElement(java.lang.Object)
   */
  public void addElement (final Object obj1)
  {
    this.dlm.addElement (obj1);
  }

  /**
   * @return the dlm
   */
  public Object getImpl ()
  {
    return this.dlm;
  }

  /**
   * 
   * @see DefaultListModel#clear()
   */
  public void clear ()
  {
    this.dlm.clear ();
  }
  
}
