package mru.game.model;

import java.util.ArrayList;

/**
 * This class represents each card that is being held by either the player or
 * the banker. it represents these hands using an arraylist.
 * 
 * @author Evan Gadsby
 *
 */

public class CardInHand extends Card {

	private ArrayList<Card> cards;

	/**
	 * Constructor which initializes the card in the hand
	 */
	public CardInHand() {
		this.cards = new ArrayList<Card>();
	}

	/**
	 * Adds the card to the players hand
	 * 
	 * @param card
	 */

	public void addCard(Card card) {
		cards.add(card);
	}

	/**
	 * gets the card from the arraylist
	 * 
	 * @param i
	 * @return removed card
	 */

	public Card getCard(int i) {
		return cards.get(i);
	}

	/**
	 * calls the method to get the top card from the arraylist
	 * 
	 * @return removed card
	 */
	public Card getCard() {
		int i = size() - 1;
		return getCard(i);

	}

	/**
	 * Returns the size of the arraylist
	 * 
	 * @return cards size
	 */

	public int size() {
		return cards.size();
	}

	/**
	 * Prints the card into the console in a readable way
	 * 
	 * @param i
	 */

	public void display(int i) {
		System.out.println(getCard(i));
		System.out.println();
	}

}
