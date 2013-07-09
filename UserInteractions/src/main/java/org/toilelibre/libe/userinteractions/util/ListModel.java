/**
 * 
 */
package org.toilelibre.libe.userinteractions.util;

import javax.swing.DefaultListModel;

/**
 * @author lionel
 * 
 */
public final class ListModel<T> {

    private final DefaultListModel<T> dlm;

    public ListModel () {
        this.dlm = new DefaultListModel<T> ();
    }

    /**
     * @param obj1
     * @see DefaultListModel#addElement(java.lang.Object)
     */
    public void addElement (final T obj1) {
        this.dlm.addElement (obj1);
    }

    /**
     * 
     * @see DefaultListModel#clear()
     */
    public void clear () {
        this.dlm.clear ();
    }

    /**
     * @return the dlm
     */
    public Object getImpl () {
        return this.dlm;
    }

}
