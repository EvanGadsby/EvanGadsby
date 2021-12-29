package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mru.game.model.CardDeck;

class DeckCreationTest {

	/**
	 * This test consists of checking that a deck contains 52 cards, then removing
	 * every card from that deck. This leaves the total cards to 0. Lastly we create
	 * a new deck and see that there are now 52 cards back in the deck
	 * 
	 * @author Evan Gadsby
	 */

	@Test
	void test() {
		CardDeck Deck = new CardDeck();
		assertEquals(52, Deck.size());
		int FullDeck = Deck.size();
		for (int i = 0; i < FullDeck; i++) {
			Deck.drawCard();
		}
		assertEquals(0, Deck.size());
		Deck.createDeck();
		assertEquals(52, Deck.size());
	}

}
