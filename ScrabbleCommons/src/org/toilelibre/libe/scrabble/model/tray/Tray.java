package org.toilelibre.libe.scrabble.model.tray;

import org.toilelibre.libe.scrabble.model.board.Board;

public class Tray {
    public static final int TRAY_LENGTH = 7;
    private char []         impl;

    public Tray () {
        this.impl = new char [Tray.TRAY_LENGTH];
        for (int i = 0 ; i < Tray.TRAY_LENGTH ; i += 1) {
            this.impl [i] = Board.EM;
        }
    }

    public final char [] getImpl () {
        return this.impl;
    }

    public final char getLetter (final int num) {
        return this.impl [num];
    }

    public final void setImpl (final char [] impl1) {
        this.impl = impl1;
    }

    public final void setLetter (final int num, final char letter) {
        this.impl [num] = letter;
    }
}
