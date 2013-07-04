package org.toilelibre.libe.userinteractions.loader.parser.tags;

import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.xml.sax.Attributes;

public interface TagParser {
	void parse (UserInteractions ui, String [] currentAction, String name,
	        Attributes atts);
}
