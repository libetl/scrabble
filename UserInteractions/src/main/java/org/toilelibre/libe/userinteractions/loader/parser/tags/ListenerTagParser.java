package org.toilelibre.libe.userinteractions.loader.parser.tags;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.toilelibre.libe.userinteractions.model.interactions.Interaction;
import org.xml.sax.Attributes;

public class ListenerTagParser implements TagParser {
	public ListenerTagParser() {

	}

	@Override
	public final void parse(final UserInteractions ui,
			final String[] currentAction, final String name,
			final Attributes atts) {
		final Interaction interaction = ui.getInteraction(currentAction[0]);

		if (atts.getValue(UIConstants.EVENT_ATTRIBUTE) != null) {

			final int instanceId = interaction.createInstance(
					atts.getValue(UIConstants.ID_ATTRIBUTE),
					atts.getValue(UIConstants.TYPE_ATTRIBUTE));
			interaction.addEvent(instanceId,
					atts.getValue(UIConstants.EVENT_ATTRIBUTE),
					atts.getValue(UIConstants.METHOD_ATTRIBUTE));
		} else if (atts.getValue(UIConstants.EVENTS_ATTRIBUTE) != null) {
			final String[] events = atts.getValue(UIConstants.EVENTS_ATTRIBUTE)
					.split("" + UIConstants.SEPARATOR);
			final int instanceId = interaction.createInstance(
					atts.getValue(UIConstants.ID_ATTRIBUTE),
					atts.getValue(UIConstants.TYPE_ATTRIBUTE));
			for (final String event : events) {
				interaction.addEvent(instanceId, event.trim(),
						atts.getValue(UIConstants.METHOD_ATTRIBUTE));
			}
		}
	}
}
