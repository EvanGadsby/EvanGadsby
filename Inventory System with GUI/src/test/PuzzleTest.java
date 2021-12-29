package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mru.tsc.model.Puzzle;

class PuzzleTest {

	/**
	 * The following class tests to make sure that an puzzle can be created, later
	 * than returning the toString method.
	 */
	@Test
	void TestPuzzle() {
		Puzzle test1 = new Puzzle("5234567891", "test", "Testy", 12.12, 4, 7, "L");
		String PuzzleTest = test1.toString();
		assertEquals(
				"Puzzle [SN=5234567891, name=test, brand=Testy, price=12.12, availableCount=4, ageAppropraite=7, puzzleType=L]",
				PuzzleTest);
	}
}
