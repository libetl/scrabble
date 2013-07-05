package org.toilelibre.libe.scrabble.model.board.placements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Insertion implements Iterable<Placement> {
    private final List<Placement> placements;

    public Insertion() {
        super();
        this.placements = new ArrayList<Placement>();
    }

    public final boolean addPlacement (final Placement p) {
        return this.placements.add(p);
    }

    public final boolean contains (final int x, final int y) {
        for (final Placement p : this) {
            if ((p.getX() == x) && (p.getY() == y)) {
                return true;
            }
        }
        return false;
    }

    public final boolean contains (final Placement p) {
        return this.placements.contains(p);
    }

    public final Placement getPlacement (final int index) {
        return this.placements.get(index);
    }

    public final List<Placement> getPlacements () {
        return this.placements;
    }

    public final Iterator<Placement> iterator () {
        return this.placements.iterator();
    }

    public final int nbOfLetters () {
        return this.placements.size();
    }

    public final Placement placementAt (final int x, final int y) {
        for (final Placement p : this) {
            if ((p.getX() == x) && (p.getY() == y)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public final String toString () {
        String res = "Insertion : [";

        for (final Placement p : this.placements) {
            res += p.toString() + " ";
        }
        return res + "]";
    }
}
