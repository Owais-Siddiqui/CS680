package edu.umb.cs680.hw11;

import java.util.Comparator;

public class ParetoComparator implements Comparator<Car>{

	@Override
	public int compare(Car car1, Car car2) {
		if(car2.getPrice()>=car1.getPrice() && car1.getYear()>=car2.getYear() && car2.getMileage()>=car1.getMileage()) {
			if(car2.getPrice()>car1.getPrice() || car1.getYear()>car2.getYear() || car2.getMileage()>car1.getMileage())
				return -1;
			return 0;
		}
		return 0;
	}
}
