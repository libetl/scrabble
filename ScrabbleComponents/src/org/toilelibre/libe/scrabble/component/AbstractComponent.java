/**
 * 
 */
package org.toilelibre.libe.scrabble.component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.toilelibre.libe.scrabble.model.ScrabbleData;

public abstract class AbstractComponent implements IComponent {

    private ScrabbleData data;

    public AbstractComponent () {

    }

    /**
     * @see org.toilelibre.libe.scrabble.component.IComponent#execute
     *      (java.lang.Object[])
     */
    public final Object execute (final Object... parameters1) {
        final Method [] methods = this.getClass ().getInterfaces () [0]
                .getDeclaredMethods ();
        Object res = null;
        if (methods.length > 0) {
            try {
                res = methods [0].invoke (this, parameters1);
            } catch (IllegalArgumentException e) {
                e.hashCode ();
            } catch (IllegalAccessException e) {
                e.hashCode ();
            } catch (InvocationTargetException e) {
                e.hashCode ();
            }
        }
        return res;
    }

    /**
     * @see org.toilelibre.libe.scrabble.component.IComponent#execute
     *      (java.lang.Object[])
     */
    public final Object executeAndThrow (final Object... parameters1)
            throws InvocationTargetException {
        final Method [] methods = this.getClass ().getInterfaces () [0]
                .getDeclaredMethods ();
        Object res = null;
        if (methods.length > 0) {
            try {
                res = methods [0].invoke (this, parameters1);
            } catch (IllegalArgumentException e) {
                e.hashCode ();
            } catch (IllegalAccessException e) {
                e.hashCode ();
            }
        }
        return res;
    }

    /**
     * @see org.toilelibre.libe.scrabble.component.IComponent#getData()
     */
    public final ScrabbleData getData () {
        return this.data;
    }

    /**
     * @see org.toilelibre.libe.scrabble.component.IComponent#setData
     *      (org.toilelibre.libe.scrabble.model.ScrabbleData)
     */
    public final void setData (final ScrabbleData sd) {
        this.data = sd;
    }

}
