package edu.umb.cs680.hw11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DominationComparatorTest {

	static LinkedList<Car> carList = new LinkedList<Car>();
	
	@BeforeAll
	public static void initialize() {
		Car car1 = new Car("Toyota", "Land Cruiser", 2020, 75000, 175000);
		Car car2 = new Car("BMW", "X6", 2017, 35000, 75000);
		Car car3 = new Car("Ford", "Icon", 2016, 30000, 30000);
		Car car4 = new Car("Tesla", "X1", 2019, 1000, 1000);
		carList.add(car1);
		carList.add(car2);
		carList.add(car3);
		carList.add(car4);
	}
	
	@Test
	void test_car1() {
		Car car1 = new Car("Toyota", "Land Cruiser", 2020, 75000, 175000);
		carList.sort(new YearComparator());
		assertEquals(car1, carList.get(0));
	}
	
	@Test
	void test_car2() {
		Car car2 = new Car("BMW", "X6", 2017, 35000, 75000);
		carList.sort(new YearComparator());
		assertEquals(car2, carList.get(2));
	}
	
	@Test
	void test_car3() {
		Car car3 = new Car("Ford", "Icon", 2016, 30000, 30000);
		carList.sort(new YearComparator());
		assertEquals(car3, carList.get(3));
	}
	
	@Test
	void test_car4() {
		Car car4 = new Car("Tesla", "X1", 2019, 1000, 1000);
		carList.sort(new YearComparator());
		assertEquals(car4, carList.get(1));
	}	
}
