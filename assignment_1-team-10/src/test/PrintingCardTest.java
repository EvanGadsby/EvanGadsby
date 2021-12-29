package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.game.model.Card;
import mru.game.model.CardDeck;
import mru.game.model.CardInHand;

class PrintingCardTest {

	/**
	 * This test demonstrates that creating a card using the card parameters (in
	 * this case 3 and clubs) creates a card and will print it out in the proper
	 * format
	 * 
	 * @author Evan Gadsby
	 */

	@BeforeEach
	public void createcards() {
		CardDeck deck = new CardDeck();
		CardInHand hand = new CardInHand();

	}

	@Test
	void test() {
		Card test = new Card(3, "Clubs");
		String output = test.toString();
		assertEquals("3 of Clubs", output);
	}
}
