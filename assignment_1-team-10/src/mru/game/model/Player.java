package mru.game.model;

public class Player {

	/**
	 * This class represent each player record in the Database. It is basically a
	 * model class for each record in the txt file.
	 * 
	 * @author Jurriel Tapado
	 */
	String name;
	int balance;
	int numOfWins;
	int bet;
	PuntoBancoGame pb;

	/**
	 * This constructor sets the name, balance, and number of wins of the player.
	 *
	 * @param name
	 * @param balance
	 * @param numOfWins
	 */
	public Player(String name, int balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
	}

	/**
	 * Name setter method
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Name getter method
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Balance setter method
	 * 
	 * @param balance
	 */
	public void setID(int balance) {
		this.balance = balance;
	}

	/**
	 * balance getter method
	 * 
	 * @return balance
	 */
	public int getId() {
		return balance;
	}

	/**
	 * number of wins setter method
	 * 
	 * @param numOfWins
	 */
	public void setNumOfWins(int numOfWins) {
		this.numOfWins = numOfWins;
	}

	/**
	 * number of wins getter method
	 * 
	 * @return numOfWins
	 */
	public int getNumOfWins() {
		return numOfWins;
	}

	/**
	 * Sets how the info will appear from the playersdb.txt
	 */

	public String toString() {
		return "Name:" + name + " Balance:" + balance + " Number of Wins: " + numOfWins;
	}

	/**
	 * This is the format that was used for the playersdb.txt. every important info
	 * is split by the ";" symbol
	 * 
	 * @return
	 */
	public String format() {
		return name + ";" + balance + ";" + numOfWins;
	}

	/**
	 * This method updates players number of wins after playing the game.
	 * 
	 * @param wins
	 * @return numOfWins
	 */
	public int updateWins(int wins) {
		numOfWins = numOfWins + wins;
		return numOfWins;
	}

	/**
	 * 
	 * This method updates players balance after playing the game.
	 * 
	 * @param c
	 * @return balance
	 */
	public int updatebalance(int c) {
		balance = c;
		return balance;
	}
}
