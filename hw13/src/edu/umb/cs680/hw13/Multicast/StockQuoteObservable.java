package edu.umb.cs680.hw13.multicast;

import java.util.ArrayList;

public class StockQuoteObservable {

	public ArrayList<StockQuoteObserver> observers = new ArrayList<StockQuoteObserver>();
	private boolean changed;

	public void addObserver(StockQuoteObserver observer) {
		this.observers.add(observer);
	}

	public void notifyObservers(StockEvent arg) {
		if (hasChanged()) {
			for (StockQuoteObserver observe : this.observers) {
				observe.updateStock(arg);
			}
			clearChanged();
		}
	}

	public void changeQuote(String tickers, float quote) {
		setChanged();
		notifyObservers(new StockEvent(tickers, quote));
	}

	public void setChanged() {
		this.changed = true;
	}

	public boolean hasChanged() {
		return this.changed;
	}

	public void clearChanged() {
		this.changed = false;
	}
}
