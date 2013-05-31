package org.toilelibre.libe.scrabble.model.player;

import java.util.LinkedList;
import java.util.List;

import org.toilelibre.libe.scrabble.model.tray.Tray;

public class Player
{

  private String             name;
  private int                score;
  private Tray               tray;
  private Type               type;

  private final List<String> words;

  public Player (final String name1)
  {
    super ();
    this.name = name1;
    this.score = 0;
    this.tray = new Tray ();
    this.words = new LinkedList<String> ();
    this.type = Type.Human;
  }

  public Player (final String name1, final boolean computerPlayer)
  {
    this (name1);
    if (computerPlayer)
    {
      this.type = Type.Computer;
    }
  }

  public final void addTurn (final String word1, final int scoreAdd)
  {
    this.words.add (word1);
    this.score += scoreAdd;
  }

  public final String getName ()
  {
    return this.name;
  }

  public final int getScore ()
  {
    return this.score;
  }

  public final Tray getTray ()
  {
    return this.tray;
  }

  public final String getType ()
  {
    return this.type.toString ();
  }

  public final List<String> getWords ()
  {
    return this.words;
  }

  public final boolean isComputerType ()
  {
    return this.type == Type.Computer;
  }

  public final boolean isHumanType ()
  {
    return this.type == Type.Human;
  }

  public final void setComputerType ()
  {
    this.type = Type.Computer;
  }

  public final void setHumanType ()
  {
    this.type = Type.Human;
  }

  public final void setName (final String name1)
  {
    this.name = name1;
  }

  public final void setTray (final Tray tray1)
  {
    this.tray = tray1;
  }

}
