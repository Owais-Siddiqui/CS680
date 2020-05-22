package edu.umb.cs680.hw13.observer;

import java.util.Observable;
import java.util.Observer;

public class THREEDObserver implements Observer {

	@Override
	public void update(Observable observable, Object arg) {
		if (arg instanceof DJIAEvent) {
			
			DJIAEvent djiaEvent = (DJIAEvent) arg;
			System.out.print("3D Observer DJIAEvent			: " + djiaEvent.getDjia());
			
		} else if (arg instanceof StockEvent) {
			
			StockEvent stockEvent = (StockEvent) arg;
			System.out.print("3D Observer StockEvent		: " + stockEvent.getTicker() + " " + stockEvent.getQuote()); 
			
		}
	}
}
