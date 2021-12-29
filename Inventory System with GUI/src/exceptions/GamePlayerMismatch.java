package exceptions;

public class GamePlayerMismatch extends Exception {
	/**
	 * Exception class to for when the minimum number of players for a board game is higher than the maximum number of players
	 */
	public GamePlayerMismatch()
	{
		super("The minimum number of players has to be lower than the maximum number of players");
	}
}
