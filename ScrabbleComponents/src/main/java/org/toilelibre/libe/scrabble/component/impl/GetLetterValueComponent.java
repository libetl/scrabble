/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.IGetLetterValueComponent;

public class GetLetterValueComponent extends AbstractComponent implements
        IGetLetterValueComponent {

    /**
   * 
   */
    public GetLetterValueComponent() {
    }

    /**
     * @see org.toilelibre.libe.scrabble.component.iface.IGetLetterValueComponent
     *      #valueOf(char)
     */
    @Override
    public final int valueOf (final char c1) {
        return this.getData ().getBallotBoxes ().get (0).getValue (c1);
    }

}
