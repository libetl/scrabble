package org.toilelibre.libe.scrabble.sessions;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.log4j.Logger;

public final class ScrabbleSessionsHandler
{
  private static final char           AT          = '@';
  private static final Logger         LOG         = Logger
                                                      .getLogger (ScrabbleSessionsHandler.class);
  private static Map<String, Session> sessions;
  private static final String         USERNAMEKEY = "user.name";

  public static void create ()
  {
    ScrabbleSessionsHandler.create (System
        .getProperty (ScrabbleSessionsHandler.USERNAMEKEY));
  }

  public static void create (final String username)
  {
    try
    {
      final String sessionName = username + ScrabbleSessionsHandler.AT
          + InetAddress.getLocalHost ();
      ScrabbleSessionsHandler.sessions.put (sessionName, new Session ());

      ScrabbleSessionsHandler.LOG.info ("Session : " + sessionName);
    } catch (final UnknownHostException e1)
    {
      ScrabbleSessionsHandler.LOG.error (e1.getMessage ());
    }
  }

  public static Session get ()
  {
    return ScrabbleSessionsHandler.get (System
        .getProperty (ScrabbleSessionsHandler.USERNAMEKEY));
  }

  public static Session get (final String username)
  {
    try
    {
      final String sessionName = username + ScrabbleSessionsHandler.AT
          + InetAddress.getLocalHost ();
      return ScrabbleSessionsHandler.sessions.get (sessionName);
    } catch (final UnknownHostException e1)
    {
      ScrabbleSessionsHandler.LOG.error (e1.getMessage ());
    }
    return null;
  }

  public ScrabbleSessionsHandler ()
  {

  }

  public Map<String, Session> getSessions ()
  {
    return ScrabbleSessionsHandler.sessions;
  }

  public void setSessions (final Map<String, Session> sessions1)
  {
    ScrabbleSessionsHandler.sessions = sessions1;
  }
}
