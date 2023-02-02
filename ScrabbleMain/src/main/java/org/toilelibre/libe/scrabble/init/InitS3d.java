package org.toilelibre.libe.scrabble.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.exception.ScrabbleException;
import org.toilelibre.libe.scrabble.properties.ScrabbleMessages;
import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.loader.IS3DLoader;
import org.toilelibre.libe.scrabble.s3d.loader.S3DLoaderException;
import org.toilelibre.libe.scrabble.s3d.model.AbstractBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.Canvas3DKeeper;
import org.toilelibre.libe.scrabble.s3d.model.IBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;

public final class InitS3d implements IScrabbleInit
{

  private static final Logger LOG = LogManager.getLogger (InitS3d.class);

  public InitS3d ()
  {

  }

  @Override
  public void init () throws ScrabbleException
  {
    try
    {
      final int m = 1000;
      InitS3d.LOG.info ("Canvas 3D");
      ScrabbleBeansHelper.getBean ("s3dHelper");
      final String fichier3D = ScrabbleMessages.getMessage ("scenery.impl");
      final String fichierLetter3D = ScrabbleMessages
          .getMessage ("scenery.letter.impl");
      final String clazz = ScrabbleMessages.getMessage ("j3dloader.clazz");
      final IS3DLoader s3dl = (IS3DLoader) ScrabbleBeansHelper
          .getBean ("s3dLoader");
      Object ret = s3dl.init (clazz, fichier3D);
      while (Canvas3DKeeper.getCanvas () == null)
      {
        try
        {
          Thread.sleep (m);
        } catch (final InterruptedException e)
        {
          e.hashCode ();
        }
      }
      final ICanvas3D sc3d = Canvas3DKeeper.getCanvas ();
      final IBranchGroup sbg = sc3d.getScrabbleBranchGroup ();
      ((AbstractBranchGroup) sbg).setBranchGraph (ret);
      ret = s3dl.init (clazz, fichierLetter3D);
      S3DHelper.newLetterBranchGroup (ret);
    } catch (final S3DLoaderException e)
    {
      throw new ScrabbleException (e);
    }

  }

}
