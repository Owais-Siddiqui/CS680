package edu.umb.cs680.hw02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {


	@Test
	public void multiply30And4() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(30, 4);
		float expected = 120;
		assertEquals(expected, actual);
	}

	@Test
	public void multiplyMinus98By0() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(-98, 0);
		float expected = -0.0f;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply60AndMinus1() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(60, -1);
		float expected = -60;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply600AndMinus1() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(600, -1);
		float expected = -600;
		assertEquals(expected, actual);
	}

	@Test
	public void multiplyMinus1AndMinus10() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(-1, -10);
		float expected = 10;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply0And0() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(0, 0);
		float expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply30Point5And10() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(30.5f, 10f);
		float expected = 305f;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply100And0() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(100, 0);
		float expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply10Point10And23Point55() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(10.10f, 23.55f);
		float expected = 237.855f;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply5By3() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(5, 3);
		float expected = 15;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply7Point0And6() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(7.0f, 6f);
		float expected = 42.0f;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply1500Point15And3Thousand() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(1500.15f, 3000);
		float expected = 4500450f;
		assertEquals(expected, actual);
	}

	@Test
	public void multiplyMinus1234AndMinus12350() {
		Calculator cut = new Calculator();
		float actual = cut.multiply(-1234, -12350);
		float expected = 1.52399E7f;
		assertEquals(expected, actual);
	}

	@Test
	public void multiply3And40ThenMultiplyResultBy5() {
		Calculator cut = new Calculator();
		float result = cut.multiply(3, 40);
		float actual = cut.multiply(result, 5);
		float expected = 600;
		assertEquals(expected, actual);
	}

	@Test
	public void multiplyNegavtive100By4ThenMultiplyResultBy30() {
		Calculator cut = new Calculator();
		float result = cut.multiply(-100, 4);
		float actual = cut.multiply(result, 30);
		float expected = -12000;
		assertEquals(expected, actual);
	}

	@Test
	public void divide5By4() {
		Calculator cut = new Calculator();
		float actual = cut.divide(5, 4);
		float expected = 1.25f;
		assertEquals(expected, actual);
	}

	@Test
	public void divide1By1() {
		Calculator cut = new Calculator();
		float actual = cut.divide(1, 1);
		float expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void divide0By30() {
		Calculator cut = new Calculator();
		float actual = cut.divide(0, 30);
		float expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void divide5By0() {
		Calculator cut = new Calculator();
		try {
			cut.divide(5, 0);
			fail("Division by zero");
		} catch (IllegalArgumentException ex) {
			assertEquals("division by zero", ex.getMessage());
		}
	}

	@Test
	public void divide0By0() {
		Calculator cut = new Calculator();
		try {
			cut.divide(0, 0);
			fail("Division by zero");
		} catch (IllegalArgumentException ex) {
			assertEquals("division by zero", ex.getMessage());
		}
	}

	@Test
	public void divide6ByMinus1() {
		Calculator cut = new Calculator();
		float actual = cut.divide(6, -1);
		float expected = -6;
		assertEquals(expected, actual);
	}

	@Test
	public void divide6Point0By6() {
		Calculator cut = new Calculator();
		float actual = cut.divide(6.0f, 6);
		float expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void divide1ByMinus1() {
		Calculator cut = new Calculator();
		float actual = cut.divide(1, -1);
		float expected = -1;
		assertEquals(expected, actual);
	}

	@Test
	public void divideMinus1By1() {
		Calculator cut = new Calculator();
		float actual = cut.divide(-1, 1);
		float expected = -1;
		assertEquals(expected, actual);
	}

	@Test
	public void divideMinus1ByMinus1() {
		Calculator cut = new Calculator();
		float actual = cut.divide(-1, -1);
		float expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void divide15By3() {
		Calculator cut = new Calculator();
		float actual = cut.divide(15, 3);
		float expected = 5;
		assertEquals(expected, actual);
	}

	@Test
	public void divide20By2() {
		Calculator cut = new Calculator();
		float actual = cut.divide(20, 2);
		float expected = 10;
		assertEquals(expected, actual);
	}

	@Test
	public void divide4By2() {
		Calculator cut = new Calculator();
		float actual = cut.divide(4, 2);
		float expected = 2;
		assertEquals(expected, actual);
	}

	@Test
	public void divide2By4() {
		Calculator cut = new Calculator();
		float actual = cut.divide(2, 4);
		float expected = 0.5f;
		assertEquals(expected, actual);
	}

	@Test
	public void divide3Point5By7() {
		Calculator cut = new Calculator();
		float actual = cut.divide(3.5f, 7);
		float expected = 0.5f;
		assertEquals(expected, actual);
	}

	@Test
	public void divide10Point20By13Point55() {
		Calculator cut = new Calculator();
		float actual = cut.divide(10.20f, 13.55f);
		float expected = 0.7527675277f;
		assertEquals(expected, actual);
	}

	@Test
	public void divide20Point0By4() {
		Calculator cut = new Calculator();
		float actual = cut.divide(20.0f, 4);
		float expected = 5;
		assertEquals(expected, actual);
	}

	@Test
	public void divide90Point0By9Point0() {
		Calculator cut = new Calculator();
		float actual = cut.divide(90.0f, 9.0f);
		float expected = 10.0f;
		assertEquals(expected, actual);
	}

	@Test
	public void divide1800Point15By3Thousand() {
		Calculator cut = new Calculator();
		float actual = cut.divide(1800.15f, 3000);
		float expected = 0.60005003f;
		assertEquals(expected, actual);
	}

	@Test
	public void divideMinus20ThousandBy2Thousand() {
		Calculator cut = new Calculator();
		float actual = cut.divide(-20000, 2000);
		float expected = -10;
		assertEquals(expected, actual);
	}

	@Test
	public void divideMinus20ThousandByMinusThousan() {
		Calculator cut = new Calculator();
		float actual = cut.divide(-20000, -1000);
		float expected = 20;
		assertEquals(expected, actual);
	}

	@Test
	public void divideMinus1234ByMinus1235() {
		Calculator cut = new Calculator();
		float actual = cut.divide(-1234, -1235);
		float expected = 0.9991902834008097f;
		assertEquals(expected, actual);
	}

	@Test
	public void divide30By4ThenDivideResultBy5() {
		Calculator cut = new Calculator();
		float result = cut.divide(30, 4);
		float actual = cut.divide(result, 5);
		float expected = 1.5f;
		assertEquals(expected, actual);
	}

	@Test
	public void divideNegavtive10By4ThenDivideResultBy34() {
		Calculator cut = new Calculator();
		float result = cut.divide(-10, 4);
		float actual = cut.divide(result, 34);
		float expected = -0.0735294117647059f;
		assertEquals(expected, actual);
	}

}
