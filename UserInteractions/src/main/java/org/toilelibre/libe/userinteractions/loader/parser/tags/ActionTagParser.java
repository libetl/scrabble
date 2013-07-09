package org.toilelibre.libe.userinteractions.loader.parser.tags;

import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.interactions.Interaction;
import org.toilelibre.libe.userinteractions.timers.impl.IUITimer;
import org.xml.sax.Attributes;

public class ActionTagParser implements TagParser {
    private static final Logger LOG = Logger.getLogger (ActionTagParser.class);

    public ActionTagParser() {

    }

    @Override
    public final void parse (final UserInteractions ui,
            final String [] currentAction, final String name,
            final Attributes atts) {
        try {
            final Class<?> clazz = Class.forName (atts
                    .getValue (UIConstants.IMPL_ATTRIBUTE));
            currentAction [0] = atts.getValue (UIConstants.IMPL_ATTRIBUTE);
            final Action action = (Action) clazz.newInstance ();
            action.setTimers (new HashMap<String, IUITimer> ());
            action.setListeners (new LinkedList<EventListener> ());
            action.setUiOwner (ui);
            final Interaction interaction = new Interaction ();
            interaction.setAction (action);
            interaction.setBean (ui.getBean (atts
                    .getValue (UIConstants.BEAN_TAG)));
            ui.addInteraction (currentAction [0], interaction);
        } catch (final ClassNotFoundException e) {
            ActionTagParser.LOG.error (e);
        } catch (final InstantiationException e) {
            ActionTagParser.LOG.error (e);
        } catch (final IllegalAccessException e) {
            ActionTagParser.LOG.error (e);
        }
    }
}
