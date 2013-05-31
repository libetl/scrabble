/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import java.util.LinkedList;
import java.util.List;

import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.IValidateInsertionComponent;
import org.toilelibre.libe.scrabble.model.board.Board;
import org.toilelibre.libe.scrabble.model.board.placements.Insertion;
import org.toilelibre.libe.scrabble.model.board.placements.Placement;
import org.toilelibre.libe.scrabble.model.exception.ScrabbleModelException;

public class ValidateInsertionComponent extends AbstractComponent implements
    IValidateInsertionComponent
{

  /**
   * 
   */
  public ValidateInsertionComponent ()
  {
  }

  /**
   * @throws ScrabbleModelException
   * @see org.toilelibre.libe.scrabble.component.iface.
   * IValidateInsertionComponent#validate()
   */
  public final String[] validate (final Insertion i)
      throws ScrabbleModelException
  {
	List<String> words = new LinkedList<String> ();
    String word = "";
    final Board board = this.getData ().getBoards ().get (0);
    // Contrôles d'intégrité
    this.integrityCheck (i);

    char [] row = null;
    if (i.nbOfLetters () > 2)
    {
      // Contrôle de l'alignement
      row = this.alignCheck (board, i);
    }

    if (i.nbOfLetters () == 1)
    {
      word = "" + i.getPlacement (0).getLetter ();

    } else if (i.nbOfLetters () == 2)
    {

      word = this.buildWordOnly2Letters (i);
    } else if (row != null)
    {
      // Les lettres doivent être placées consécutivement
      word = this.buildWord (row);
    }

    if (!this.getData ().getDictionaries ().get (0).contains (
        word.toLowerCase ()))
    {
      throw new ScrabbleModelException ("Mot non présent : " + word);
    }
    return (String[])words.toArray();
  }

  private void integrityCheck (final Insertion i) throws ScrabbleModelException
  {
    final Board b = this.getData ().getBoards ().get (0);
    for (final Placement p : i)
    {
      // Contrôles d'intégrité
      if ((p.getX () >= Board.COLS) || (p.getY () >= Board.ROWS)
          || (p.getX () < 0) || (p.getY () < 0))
      {
        throw new ScrabbleModelException ("Placement en dehors des limites"
            + "du plateau " + p);
      }
      if (b.getCellLetter (p.getX (), p.getY ()) != 0)
      {
        throw new ScrabbleModelException ("Essai de placement par dessus une"
            + " lettre " + p);
      }
    }
  }

  private char [] alignCheck (final Board b,
      final Insertion i) throws ScrabbleModelException
  {
    char [] row = null;
    final Placement p1 = i.getPlacement (0);
    final Placement p2 = i.getPlacement (1);
    final int dHorz = p2.getX () - p1.getX ();
    final int dVert = p2.getY () - p1.getY ();
    if (dHorz == 0)
    {
      row = this.alignCheckX (i);
      this.completeRowX (b, i, row, p1.getX ());
    } else if (dVert == 0)
    {
      row = this.alignCheckY (i);
      this.completeColY (b, i, row, p1.getY ());
    } else
    {
      throw new ScrabbleModelException (Board.NOT_ALIGNED);
    }
    return row;
  }

  /**
   * @param b1
   * @param row1
   * @param y1
   */
  private void completeColY (final Board b1, final Insertion i,
      final char [] row1, final int row)
  {
    int max = 0;
    int min = Board.COLS;
    for (Placement p : i)
    {
      if (min > p.getX ())
      {
        min = p.getX ();
      }
      if (max < p.getX ())
      {
        max = p.getX ();
      }
    }
    while (min > 0 && b1.getCellLetter (min - 1, row) != Board.EM)
    {
      min--;
    }
    while (max < Board.COLS - 1 && b1.getCellLetter (max + 1, row) != Board.EM)
    {
      max++;
    }
    for (int j = min ; j < max ; j++)
    {
      if (b1.getCellLetter (j, row) != Board.EM)
      {
        row1 [j] = b1.getCellLetter (j, row);
      }
    }
  }

  /**
   * @param b1
   * @param row1
   */
  private void completeRowX (final Board b1, final Insertion i,
      final char [] row1, final int col)
  {
    int max = 0;
    int min = Board.ROWS;
    for (Placement p : i)
    {
      if (min > p.getY ())
      {
        min = p.getY ();
      }
      if (max < p.getY ())
      {
        max = p.getY ();
      }
    }
    while (min > 0 && b1.getCellLetter (col, min - 1) != Board.EM)
    {
      min--;
    }
    while (max < Board.ROWS - 1 && b1.getCellLetter (col, max + 1) != Board.EM)
    {
      max++;
    }    
    for (int j = min ; j < max ; j++)
    {
      if (b1.getCellLetter (col, j) != Board.EM)
      {
        row1 [j] = b1.getCellLetter (col, j);
      }
    }
  }

  /**
   * @param i1
   * @return
   * @throws ScrabbleModelException 
   */
  private char [] alignCheckX (final Insertion i) throws ScrabbleModelException
  {
    final Placement p1 = i.getPlacement (0);
    final char [] row = new char [Board.COLS];
    for (final Placement p : i)
    {
      if (p.getX () - p1.getX () != 0)
      {
        throw new ScrabbleModelException (Board.NOT_ALIGNED + p);
      }
      row[p.getY ()] = p.getLetter ();
    }
    return row;
  }

  /**
   * @param i1
   * @return
   * @throws ScrabbleModelException 
   */
  private char [] alignCheckY (final Insertion i) throws ScrabbleModelException
  {
    final Placement p1 = i.getPlacement (0);
    final char [] row = new char [Board.ROWS];
    for (final Placement p : i)
    {
      if (p.getY () - p1.getY () != 0)
      {
        throw new ScrabbleModelException (Board.NOT_ALIGNED + p);
      }
      row[p.getX ()] = p.getLetter ();
    }
    return row;
  }
  
  private String buildWordOnly2Letters (final Insertion i)
      throws ScrabbleModelException
  {
    String word;
    final Placement p1 = i.getPlacement (0);
    final Placement p2 = i.getPlacement (1);
    if (!((Math.abs (p1.getX () - p2.getX ()) == 1) && 
        (p1.getY () - p2.getY () == 0))
        && !((p1.getX () - p2.getX () == 0) && (Math.abs (p1.getY ()
            - p2.getY ()) == 1)))
    {
      throw new ScrabbleModelException (Board.NOT_CONTINUED);
    }
    if ((p1.getX () < p2.getX ()) || (p1.getY () < p2.getY ()))
    {
      word = "" + p1.getLetter () + p2.getLetter ();
    } else
    {
      word = "" + p2.getLetter () + p1.getLetter ();
    }

    return word;
  }

  private String buildWord (final char [] row) throws ScrabbleModelException
  {
    String word = "";
    int j = 0;
    while ((j < row.length) && (row[j] == 0))
    {
      j++;
    }
    while ((j < row.length) && (row[j] != 0))
    {
      word += row[j];
      j++;
    }
    while ((j < row.length) && (row[j] == 0))
    {
      j++;
    }
    if (j != row.length)
    {
      throw new ScrabbleModelException (Board.NOT_CONTINUED);
    }
    return word;
  }
}
