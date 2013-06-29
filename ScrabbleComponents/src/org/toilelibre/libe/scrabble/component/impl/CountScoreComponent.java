/**
 * 
 */
package org.toilelibre.libe.scrabble.component.impl;

import java.util.HashMap;
import java.util.Map;

import org.toilelibre.libe.scrabble.component.AbstractComponent;
import org.toilelibre.libe.scrabble.component.iface.ICountScoreComponent;
import org.toilelibre.libe.scrabble.model.board.Board;
import org.toilelibre.libe.scrabble.model.board.placements.Insertion;
import org.toilelibre.libe.scrabble.model.board.placements.Placement;
import org.toilelibre.libe.scrabble.model.dist.BallotBox;

public final class CountScoreComponent extends AbstractComponent implements
        ICountScoreComponent {

    public CountScoreComponent () {

    }

    private int coeffPosition (int x, int y) {
        final Board b = this.getData ().getBoards ().get (0);
        int coeff = 1;
        if (Board.DW == b.getCellPoints (x, y)) {
            coeff = 2;
        } else if (Board.TW == b.getCellPoints (x, y)) {
            coeff = 3;
        }
        return coeff;
    }

    /**
     * @see org.toilelibre.libe.scrabble.component.iface.ICountScoreComponent
     *      #count(org.toilelibre.libe.scrabble.model.board.placements.Insertion)
     */
    public int count (final Insertion i1) {
        Map<String, Integer> counts = this.countWordsAround (i1);
        return this.sumValues (counts);
    }

    private int countPosition (int x, int y) {
        final BallotBox bb = this.getData ().getBallotBoxes ().get (0);
        final Board b = this.getData ().getBoards ().get (0);
        int scoreIncr = 0;
        if (Board.DL == b.getCellPoints (x, y)) {
            scoreIncr += bb.getValue (b.getCellLetter (x, y)) * 2;
        } else if (Board.TL == b.getCellPoints (x, y)) {
            scoreIncr += bb.getValue (b.getCellLetter (x, y)) * 3;
        } else {
            scoreIncr += bb.getValue (b.getCellLetter (x, y));
        }
        return scoreIncr;
    }

    private String countWordAroundDirX (Placement p, Insertion i) {
        int score = this.countPosition (p.getX (), p.getY ());
        int coeff = this.coeffPosition (p.getX (), p.getY ());
        final BallotBox bb = this.getData ().getBallotBoxes ().get (0);
        final Board b = this.getData ().getBoards ().get (0);
        StringBuffer sb = new StringBuffer ("" + p.getLetter ());
        int x = p.getX ();
        while (--x > 0 && b.getCellLetter (x, p.getY ()) != 0
                || i.contains (x, p.getY ())) {
            if (i.contains (x, p.getY ())) {
                sb.insert (0, i.placementAt (x, p.getY ()).getLetter ());
                score += this.countPosition (x, p.getY ());
                coeff *= this.coeffPosition (x, p.getY ());
            } else {
                sb.insert (0, b.getCellLetter (x, p.getY ()));
                score += bb.getValue (b.getCellLetter (x, p.getY ()));
            }
        }
        final int x1 = x;
        x = p.getX ();
        while (++x < Board.COLS && b.getCellLetter (x, p.getY ()) != 0
                || i.contains (x, p.getY ())) {
            if (i.contains (x, p.getY ())) {
                sb.append (i.placementAt (x, p.getY ()).getLetter ());
                score += this.countPosition (x, p.getY ());
                coeff *= this.coeffPosition (x, p.getY ());
            } else {
                sb.append (b.getCellLetter (x, p.getY ()));
                score += bb.getValue (b.getCellLetter (x, p.getY ()));
            }
        }
        score *= coeff;
        return "{" + x1 + "," + p.getY () + "}{" + x + "," + p.getY () + "}"
                + sb.toString () + "/" + score;
    }

    private String countWordAroundDirY (Placement p, Insertion i) {
        int score = this.countPosition (p.getX (), p.getY ());
        int coeff = this.coeffPosition (p.getX (), p.getY ());
        final Board b = this.getData ().getBoards ().get (0);
        final BallotBox bb = this.getData ().getBallotBoxes ().get (0);
        StringBuffer sb = new StringBuffer ("" + p.getLetter ());
        int y = p.getY ();
        while (--y > 0
                && (b.getCellLetter (p.getX (), y) != 0 || i.contains (
                        p.getX (), y))) {
            if (i.contains (p.getX (), y)) {
                sb.insert (0, i.placementAt (p.getX (), y).getLetter ());
                score += this.countPosition (p.getX (), y);
                coeff *= this.coeffPosition (p.getX (), y);
            } else {
                sb.insert (0, b.getCellLetter (p.getX (), y));
                score += bb.getValue (b.getCellLetter (p.getX (), y));
            }
        }
        final int y1 = y;
        y = p.getY ();
        while (++y < Board.ROWS
                && (b.getCellLetter (p.getX (), y) != 0 || i.contains (
                        p.getX (), y))) {
            if (i.contains (p.getX (), y)) {
                sb.append (i.placementAt (p.getX (), y).getLetter ());
                score += this.countPosition (p.getX (), y);
                coeff *= this.coeffPosition (p.getX (), y);
            } else {
                sb.append (b.getCellLetter (p.getX (), y));
                score += bb.getValue (b.getCellLetter (p.getX (), y));
            }
        }
        score *= coeff;
        return "{" + p.getX () + "," + y1 + "}{" + p.getX () + "," + y + "}"
                + sb.toString () + "/" + score;
    }

    private Map<String, Integer> countWordsAround (Insertion i) {
        Map<String, Integer> counts = new HashMap<String, Integer> ();

        final Board b = this.getData ().getBoards ().get (0);
        for (Placement p : i) {
            if (p.getY () - 1 > 0
                    && (b.getCellLetter (p.getX (), p.getY () - 1) != 0 || i
                            .contains (p.getX (), p.getY () - 1))
                    || p.getY () + 1 < Board.ROWS
                    && (b.getCellLetter (p.getX (), p.getY () + 1) != 0 || i
                            .contains (p.getX (), p.getY () + 1))) {
                String [] countEntry = this.countWordAroundDirY (p, i).split (
                        "/");
                counts.put (countEntry [0], Integer.parseInt (countEntry [1]));
            }
            if (p.getX () - 1 > 0
                    && (b.getCellLetter (p.getX () - 1, p.getY ()) != 0 || i
                            .contains (p.getX () - 1, p.getY ()))
                    || p.getX () + 1 < Board.COLS
                    && (b.getCellLetter (p.getX () + 1, p.getY ()) != 0 || i
                            .contains (p.getX () + 1, p.getY ()))) {
                String [] countEntry = this.countWordAroundDirX (p, i).split (
                        "/");
                counts.put (countEntry [0], Integer.parseInt (countEntry [1]));
            }
        }
        return counts;
    }

    private int sumValues (Map<String, Integer> counts) {
        int value = 0;
        for (Integer i : counts.values ()) {
            value += i.intValue ();
        }
        return value;
    }
}
