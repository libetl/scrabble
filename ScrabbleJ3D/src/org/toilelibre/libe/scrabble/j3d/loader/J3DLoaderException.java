package org.toilelibre.libe.scrabble.j3d.loader;

import org.toilelibre.libe.scrabble.s3d.loader.S3DLoaderException;

public class J3DLoaderException extends S3DLoaderException
{

  /**
   * 
   */
  private static final long serialVersionUID = -7184604413601705595L;

  public J3DLoaderException ()
  {
    super ();
  }

  public J3DLoaderException (final String message)
  {
    super (message);
  }

  public J3DLoaderException (final String message, final Throwable cause)
  {
    super (message, cause);
  }

  public J3DLoaderException (final Throwable cause)
  {
    super (cause);
  }

}
