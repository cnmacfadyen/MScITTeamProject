package commandline;

import java.util.ArrayList;

public class Player {

	private int playerNumber;
	private String playerName;
	private int wonRound;

	/**
	 * Default constructor for Player
	 */
	public Player() {

	}

	/**
	 * Constructor for Player with parameters
	 * 
	 * @param playerName   Name of player
	 * @param playerNumber Player number
	 */
	public Player(String playerName, int playerNumber) {
		this.playerName = playerName;
		this.playerNumber = playerNumber;
	}

	/**
	 * 
	 * @param p ArrayList of players in the game
	 * @return False if arraylist Player size is equal or greater than 2 and if not
	 *         then return true
	 */
	public boolean gameWinner(ArrayList<Player> p) {
		if (p.size() >= 2) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Get the number of rounds each player has won for a game
	 * 
	 * @return wonRound {@link Player#wonRound}
	 */
	public int getWonRound() {
		return wonRound;
	}

	/**
	 * Each time a player wins a round the wonRound gets incremented
	 * 
	 * @return {@link Player#wonRound} wonRound incremented
	 */
	public int getPlayerWonRound() {
		return wonRound++;
	}

	/**
	 * Getter for playerNumber
	 * 
	 * @return playerNymber {@link Player#playerNumber}
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * Setter for setting playerNumber
	 * 
	 * @param num Number to set each player
	 * @return playerNumber {@link Player#playerNumber}
	 */
	public int setPlayerNumber(int num) {
		return playerNumber = num;
	}

	/**
	 * Getter for playerName
	 * 
	 * @return playerName {@link Player#playerName}
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Setter for setting playerName
	 * 
	 * @param playerName {@link Player#playerName}
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}
