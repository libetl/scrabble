package org.toilelibre.libe.scrabble.s3d.model;

public interface ILetterBranchGroup extends IBranchGroup {

    void affectLetter (char name);

    int getIdTray ();

    char getLetter ();

    void setIdTray (int i);

    void setOld ();

    void setPoints (int value);

    void setTransform (ITransform ist);

}
