package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the InsertionSorter class
 * 
 * @author Chase Helton
 */
public class BubbleSorterTest {

	//Integer tests
	
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	private BubbleSorter<Integer> integerSorter;
	

	/**
	 * Sets up the sorter to test.
	 */
	@Before
	public void setUp() {
		integerSorter = new BubbleSorter<Integer>(null);
	}

	/**
	 * Tests the sorting function with integers.
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(1, (int) dataAscending[0]);
		assertEquals(2, (int) dataAscending[1]);
		assertEquals(3, (int) dataAscending[2]);
		assertEquals(4, (int) dataAscending[3]);
		assertEquals(5, (int) dataAscending[4]);
		integerSorter.sort(dataDescending);
		assertEquals(1, (int) dataDescending[0]);
		assertEquals(2, (int) dataDescending[1]);
		assertEquals(3, (int) dataDescending[2]);
		assertEquals(4, (int) dataDescending[3]);
		assertEquals(5, (int) dataDescending[4]);
		integerSorter.sort(dataRandom);
		assertEquals(1, (int) dataRandom[0]);
		assertEquals(2, (int) dataRandom[1]);
		assertEquals(3, (int) dataRandom[2]);
		assertEquals(4, (int) dataRandom[3]);
		assertEquals(5, (int) dataRandom[4]);
	}
	
}
