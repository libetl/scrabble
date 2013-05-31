package org.toilelibre.libe.scrabble.s3d.util.move;

import java.util.HashMap;
import java.util.Map;

import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;

public final class MoveObjectHelper
{

  private static Map<String, IMoveObject> instances = 
                                            new HashMap<String, IMoveObject> ();

  private MoveObjectHelper ()
  {
    
  }
  
  public static void move (final ICanvas3D iScrabbleCanvas3D,
      final String type, final Object stg1, final int... params)
  {
    if (!(stg1 instanceof ITransformGroup))
    {
      return;
    }
    final ITransformGroup stg = (ITransformGroup) stg1;
    IMoveObject imo;
    imo = MoveObjectHelper.instances.get (type);
    if (imo == null)
    {
      try
      {
        final Class<?> clazz = Class.forName (IMoveObject.class.getPackage ()
            .getName ()
            + ".Move" + type);
        imo = (IMoveObject) clazz.newInstance ();
        MoveObjectHelper.instances.put (type, imo);
      } catch (final ClassNotFoundException e)
      {
        e.hashCode ();
      } catch (final InstantiationException e)
      {
        e.hashCode ();
      } catch (final IllegalAccessException e)
      {
        e.hashCode ();
      }
    }
    if (imo != null)
    {
      imo.move (iScrabbleCanvas3D, stg, params);
    }
  }

}
