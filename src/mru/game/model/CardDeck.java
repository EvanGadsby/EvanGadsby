package mru.game.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a card deck.
 * 
 * @author khosro salmani
 * @version 1.0
 */
public class CardDeck {

	/**
	 * deck holds all of the cards that currently are in the current deck
	 */
	private ArrayList<Card> deck;

	/**
	 * This constructor initiate the arraylist and calls the repective methods to
	 * create a new deck
	 */
	public CardDeck() {
		deck = new ArrayList<Card>();
		createDeck();
		shuffleDeck();
	}

	/**
	 * This method creates the deck
	 */
	public void createDeck() {
		// suits holds the name of the suits
		String[] suits = { "Spades", "Diamonds", "Clubs", "Hearts" };

		/*
		 * The for loop creates a whole new deck based on their suit and rank
		 */
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= 13; j++) {
				deck.add(new Card(j, suits[i]));
			}
		}

	}

	/**
	 * Gets a card from the arraylist and deals it out. Also creates a new deck if
	 * the current one is empty
	 * 
	 * @return dealtcard
	 */
	public Card drawCard() {
		if (size() == 0) {
			createDeck();
			shuffleDeck();
		}
		Card dealtcard;
		dealtcard = deck.get(0);
		deck.remove(0);
		return dealtcard;
	}

	/**
	 * Returns the amount of cards still in the deck
	 * 
	 * @return deck size
	 */

	public int size() {
		return deck.size();
	}

	/**
	 * this method shuffle the deck after creating a new deck
	 */
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	/**
	 * The deck getter method
	 * 
	 * @return the deck
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}
}
