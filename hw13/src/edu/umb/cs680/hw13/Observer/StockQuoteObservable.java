package edu.umb.cs680.hw13.observer;

import java.util.Observable;

public class StockQuoteObservable extends Observable {

	public void changeQuote(String tickers, float quote) {
		this.setChanged();
		this.notifyObservers(new StockEvent(tickers, quote));
	}
}
