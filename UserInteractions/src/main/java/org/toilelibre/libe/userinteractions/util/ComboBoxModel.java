/**
 * 
 */
package org.toilelibre.libe.userinteractions.util;

import javax.swing.DefaultComboBoxModel;

/**
 * @author lionel
 * 
 */
public class ComboBoxModel<T> {

    private final DefaultComboBoxModel<T> dcbm;

    public ComboBoxModel () {
        this.dcbm = new DefaultComboBoxModel<T> ();
    }

    /**
     * @param obj1
     * @see DefaultComboBoxModel#addElement(java.lang.Object)
     */
    public final void addElement (final T obj1) {
        this.dcbm.addElement (obj1);
    }

    /**
     * 
     * @see DefaultComboBoxModel#clear()
     */
    public final void clear () {
        this.dcbm.removeAllElements ();
    }

    /**
     * @return the dcbm
     */
    public final Object getImpl () {
        return this.dcbm;
    }

}
