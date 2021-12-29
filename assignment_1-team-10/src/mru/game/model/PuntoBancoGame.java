package mru.game.model;

import java.io.IOException;
import java.util.Scanner;

import mru.game.controller.GameManager;

public class PuntoBancoGame extends CardDeck {

	/**
	 * This class plays the punto banco game. It goes through all the steps of the
	 * game and then checks how much money the player has won or lost.
	 * 
	 * @author Evan Gadsby
	 */

	CardInHand playerCard = null;
	CardInHand bankerCard = null;
	private CardDeck gameDeck;
	char winnerChoice;
	private int playerScore;
	private int bankerScore;
	Card newCard = new Card();
	boolean extraPlayerCard = false;
	boolean extraBankerCard = false;
	int thirdPlayerCard;
	int payout;
	int bet;
	int totalcash;
	int wins = 0;

	GameManager gm;
	Player player;

	/**
	 * In this class you implement the game You should use CardDeck class here See
	 * the instructions for the game rules
	 */

	Scanner input;

	/**
	 * Constructor which initializes the Scanner, Creates the card deck, and gets
	 * the game started when called
	 * 
	 * @throws IOException
	 */

	public PuntoBancoGame(int money) throws IOException {
		input = new Scanner(System.in);
		totalcash = money;
		gameDeck = new CardDeck();

		playGame();

	}

	/**
	 * Goes though the steps to actually play the game, this involves calling
	 * several other methods for a variety of reasons
	 * 
	 * @throws IOException
	 */
	public void playGame() throws IOException {
		// Calling the method to allow the player to choose Punto, Banco, or a Tie
		winnerChoice = betChoice();
		// Creates all the different cards and sets the players and bankers scores to
		// zero
		initializeGame();
		// Allows the player to choose how much cash to bet
		bet = askBet();

		// This if statement skips to the end of the game if either side has a score
		// above seven

		if (playerScore < 8 && bankerScore < 8)

		{
			// Draws another card if the player has a score of 5 or bellow, and then checks
			// if the banker needs another one based of the players draw
			if (playerScore < 6) {
				playerThirdDraw();
				bankerDrawCheck(thirdPlayerCard);
			}

			// Draws an additional card for the banker if the players score is 6 or under
			// the bankers score is 5 or under, and the banker has not already drawn a card
			if (playerScore >= 6 && bankerScore < 6 && extraBankerCard == false) {
				bankerThirdDraw();
			}
		}

		// Prints the players cards with a label indicating they are the players cards
		System.out.println("PLAYERS CARDS");
		playerCard.display(0);
		playerCard.display(1);
		// To prevent errors this if statement checks if a third card was drawn by the
		// player
		// before displaying it
		if (extraPlayerCard == true) {
			playerCard.display(2);
		}

		// Prints out the bankers cards with a label indicating they are the bankers
		// cards
		System.out.println("BANKERS CARDS");
		bankerCard.display(0);
		bankerCard.display(1);
		// To prevent errors this if statement checks if a third card was drawn by the
		// banker
		// before displaying it
		if (extraBankerCard == true) {
			bankerCard.display(2);
		}

		// Prints however many cards are left in the deck
		System.out.println("Cards Left:" + gameDeck.size());
		System.out.println(" ");

		// prints the players and bankers score
		System.out.println("Player Total: " + playerScore);
		System.out.println(" ");
		System.out.println("Banker Total: " + bankerScore);
		System.out.println(" ");
		// Calls the method to determine who the winner is
		determineWinner();
		System.out.println(" ");
		// Informs the player of how much money they lost or won
		results();
		System.out.println(" ");

	}

	/**
	 * Asks the player if they would like to bet on the Punto, Banco, or a tie. Also
	 * displays an error message if they do not make a valid selection
	 * 
	 * @return option
	 */
	public char betChoice() {
		boolean correctInput = false;
		char option = 0;
		while (correctInput == false) {
			System.out.println("Who do you want to bet on?");
			System.out.println("\t(P) Player Wins");
			System.out.println("\t(B) Banker Wins");
			System.out.println("\t(T) Tie Game");
			System.out.print("Enter a choice:");
			option = input.nextLine().toUpperCase().charAt(0);
			// This if statement will cause the this method to repeat if the user does
			// not enter a valid input
			if (option == 'P' || option == 'B' || option == 'T') {
				correctInput = true;
			} else {
				System.out.println("Please choose a valid option");
			}
		}
		return option;
	}

	/**
	 * Sets the game up by creating objects for the players cards, the bankers card,
	 * as well as setting each sides score to zero. It also draws the first 4 cards
	 * already, but does not actually show them to the player yet
	 */
	public void initializeGame() {
		playerCard = new CardInHand();
		bankerCard = new CardInHand();
		extraPlayerCard = false;
		extraBankerCard = false;
		playerScore = 0;
		bankerScore = 0;

		newCard = gameDeck.drawCard();
		playerCard.addCard(newCard);
		addPlayerScore(newCard.getRank());

		newCard = gameDeck.drawCard();
		bankerCard.addCard(newCard);
		addBankerScore(newCard.getRank());

		newCard = gameDeck.drawCard();
		playerCard.addCard(newCard);
		addPlayerScore(newCard.getRank());

		newCard = gameDeck.drawCard();
		bankerCard.addCard(newCard);
		addBankerScore(newCard.getRank());
	}

