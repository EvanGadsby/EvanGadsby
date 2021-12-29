package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mru.game.model.Card;
import mru.game.model.CardDeck;

class ShuffleDeckTest {

	/**
	 * This test consists of checking if a deck has been shuffled after the shuffled
	 * method is called. It does this by creating a deck and then assigning the
	 * cards of that deck to an arraylist. Next it assigns stores the values of the
	 * top six cards of the deck into Strings (the old cards). It then shuffles that
	 * same deck and assigns it back into the arraylist. Now the arraylist contains
	 * the shuffled deck. It then takes the top six cards from that arraylist and
	 * assigns them into Strings (the new Cards). Lastly it checks if any of the old
	 * cards are equals to the new cards. If any of them are the test fails. But if
	 * they are all different (because they were shuffled) the test is a success.
	 * 
	 * @author Evan Gadsby
	 */

	private ArrayList<Card> cardlist;
	private String oldCard1;
	private String oldCard2;
	private String newCard1;
	private String newCard2;
	private String oldCard3;
	private String oldCard4;
	private String newCard3;
	private String newCard4;
	private String oldCard5;
	private String oldCard6;
	private String newCard5;
	private String newCard6;

	@Test
	void test() {
		CardDeck deck = new CardDeck();
		cardlist = deck.getDeck();
		oldCard1 = cardlist.get(0).toString();
		oldCard2 = cardlist.get(1).toString();
		oldCard3 = cardlist.get(2).toString();
		oldCard4 = cardlist.get(3).toString();
		oldCard5 = cardlist.get(4).toString();
		oldCard6 = cardlist.get(5).toString();
		deck.shuffleDeck();
		cardlist = deck.getDeck();
		newCard1 = cardlist.get(0).toString();
		newCard2 = cardlist.get(1).toString();
		newCard3 = cardlist.get(2).toString();
		newCard4 = cardlist.get(3).toString();
		newCard5 = cardlist.get(4).toString();
		newCard6 = cardlist.get(5).toString();
		boolean expectedResult = true;
		if (oldCard1 == newCard1) {
			expectedResult = false;
		}
		if (oldCard2 == newCard2) {
			expectedResult = false;
		}
		if (oldCard3 == newCard3) {
			expectedResult = false;
		}
		if (oldCard4 == newCard4) {
			expectedResult = false;
		}
		if (oldCard5 == newCard5) {
			expectedResult = false;
		}
		if (oldCard6 == newCard6) {
			expectedResult = false;
		}
		assertEquals(expectedResult, true);

	}
}
