/**
 * 
 */
package org.toilelibre.libe.scrabble.component;

import java.lang.reflect.InvocationTargetException;

import org.toilelibre.libe.scrabble.model.ScrabbleData;

public interface IComponent {

    Object execute (final Object... parameters);

    Object executeAndThrow (final Object... parameters)
            throws InvocationTargetException;

    ScrabbleData getData ();

    void setData (final ScrabbleData sd);
}
