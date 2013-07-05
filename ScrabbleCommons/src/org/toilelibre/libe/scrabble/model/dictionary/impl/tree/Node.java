package org.toilelibre.libe.scrabble.model.dictionary.impl.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Node {

    private List<Node> children;
    private Node       nextSibling;
    private Node       parent;
    private boolean    term;
    private char       val;

    public Node(final char value) {
        this.val = value;
        this.nextSibling = null;
        this.parent = null;
        this.children = new LinkedList<Node> ();
        this.term = false;
    }

    public Node(final char value, final Node... childNodes) {
        this.val = value;
        this.nextSibling = null;
        this.parent = null;
        final List<Node> ln = new LinkedList<Node> ();
        ln.addAll (Arrays.asList (childNodes));
        this.setChildNodes (ln);
        this.term = false;
    }

    public final void addChild (final Node n) {
        this.children.add (n);
        n.parent = this;
        if (this.children.size () > 1) {
            this.children.get (this.children.size () - 2).nextSibling = n;
        }
    }

    public final Node findSibling (final char value) {
        Node res = this;
        while ( (res != null) && (res.val != value)) {
            res = res.nextSibling;
        }
        return res;
    }

    public final Node getChild (final char value) {
        Node res = null;
        int i = 0;
        while ( (i < this.children.size ()) && (res == null)) {
            if (this.children.get (i).getValue () == value) {
                res = this.children.get (i);
            }
            i += 1;
        }
        return res;
    }

    public final List<Node> getChildNodes () {
        return this.children;
    }

    public final int getChildNum (final char value) {
        int i = 0;
        while (i < this.children.size ()) {
            if (this.children.get (i).getValue () == value) {
                return i;
            }
            i += 1;
        }
        return -1;
    }

    public final Node getChildNum (final int index) {
        return this.children.get (index);
    }

    public final Node getNextSibling () {
        return this.nextSibling;
    }

    public final Node getParent () {
        return this.parent;
    }

    public final char getValue () {
        return this.val;
    }

    public final boolean isTerm () {
        return this.term;
    }

    public final void removeChild (final int num) {
        if ( (num > 0) && (this.children.size () > 2)) {
            this.children.get (num - 1).nextSibling = this.children
                    .get (num + 1);
        }
        if ( (num == (this.children.size () - 1))
                && (this.children.size () > 1)) {
            this.children.get (num - 1).nextSibling = null;
        }
        this.children.remove (num);
    }

    public final void removeChild (final Node n) {
        this.removeChild (this.getChildNum (n.getValue ()));
    }

    public final void removeChildValue (final char value) {
        this.removeChild (this.getChildNum (value));
    }

    public final void setChildNodes (final List<Node> childNodes) {
        this.children = childNodes;
        for (int i = 0 ; i < (this.children.size () - 1) ; i += 1) {
            this.children.get (i).nextSibling = this.children.get (i + 1);
            this.children.get (i).parent = this;
        }
        this.children.get (this.children.size () - 1).parent = this;
    }

    public final void setTerm (final boolean term2) {
        this.term = term2;
    }

    public final void setValue (final char val2) {
        this.val = val2;
    }

}
