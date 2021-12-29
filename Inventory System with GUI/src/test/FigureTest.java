package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mru.tsc.model.Figure;

class FigureTest {

	/**
	 * The following class tests to make sure that a figure can be created, later
	 * than returning the toString method.
	 */
	@Test
	void TestFigure() {
		Figure test1 = new Figure("1234567891", "test", "Testy", 12.12, 4, 7, "Doll");
		String FigureTest = test1.toString();
		assertEquals(
				"Figure [SN=1234567891, name=test, brand=Testy, price=12.12, availableCount=4, ageAppropraite=7, classification=D]",
				FigureTest);
	}
}
