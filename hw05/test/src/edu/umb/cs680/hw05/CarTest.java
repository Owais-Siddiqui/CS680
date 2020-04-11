package edu.umb.cs680.hw05;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class CarTest {

	private String[] carToStringArray(Car car) {
		String[] carDetails = { car.getMake(), car.getModel(), String.valueOf(car.getYear()) };
		return carDetails;
	}

	@Test
	public void verifyCarEqualityWithMakeModelYear() {
		Car actual = new Car("Toyota", "RAV4", 20, 2020, 10000);
		String[] expected = { "Toyota", "RAV4", "2020" };
		assertArrayEquals(expected, carToStringArray(actual));
	}

}
