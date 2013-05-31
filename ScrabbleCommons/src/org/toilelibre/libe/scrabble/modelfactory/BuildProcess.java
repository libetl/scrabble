package org.toilelibre.libe.scrabble.modelfactory;

import java.util.Locale;

import org.toilelibre.libe.scrabble.model.ModelElement;

public interface BuildProcess
{
  String getFile ();

  Locale getLocale ();

  ModelElement process ();

  void setFile (String fileset);

  void setLocale (Locale l);
}
