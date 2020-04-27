package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests QuickSorter.
 * 
 * @author Chase Helton
 *
 */
public class QuickSorterTest {

	//Integer tests
	
	private Integer[] dataAscending = { 1, 2, 3, 4, 5, 6};
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	private QuickSorter<Integer> integerSorter;
	private QuickSorter<Integer> integerSorter2;
	private QuickSorter<Integer> integerSorter3;
	private QuickSorter<Integer> integerSorter4;
	private QuickSorter<Integer> integerSorter5;
	private Comparator<Integer> c;
	

	/**
	 * Sets up the sorter to test.
	 */
	@Before
	public void setUp() {
		integerSorter = new QuickSorter<Integer>(c, QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		integerSorter2 = new QuickSorter<Integer>(c);
		integerSorter3 = new QuickSorter<Integer>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		integerSorter4 = new QuickSorter<Integer>();
		integerSorter5 = new QuickSorter<Integer>(QuickSorter.LAST_ELEMENT_SELECTOR);
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
		assertEquals(6, (int) dataAscending[5]);
		integerSorter2.sort(dataDescending);
		assertEquals(1, (int) dataDescending[0]);
		assertEquals(2, (int) dataDescending[1]);
		assertEquals(3, (int) dataDescending[2]);
		assertEquals(4, (int) dataDescending[3]);
		assertEquals(5, (int) dataDescending[4]);
		integerSorter3.sort(dataRandom);
		assertEquals(1, (int) dataRandom[0]);
		assertEquals(2, (int) dataRandom[1]);
		assertEquals(3, (int) dataRandom[2]);
		assertEquals(4, (int) dataRandom[3]);
		assertEquals(5, (int) dataRandom[4]);
		integerSorter4.sort(dataRandom);
		assertEquals(1, (int) dataRandom[0]);
		assertEquals(2, (int) dataRandom[1]);
		assertEquals(3, (int) dataRandom[2]);
		assertEquals(4, (int) dataRandom[3]);
		assertEquals(5, (int) dataRandom[4]);
		integerSorter5.sort(dataRandom);
		assertEquals(1, (int) dataRandom[0]);
		assertEquals(2, (int) dataRandom[1]);
		assertEquals(3, (int) dataRandom[2]);
		assertEquals(4, (int) dataRandom[3]);
		assertEquals(5, (int) dataRandom[4]);
	}
	
}
