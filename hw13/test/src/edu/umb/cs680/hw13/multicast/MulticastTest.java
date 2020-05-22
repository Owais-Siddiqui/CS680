package edu.umb.cs680.hw13.multicast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class MulticastTest {
	
	StockQuoteObservable stock = new StockQuoteObservable();
	DJIAQuoteObservable djia = new DJIAQuoteObservable();

	PiechartObserver piechartObserver = new PiechartObserver();
	TableObserver tableObserver = new TableObserver();
	THREEDObserver threeDObserver = new THREEDObserver();
	
	@Test
	public void Test_DJIA() {
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(byteArrayOutputStream));
		Random randomNumber = new Random(10);
		float quote = randomNumber.nextFloat() * 10;
		
		djia.addObserver(threeDObserver);
		djia.addObserver(tableObserver);
		djia.addObserver(piechartObserver);
		
		djia.changeQuote(quote);
		
		assertEquals("3D Observer DJIAEvent	: " + quote + "Table Observer DJIAEvent	: " + quote 
					+ "PieChart Observer DJIAEvent	: " + quote, byteArrayOutputStream.toString());
		
		byteArrayOutputStream.reset();
		quote = randomNumber.nextFloat() * 10;
		djia.changeQuote(quote);
		
		assertEquals("3D Observer DJIAEvent	: " + quote + "Table Observer DJIAEvent	: " + quote 
				+ "PieChart Observer DJIAEvent	: " + quote, byteArrayOutputStream.toString());
	}

	@Test
	public void Test_Stock() {
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(byteArrayOutputStream));
		Random randomNumber = new Random(10);
		float quote = randomNumber.nextFloat() * 10;
		
		stock.addObserver(threeDObserver);
		stock.addObserver(tableObserver);
		stock.addObserver(piechartObserver);
		
		stock.changeQuote("Lenovo", quote);
		
		assertEquals("3D Observer StockEvent : " + "Lenovo" + " " + quote + "Table Observer StockEvent	: " + "Lenovo" 
					+ " " + quote + "PieChart Observer StockEvent	: " + "Lenovo" + " " 
					+ quote, byteArrayOutputStream.toString());
		
		byteArrayOutputStream.reset();
		quote = randomNumber.nextFloat() * 10;
		stock.changeQuote("LG", quote);
		
		assertEquals("3D Observer StockEvent	: " + "LG" + " " + quote + "Table Observer StockEvent	: " + "LG" 
					+ " " + quote + "PieChart Observer StockEvent	: " + "LG" + " " 
					+ quote, byteArrayOutputStream.toString());
	}
}