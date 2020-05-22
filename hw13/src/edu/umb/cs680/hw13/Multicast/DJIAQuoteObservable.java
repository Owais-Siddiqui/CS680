package edu.umb.cs680.hw13.multicast;

import java.util.ArrayList;

public class DJIAQuoteObservable {

	public ArrayList<DJIAQuoteObserver> observers = new ArrayList<DJIAQuoteObserver>();
	private boolean changed;

	public void addObserver(DJIAQuoteObserver observer) {
		this.observers.add(observer);
	}

	public void notifyObservers(DJIAEvent arg) {
		if (hasChanged()) {
			for (DJIAQuoteObserver observe : this.observers) {
				observe.updateDJIA(arg);
			}
			clearChanged();
		}
	}

	public void changeQuote(float q) {
		setChanged();
		notifyObservers(new DJIAEvent(q));
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