package exceptions;

public class NegativeInputPrice extends Exception {
	/**
	 * Exception class to handle when the user enters a negative number as the price for a toy
	 */
	public NegativeInputPrice()
	{
		super("Please enter a positive double number, the price cannot be negative");
	}
}
