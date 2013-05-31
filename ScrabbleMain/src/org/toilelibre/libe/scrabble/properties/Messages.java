package org.toilelibre.libe.scrabble.properties;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Classe tres simple pour aller chercher un fichier de ressources dans les
 * proprietes
 * 
 * @see java.util.ResourceBundle
 */
public class Messages
{
  private final String         bundleName;

  private final ResourceBundle resourceBundle;

  /**
   * 
   * Constructeur de la classe Messages
   * 
   * @param bName
   *          nom du bundle, i.e. nom de la classe appellante. Le fichier de
   *          ressource sera pris dans le dossier du .class correspondant au nom
   *          de la classe
   * 
   */
  public Messages (final String bName)
  {
    this.bundleName = bName;
    this.resourceBundle = ResourceBundle.getBundle (this.bundleName, Locale
        .getDefault ());
  }

  /**
   * 
   * Constructeur de la classe Messages
   * 
   * @param bName
   *          nom du bundle, i.e. nom de la classe appellante. Le fichier de
   *          ressource sera pris dans le dossier du .class correspondant au nom
   *          de la classe
   * @param l
   *          un Locale est un objet donnant la langue de l'environnement en
   *          cours. Le fichier de proprietes sera eventuellement recherche avec
   *          comme suffixe _nomDuPays-nomDeLaLangue, exemple _FR-FR
   * 
   * @see java.util.Locale
   */
  public Messages (final String bName, final Locale l)
  {
    this.bundleName = bName;
    this.resourceBundle = ResourceBundle.getBundle (this.bundleName, l);
  }

  /**
   * 
   * @param key
   *          Nom de la propriete a rechercher dans le fichier
   * @return Valeur trouvee pour la propriete ou null
   */
  public final String getString (final String key)
  {
    try
    {
      return this.resourceBundle.getString (key);
    } catch (final MissingResourceException e)
    {
      return '!' + key + '!';
    }
  }
}
