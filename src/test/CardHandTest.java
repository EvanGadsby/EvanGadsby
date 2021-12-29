package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mru.game.model.CardDeck;
import mru.game.model.CardInHand;

class CardHandTest {

	/**
	 * This class tests to make sure that three cards can be drawn to a hand by
	 * drawing three cards to a hand and then checking if that hand does in fact
	 * have three cards
	 * 
	 * @author Evan Gadsby
	 */
	@Test
	void test() {
		CardDeck deck = new CardDeck();
		CardInHand Hand = new CardInHand();

		Hand.addCard(deck.drawCard());
		Hand.addCard(deck.drawCard());
		Hand.addCard(deck.drawCard());
		int output = Hand.size();
		assertEquals(3, output);
	}

}
