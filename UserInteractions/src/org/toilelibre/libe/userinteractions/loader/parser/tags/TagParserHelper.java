package org.toilelibre.libe.userinteractions.loader.parser.tags;

import java.util.HashMap;
import java.util.Map;

import org.toilelibre.libe.userinteractions.constants.UIConstants;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.xml.sax.Attributes;

public class TagParserHelper implements TagParser {

    private static final TagParserHelper INSTANCE = new TagParserHelper();

    public static TagParserHelper getInstance () {
        return TagParserHelper.INSTANCE;
    }

    public static void parseTag (final UserInteractions ui,
            final String[] currentAction, final String name,
            final Attributes atts) {
        TagParserHelper.getInstance().parse(ui, currentAction, name, atts);
    }

    private final Map<String, TagParser> tagParsers;

    public TagParserHelper() {
        this.tagParsers = new HashMap<String, TagParser>();
        this.tagParsers.put(UIConstants.ACTION_TAG, new ActionTagParser());
        this.tagParsers.put(UIConstants.BEAN_TAG, new BeanTagParser());
        this.tagParsers.put(UIConstants.CALLBACK_TAG, new CallbackTagParser());
        this.tagParsers.put(UIConstants.LISTENER_TAG, new ListenerTagParser());
        this.tagParsers.put(UIConstants.TIMER_TAG, new TimerTagParser());
    }

    public final void parse (final UserInteractions ui,
            final String[] currentAction, final String name,
            final Attributes atts) {
        final TagParser tp = this.tagParsers.get(name);
        if (tp != null) {
            tp.parse(ui, currentAction, name, atts);
        }
    }

}
