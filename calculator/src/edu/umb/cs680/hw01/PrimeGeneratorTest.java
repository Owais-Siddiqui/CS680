package edu.umb.cs680.hw01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PrimeGeneratorTest {

	@Test
	public void Primesfrom1to10() {
	PrimeGenerator gen = new PrimeGenerator(1, 10);
	gen.generatePrimes();
	Long[] expectedPrimes = {2L, 3L, 5L, 7L};
	assertArrayEquals( expectedPrimes,
	gen.getPrimes().toArray() );
	}
	@Test
	public void Primesfrom10to50() {
	PrimeGenerator gen = new PrimeGenerator(10, 50);
	gen.generatePrimes();
	Long[] expectedPrimes = {11L, 13L, 17L, 19L, 23l, 29l, 31l, 37L, 41l, 43l, 47l};
	assertArrayEquals( expectedPrimes,
	gen.getPrimes().toArray() );
	}
	@Test
	public void Primesfrom50to10() {
		try 
		{
			PrimeGenerator gen = new PrimeGenerator(50, 10);
			gen.generatePrimes();
		}
		catch(RuntimeException ex){
			assertEquals("Wrong input values: from=50 to=10", ex.getMessage());
		}
	}
}
