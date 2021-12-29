package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.tsc.model.Animal;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Figure;

class FigureTest {
	
	/**
	 * This class tests to make sure that we can create an Figure object and that Figure
	 * can return a proper toString method
	 * @author Evan Gadsby
	 */

	@Test
	void test() {
			Figure test1 = new Figure("1234567891", "test", "Testy", 12.12, 4, 7, "Doll");
			String FigureTest = test1.toString();
			assertEquals("Figure [SN=1234567891, name=test, brand=Testy, price=12.12, availableCount=4, ageAppropraite=7, classification=D]", FigureTest);
			
		}

	}


