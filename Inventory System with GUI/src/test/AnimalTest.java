package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mru.tsc.model.Animal;

class AnimalTest {

	/**
	 * The following class tests to make sure that an animal object can be created,
	 * later than returning the toString method.
	 */
	@Test
	void TestAnimal() {
		Animal test1 = new Animal("2234567891", "test", "Testy", 12.12, 4, 7, "Rubber", "Large");
		String testAnimal = test1.toString();
		assertEquals(
				"Animal [SN=2234567891, name=test, brand=Testy, price=12.12, availableCount=4, ageAppropraite=7, Material=Rubber, size=L]",
				testAnimal);
	}
}
