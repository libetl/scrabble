package org.toilelibre.libe.scrabble.s3d.model;

public interface ILetterBranchGroup extends IBranchGroup {

    void affectLetter (char name);

    void setIdTray (int i);

    void setPoints (int value);

    void setTransform (ITransform ist);

    char getLetter ();

    void setOld ();

    int getIdTray ();

}
