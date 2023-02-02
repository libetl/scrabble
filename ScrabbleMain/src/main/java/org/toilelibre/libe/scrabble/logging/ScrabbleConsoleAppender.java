/**
 * 
 */
package org.toilelibre.libe.scrabble.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.appender.AbstractWriterAppender;
import org.apache.logging.log4j.core.appender.WriterManager;
import org.apache.logging.log4j.core.filter.LevelMatchFilter;
import org.apache.logging.log4j.core.layout.SyslogLayout;
import org.toilelibre.libe.userinteractions.util.BeansComponents;
import org.toilelibre.libe.userinteractions.util.ListModel;

import java.io.IOException;
import java.io.Writer;

/**
 * @author lionel
 * 
 */
public final class ScrabbleConsoleAppender extends AbstractWriterAppender
{

  private static ListModel<String> dlm;

  /**
   * @param console1
   */
  public static void setImpl (final Object console1)
  {
    BeansComponents.setListModel (console1, ScrabbleConsoleAppender.dlm);
  }

  private static final StringLayout layout =
          new SyslogLayout.Builder<>().setIncludeNewLine(true)
                  .setEscapeNL("\n")
                  .build();
  public ScrabbleConsoleAppender ()
  {
    super("Console Appender", layout,
            LevelMatchFilter.newBuilder().setLevel(Level.ALL).build(),
            true,
            true,
            new WriterManager(new Writer() {
              @Override
              public void write(char[] chars, int i, int i1) throws IOException {

                if (ScrabbleConsoleAppender.dlm != null)
                {
                  ScrabbleConsoleAppender.dlm.addElement (new String(chars));
                }
              }

              @Override
              public void flush() throws IOException {

              }

              @Override
              public void close() throws IOException {

                if (ScrabbleConsoleAppender.dlm != null)
                {
                  ScrabbleConsoleAppender.dlm.clear ();
                }
              }
            }, "listmodelWriter", layout, false)
    );
    ScrabbleConsoleAppender.dlm = new ListModel<String> ();
  }
}
