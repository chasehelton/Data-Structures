package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Student class.
 * @author Chase Helton
 *
 */
public class StudentTest {

	private Student sOne;
	private Student sOneN1;
	private Student sOneN2;
	private Student sTwo;
	private Student sOneF1;
	private Student sOneF2;
	private Student sOneU1;
	private Student sOneU2;
	
	/**
	 * Sets up the tests.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sOneF1 = new Student("OneFirst1", "OneLast", 1, 1, 1.0, "oneUnityID");
		sOneF2 = new Student("1OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sOneU1 = new Student("OneFirst", "OneLast", 2, 1, 1.0, "oneUnityID");
		sOneU2 = new Student("OneFirst", "OneLast", 0, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sOneN1 = new Student("first", null, 0, 1, 1.0, "ID");
		sOneN2 = new Student(null, "last", 0, 1, 1.0, "ID");
	}

	/**
	 * Tests the first setter.
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests the last setter.
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests the ID setter.
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests the gpa setter.
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Tests the unity ID setter.
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests the compareTo method.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOneF1) < 0);
		assertTrue(sOne.compareTo(sOneF2) > 0);
		assertTrue(sOne.compareTo(sOneU1) > 0);
		assertTrue(sOne.compareTo(sOneU2) < 0);
	}
	
	/**
	 * Tests the equals method.
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.equals(sOne));
		assertFalse(sOne.equals(sTwo));
		assertFalse(sOne.equals(sOneF1));
		assertFalse(sOne.equals(sOneU1));
		assertFalse(sOne.equals(sOneN1));
		assertFalse(sOne.equals(sOneN2));
	}
	
	/**
	 * Tests the toString method.
	 */
	@Test
	public void testToString() {
		String test = "Last: OneLast, First: OneFirst, Student ID: 1";
		assertEquals(test, sOne.toString());
	}
	
	/**
	 * Tests the hashCode method.
	 */
	@Test
	public void testHashCode() {
		assertEquals(sOne.hashCode(), sOne.hashCode());
	}
}
