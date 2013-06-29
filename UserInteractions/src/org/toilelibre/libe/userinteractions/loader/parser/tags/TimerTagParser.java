package org.toilelibre.libe.userinteractions.loader.parser.tags;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.toilelibre.libe.userinteractions.model.interactions.Interaction;
import org.xml.sax.Attributes;

public class TimerTagParser implements TagParser {

    public TimerTagParser () {

    }

    public final void parse (final UserInteractions ui,
            final String [] currentAction, final String name,
            final Attributes atts) {
        final Interaction interaction = ui.getInteraction (currentAction [0]);
        interaction.addTimer (atts.getValue (UIConstants.NAME_ATTRIBUTE),
                Long.parseLong (atts.getValue (UIConstants.DELAY_ATTRIBUTE)),
                atts.getValue (UIConstants.METHOD_ATTRIBUTE));
    }
}