	/**
	 * Calculates the players score based on the card they picked up. It does this
	 * by setting each face card to zero and using remainder division to keep the
	 * total under 10
	 * 
	 * @param rank
	 */
	public void addPlayerScore(int rank) {
		int cardValue = rank;
		if (rank == 10 || rank == 11 || rank == 12 || rank == 13) {
			cardValue = 0;
		}

		playerScore = (playerScore + cardValue) % 10;

	}

	/**
	 * Calculates the bankers score based on the card they picked up. It does this
	 * by setting each face card to zero and using remainder division to keep the
	 * total under 10
	 * 
	 * @param rank
	 */

	public void addBankerScore(int rank) {
		int cardValue = rank;
		if (rank == 10 || rank == 11 || rank == 12 || rank == 13) {
			cardValue = 0;
		}

		bankerScore = (bankerScore + cardValue) % 10;
	}

	/**
	 * Draws a third card for the player and sets extraPlayerCard to true, which
	 * indicates that a third card will need to be displayed later
	 */
	public void playerThirdDraw() {
		newCard = gameDeck.drawCard();
		playerCard.addCard(newCard);
		addPlayerScore(newCard.getRank());
		extraPlayerCard = true;
	}

	/**
	 * Draws a third card for the banker and sets extraBankerCard to true, which
	 * indicates that a third card will need to be displayed later
	 */

	public void bankerThirdDraw() {
		newCard = gameDeck.drawCard();
		bankerCard.addCard(newCard);
		addBankerScore(newCard.getRank());
		extraBankerCard = true;
	}

	/**
	 * Uses switch statements to determine if the banker needs to draw an additional
	 * card based on the card the player drew and the current score of the banker
	 * 
	 * @param playerCard
	 */
	public void bankerDrawCheck(int playerCard) {
		switch (playerCard) {
		case 1:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
			if (bankerScore < 4) {
				bankerThirdDraw();
			}
			break;
		case 2:
		case 3:
			if (bankerScore < 5) {
				bankerThirdDraw();
			}
			break;
		case 4:
		case 5:
			if (bankerScore < 6) {
				bankerThirdDraw();
			}
			break;
		case 6:
		case 7:
			if (bankerScore < 7) {
				bankerThirdDraw();
			}
			break;
		case 8:
			if (bankerScore < 3) {
				bankerThirdDraw();
			}
			break;
		}
	}

	/**
	 * Determines if the game is a Punto win, a Banco win, or a Egalite. If the
	 * player won under any of these circumstances it calculates the players payout,
	 * or sets the payout to zero if the player lost under any of these
	 * circumstances
	 */
	public void determineWinner() {
		payout = 0;
		if (bankerScore > playerScore) {
			System.out.println("Banco has scored higher!");
			if (winnerChoice == 'B') {
				payout = 2;
			}
		}
		if (playerScore > bankerScore) {
			System.out.println("Punco has scored higher!");
			if (winnerChoice == 'P') {
				payout = 2;
			}
		}
		if (playerScore == bankerScore) {
			System.out.println("Its an Egalite!");
			if (winnerChoice == 'T') {
				payout = 5;
			}
		}

	}

	/**
	 * Asks the player how much they would like to bet and sets that as the bet.
	 * Also returns an error if the bet is not an integer.
	 * 
	 * @return bet
	 */

	public int askBet() {
		boolean validBet = false;
		int bet = 0;
		while (validBet == false) {
			System.out.println("How much would you like to bet this round?");
			if (input.hasNextInt()) {
				bet = input.nextInt();
				input.nextLine();
				if (bet > totalcash) {
					System.out.println("you cant bet more than you have!");
				}
				if (bet <= totalcash) {
					validBet = true;
				}
			} else {
				System.out.println("Please input an integer");
				input.nextLine();
			}
		}
		return bet;
	}

	/**
	 * Calculates the players winnings based on their bet and the result of the game
	 * and shows an appropriate message. Also asks the player if they would like to
	 * play again.
	 * 
	 * @throws IOException
	 */

	public void results() throws IOException {
		boolean correctInput = false;
		int winnings = bet * payout;
		if (winnings > 0) {
			System.out.println("You have won $" + winnings + " from this round");
			wins++;
		} else {
			System.out.println("You have lost your wager of $" + bet);
		}
		totalcash = totalcash - bet + winnings;
		while (correctInput == false) {
			System.out.println("Would you like to play again? (Y/N)");
			char again = input.nextLine().toUpperCase().charAt(0);
			if (again == 'Y') {
				correctInput = true;
				playGame();
				input.nextLine();
			}
			if (again == 'N') {
				correctInput = true;

			} else {
				System.out.println("Please enter either a Y or an N");
			}
		}
	}

	/**
	 * Return the wins the player got this game.
	 * 
	 * @return wins
	 */
	public int getwins() {
		return wins;
	}

	/**
	 * Return the players total balance at the end of this game.
	 * 
	 * @return totalcash
	 */
	public int gettotalcash() {
		return totalcash;
	}

}
