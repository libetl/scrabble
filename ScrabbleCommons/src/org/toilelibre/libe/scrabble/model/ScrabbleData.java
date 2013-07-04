package org.toilelibre.libe.scrabble.model;

import java.util.LinkedList;
import java.util.List;

import org.toilelibre.libe.scrabble.model.board.Board;
import org.toilelibre.libe.scrabble.model.dictionary.Dictionary;
import org.toilelibre.libe.scrabble.model.dist.BallotBox;
import org.toilelibre.libe.scrabble.model.player.Player;

public class ScrabbleData {

	private static ScrabbleData	instance;

	/**
	 * @return the instance
	 */
	public static ScrabbleData getInstance () {
		return ScrabbleData.instance;
	}

	/**
	 * @param instance1
	 *            the instance to set
	 */
	public static void setInstance (final ScrabbleData instance1) {
		ScrabbleData.instance = instance1;
	}

	private List<BallotBox>	 ballotBoxes;

	private List<Board>	     boards;

	private List<Dictionary>	dictionaries;

	private List<Player>	 players;

	public ScrabbleData () {
		super ();
		this.boards = new LinkedList<Board> ();
		this.ballotBoxes = new LinkedList<BallotBox> ();
		this.dictionaries = new LinkedList<Dictionary> ();
		this.players = new LinkedList<Player> ();
		ScrabbleData.instance = this;
	}

	public final List<BallotBox> getBallotBoxes () {
		return this.ballotBoxes;
	}

	public final List<Board> getBoards () {
		return this.boards;
	}

	public final List<Dictionary> getDictionaries () {
		return this.dictionaries;
	}

	public final List<Player> getPlayers () {
		return this.players;
	}

	public final void setBallotBoxes (final List<BallotBox> ballotBoxes1) {
		this.ballotBoxes = ballotBoxes1;
	}

	public final void setBoards (final List<Board> boards1) {
		this.boards = boards1;
	}

	public final void setDictionaries (final List<Dictionary> dictionaries1) {
		this.dictionaries = dictionaries1;
	}

	public final void setPlayers (final List<Player> players1) {
		this.players = players1;
	}

}
