package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.tsc.model.Animal;

class AnimalTest {
	
	/**
	 * This class tests to make sure that we can create an animal object and that animal
	 * can return a proper toString method
	 * @author Evan Gadsby
	 */

	@Test
	void test() {
		Animal test1 = new Animal("2234567891", "test", "Testy", 12.12, 4, 7, "Rubber", "Large");
		String testAnimal = test1.toString();
		assertEquals("Animal [SN=2234567891, name=test, brand=Testy, price=12.12, availableCount=4, ageAppropraite=7, Material=Rubber, size=L]", testAnimal);
	}

}
