/**
 * 
 */
package org.toilelibre.libe.scrabble.applicationlayer.beans;

import org.toilelibre.libe.userinteractions.util.ComboBoxModel;

/**
 * @author lionel
 * 
 */
public class SkinListBean extends ComboBoxModel<Class<?>> {
    public SkinListBean () {
    }

    /**
   * 
   */
    public SkinListBean (final Class<?> [] cL) {
        for (Class<?> c : cL) {
            this.addElement (c);
        }
    }

}
