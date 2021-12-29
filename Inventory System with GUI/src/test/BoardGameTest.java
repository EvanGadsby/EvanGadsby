package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mru.tsc.model.BoardGame;

class BoardGameTest {

	/**
	 * The following class tests to make sure that a Board Game can be created,
	 * later than returning the toString method.
	 */
	@Test
	void TestBoardGame() {
		BoardGame test1 = new BoardGame("7234567891", "test", "Testy", 12.12, 4, 7, "2-8", "John Doe, James Smith");
		String testBoardGame = test1.toString();
		assertEquals(
				"BoardGame [SN=7234567891, name=test, brand=Testy, price=12.12, availableCount=4, ageAppropraite=7, players=2-8, designers=John Doe, James Smith]",
				testBoardGame);
	}
}
