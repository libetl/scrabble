package org.toilelibre.libe.userinteractions.loader.parser.tags;

import org.apache.log4j.Logger;
import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.exception.UIException;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.xml.sax.Attributes;

public class CallbackTagParser implements TagParser {
    private static final Logger LOG = Logger.getLogger (CallbackTagParser.class);

    public CallbackTagParser () {

    }

    @Override
    public final void parse (final UserInteractions ui,
            final String [] currentAction, final String name,
            final Attributes atts) {
        try {
            ui.setCallback (atts.getValue (UIConstants.IMPL_ATTRIBUTE),
                    atts.getValue (UIConstants.METHOD_ATTRIBUTE));
        } catch (final UIException e) {
            CallbackTagParser.LOG.error (e.getMessage ());
            CallbackTagParser.LOG.error (e.getCause ().getMessage ());
        }
    }
}
