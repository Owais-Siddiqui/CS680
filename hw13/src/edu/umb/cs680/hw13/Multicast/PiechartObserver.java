package edu.umb.cs680.hw13.multicast;

public class PiechartObserver implements DJIAQuoteObserver, StockQuoteObserver {

	@Override
	public void updateDJIA(DJIAEvent arg) {
		DJIAEvent djiaEvent = (DJIAEvent) arg;
		System.out.print("PieChart Observer DJIAEvent		: " + djiaEvent.getDjia());
	}
	
	@Override
	public void updateStock(StockEvent arg) {
		StockEvent stockEvent = (StockEvent) arg;
		System.out.print("PieChart Observer StockEvent		: " + stockEvent.getTicker() + " " + stockEvent.getQuote());
	}
}
