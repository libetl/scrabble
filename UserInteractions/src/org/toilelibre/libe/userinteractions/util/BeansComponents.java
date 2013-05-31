/**
 * 
 */
package org.toilelibre.libe.userinteractions.util;



/**
 * @author lionel
 *
 */
public final class BeansComponents
{

  private BeansComponents ()
  {
    
  }

  public static String getText (final Object component)
  {
    return ((javax.swing.JTextField)component).getText ();
  }

  public static Boolean isChecked (final Object component)
  {
    return 
    new Boolean (((javax.swing.JCheckBox)component).getModel ().isSelected ());
  }

  /**
   * @param console1
   * @param dlm1
   */
  public static void setListModel (final Object console1, final ListModel dlm1)
  {
    ((javax.swing.JList)console1).setModel (
        (javax.swing.ListModel)dlm1.getImpl ());
    
  }

  /**
   * @param scoresTable1
   * @return
   */
  public static TableModel getTableModel (final Object table)
  {
    if (((javax.swing.JTable)table).getModel () == null)
    {
      ((javax.swing.JTable)table).setModel (
          new javax.swing.table.DefaultTableModel ());
    }
    return new TableModel (((javax.swing.JTable)table).getModel ());
  }

  /**
   * @param skinCbx1
   * @param model
   */
  public static void setComboBoxModel (
      final Object skinCbx1, final ComboBoxModel model)
  {
    ((javax.swing.JComboBox)skinCbx1).setModel (
       (javax.swing.ComboBoxModel) model.getImpl ()); 
  }

  /**
   * @param skinCbx1
   * @return
   */
  public static Object getComboBoxValue (final Object skinCbx1)
  {
    return ((javax.swing.JComboBox)skinCbx1).getSelectedItem ();
  }
}
