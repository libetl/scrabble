package org.toilelibre.libe.scrabble.modelfactory.board.loader;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.model.board.Board;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class BoardXmlContentHandler implements ContentHandler {
	private static final Logger LOG = Logger
			.getLogger(BoardXmlContentHandler.class);
	private final Board board;
	private int col;
	private int row;

	public BoardXmlContentHandler(final Board b) {
		this.board = b;
		this.row = -1;
		this.col = -1;
	}

	/**
	 * Evenement recu a chaque fois que l'analyseur rencontre des caracteres
	 * (entre deux balises).
	 * 
	 * @param ch
	 *            les caracteres proprement dits.
	 * @param start
	 *            le rang du premier caractere a traiter effectivement.
	 * @param end
	 *            le rang du dernier caractere a traiter effectivement
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(final char[] ch, final int start, final int length)
			throws SAXException {
	}

	/**
	 * Evenement envoye a la fin de l'analyse du flux xml.
	 * 
	 * @throws SAXException
	 *             en cas de probleme quelquonque ne permettant pas de
	 *             considerer l'analyse du document comme etant complete.
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
	}

	/**
	 * Evenement recu a chaque fermeture de balise.
	 * 
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(final String uri, final String localName,
			final String name) throws SAXException {
	}

	/**
	 * Fin de traitement de l'espace de nommage.
	 * 
	 * @param prefixe
	 *            le prefixe choisi a l'ouverture du traitement de l'espace
	 *            nommage.
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 */
	@Override
	public void endPrefixMapping(final String prefix) throws SAXException {
	}

	/**
	 * Recu chaque fois que des caracteres d'espacement peuvent etre ignores au
	 * sens de XML. C'est a dire que cet evenement est envoye pour plusieurs
	 * espaces se succedant, les tabulations, et les retours chariot se
	 * succedants ainsi que toute combinaison de ces trois types d'occurrence.
	 * 
	 * @param ch
	 *            les caracteres proprement dits.
	 * @param start
	 *            le rang du premier caractere a traiter effectivement.
	 * @param end
	 *            le rang du dernier caractere a traiter effectivement
	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
	 */
	@Override
	public void ignorableWhitespace(final char[] ch, final int start,
			final int length) throws SAXException {
	}

	/**
	 * Rencontre une instruction de fonctionnement.
	 * 
	 * @param target
	 *            la cible de l'instruction de fonctionnement.
	 * @param data
	 *            les valeurs associees a cette cible. En general, elle se
	 *            presente sous la forme d'une serie de paires nom/valeur.
	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void processingInstruction(final String target, final String data)
			throws SAXException {
	}

	/**
	 * Definition du locator qui permet a tout moment pendant l'analyse, de
	 * localiser le traitement dans le flux. Le locator par defaut indique, par
	 * exemple, le numero de ligne et le numero de caractere sur la ligne.
	 * 
	 * @author smeric
	 * @param value
	 *            le locator a utiliser.
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	@Override
	public void setDocumentLocator(final Locator locator) {
	}

	/**
	 * Recu a chaque fois qu'une balise est evitee dans le traitement a cause
	 * d'un probleme non bloque par le parser. Pour ma part je ne pense pas que
	 * vous en ayez besoin dans vos traitements.
	 * 
	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	 */
	@Override
	public void skippedEntity(final String name) throws SAXException {
	}

	/**
	 * Evenement envoye au demarrage du parse du flux xml.
	 * 
	 * @throws SAXException
	 *             en cas de probleme quelquonque ne permettant pas de se lancer
	 *             dans l'analyse du document.
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
	}

	/**
	 * Evenement recu a chaque fois que l'analyseur rencontre une balise xml
	 * ouvrante.
	 * 
	 * @param nameSpaceURI
	 *            l'url de l'espace de nommage.
	 * @param localName
	 *            le nom local de la balise.
	 * @param rawName
	 *            nom de la balise en version 1.0
	 *            <code>nameSpaceURI + ":" + localName</code>
	 * @throws SAXException
	 *             si la balise ne correspond pas a ce qui est attendu, comme
	 *             par exemple non respect d'une dtd.
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String,
	 *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public final void startElement(final String uri, final String localName,
			final String name, final Attributes atts) throws SAXException {
		if ("row".equals(localName)) {
			this.row += 1;
			this.col = -1;
		} else if ("col".equals(localName)) {
			this.col += 1;
			final String value = atts.getValue("value");
			if (value != null) {
				try {
					this.board.setCellPoints(this.col, this.row, Board.class
							.getField(value).getChar(null));
				} catch (final IllegalArgumentException e) {
					BoardXmlContentHandler.LOG.error(e.getMessage());
				} catch (final SecurityException e) {
					BoardXmlContentHandler.LOG.error(e.getMessage());
				} catch (final IllegalAccessException e) {
					BoardXmlContentHandler.LOG.error(e.getMessage());
				} catch (final NoSuchFieldException e) {
					BoardXmlContentHandler.LOG.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * Debut de traitement dans un espace de nommage.
	 * 
	 * @param prefixe
	 *            utilise pour cet espace de nommage dans cette partie de
	 *            l'arborescence.
	 * @param URI
	 *            de l'espace de nommage.
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void startPrefixMapping(final String prefix, final String uri)
			throws SAXException {
	}

}
