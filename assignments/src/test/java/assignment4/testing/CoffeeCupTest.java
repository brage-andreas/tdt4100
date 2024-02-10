package assignment4.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoffeeCupTest {
	private CoffeeCup cup;

	@BeforeEach
	public void setup() {
		cup = new CoffeeCup(20.0, 0.0);
	}

	@Test
	@DisplayName("Constructor")
	public void testConstructor() {
		assertEquals(20.0, cup.getCapacity());
		assertEquals(0.0, cup.getCurrentVolume());
	}

	@Test
	@DisplayName("Add coffee")
	public void testAddCoffee() {
		cup.fillCoffee(10.0);
		assertEquals(10.0, cup.getCurrentVolume());

		cup.fillCoffee(5.0);
		assertEquals(15.0, cup.getCurrentVolume());

		cup.fillCoffee(5.0);
		assertEquals(20.0, cup.getCurrentVolume());

		assertThrows(IllegalArgumentException.class, () -> {
			cup.fillCoffee(5.0);
		});
	}

	@Test
	@DisplayName("Remove coffee")
	public void testRemoveCoffee() {
		cup.fillCoffee(10.0);

		cup.drinkCoffee(5.0);
		assertEquals(5.0, cup.getCurrentVolume());

		cup.drinkCoffee(5.0);
		assertEquals(0.0, cup.getCurrentVolume());

		assertThrows(IllegalArgumentException.class, () -> {
			cup.drinkCoffee(5.0);
		});
	}

	@Test
	@DisplayName("Add coffee and increase capacity")
	public void testAddCoffeeAndIncreaseCapacity() {
		cup.fillCoffee(10.0);
		assertEquals(10.0, cup.getCurrentVolume());

		cup.increaseCupSize(10.0);
		assertEquals(30.0, cup.getCapacity());

		cup.fillCoffee(20.0);
		assertEquals(30.0, cup.getCurrentVolume());
	}
}
