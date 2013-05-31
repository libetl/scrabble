package org.toilelibre.libe.scrabble.sessions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Session
{
  private final Map<String, Object> values;

  public Session ()
  {
    this.values = new HashMap<String, Object> ();
  }

  public final boolean containsKey (final Object key)
  {
    return this.values.containsKey (key);
  }

  public final Object get (final Object key)
  {
    return this.values.get (key);
  }

  public final boolean isEmpty ()
  {
    return this.values.isEmpty ();
  }

  public final Set<String> keySet ()
  {
    return this.values.keySet ();
  }

  public final Object put (final String key, final Object value)
  {
    return this.values.put (key, value);
  }

  public final void remove (final String string)
  {
    this.values.remove (string);
  }

  public final int size ()
  {
    return this.values.size ();
  }

}
