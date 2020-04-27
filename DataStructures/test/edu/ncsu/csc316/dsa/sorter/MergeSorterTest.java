package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests the MergeSort class.
 * @author Chase Helton
 */
public class MergeSorterTest {

	//Integer tests
	
	private Integer[] dataAscending = { 1, 2, 3, 4, 5, 6};
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	private MergeSorter<Integer> integerSorter;
	
	//Student tests
	
		private Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		private Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		private Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		private Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		private Student sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		private Student[] sAsc = {sOne, sTwo, sThree, sFour, sFive };
		private Student[] sDesc = {sFive, sFour, sThree, sTwo, sOne };
		private Student[] sRand = {sTwo, sFour, sOne, sFive, sThree};
		
		private MergeSorter<Student> studentGPASorter;
		private MergeSorter<Student> studentIDSorter;
		private MergeSorter<Student> studentSorter;
	

	/**
	 * Sets up the sorter to test.
	 */
	@Before
	public void setUp() {
		integerSorter = new MergeSorter<Integer>(null);
		studentGPASorter = new MergeSorter<Student>(new StudentGPAComparator());
		studentIDSorter = new MergeSorter<Student>(new StudentIDComparator());
		studentSorter = new MergeSorter<Student>(null);
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
	
	/**
	 * Tests the sorting function with Students.
	 */
	@Test
	public void testSortStudent() {
		studentGPASorter.sort(sAsc);
		assertEquals("FiveLast", sAsc[0].getLast());
		assertEquals("FourLast", sAsc[1].getLast());
		assertEquals("ThreeLast", sAsc[2].getLast());
		assertEquals("TwoLast", sAsc[3].getLast());
		assertEquals("OneLast", sAsc[4].getLast());
		studentIDSorter.sort(sDesc);
		assertEquals("OneLast", sDesc[0].getLast());
		assertEquals("TwoLast", sDesc[1].getLast());
		assertEquals("ThreeLast", sDesc[2].getLast());
		assertEquals("FourLast", sDesc[3].getLast());
		assertEquals("FiveLast", sDesc[4].getLast());
		studentSorter.sort(sRand);
		assertEquals("FiveLast", sRand[0].getLast());
		assertEquals("FourLast", sRand[1].getLast());
		assertEquals("OneLast", sRand[2].getLast());
		assertEquals("ThreeLast", sRand[3].getLast());
		assertEquals("TwoLast", sRand[4].getLast());
	}
	
}
