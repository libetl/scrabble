package org.toilelibre.libe.scrabble.model.dist;

public final class Letter {
    private final char name;
    private int        number;
    private final int  value;

    public Letter (final char n, final int v, final int num) {
        this.name = n;
        this.value = v;
        this.number = num;
    }

    public void descrNumber () {
        this.number -= 1;
    }

    public char getName () {
        return this.name;
    }

    public int getNumber () {
        return this.number;
    }

    public int getValue () {
        return this.value;
    }

    public boolean isAvailable () {
        return this.number > 0;
    }

    public void setNumber (final int number1) {
        this.number = number1;
    }

}
