package org.toilelibre.libe.userinteractions.loader.parser.tags;

import org.apache.log4j.Logger;
import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;
import org.xml.sax.Attributes;

public class BeanTagParser implements TagParser {
	private static final Logger	LOG	= Logger.getLogger (BeanTagParser.class);

	public BeanTagParser () {

	}

	public final void parse (final UserInteractions ui,
	        final String [] currentAction, final String name,
	        final Attributes atts) {
		try {
			final Class<?> clazz = Class.forName (atts
			        .getValue (UIConstants.IMPL_ATTRIBUTE));
			final IUIBean bean = (IUIBean) clazz.newInstance ();
			ui.addBean (atts.getValue (UIConstants.NAME_ATTRIBUTE), bean);
		} catch (final ClassNotFoundException e) {
			BeanTagParser.LOG.error (e.getMessage ());
			BeanTagParser.LOG.error (e.getCause ().getMessage ());
		} catch (final InstantiationException e) {
			BeanTagParser.LOG.error (e.getMessage ());
			BeanTagParser.LOG.error (e.getCause ().getMessage ());
		} catch (final IllegalAccessException e) {
			BeanTagParser.LOG.error (e.getMessage ());
			BeanTagParser.LOG.error (e.getCause ().getMessage ());
		}
	}
}
