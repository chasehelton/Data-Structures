package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the SinglyLinkedList class
 * @author Chase Helton
 */
public class SinglyLinkedListTest {

	private List<String> list;
	private List<String> list2;
	private List<String> itList;

	/**
	 * Sets up tests
	 */
	@Before
	public void setUp() {
		list = new SinglyLinkedList<String>();
		list2 = new SinglyLinkedList<String>();
		itList = new SinglyLinkedList<String>();
	}

	/**
	 * Sets up add tests
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		
		try{
			list.add(15,  "fifteen");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
	}

	/**
	 * Sets up add tests
	 */
	@Test
	public void testAddLast() {
		list2.add(0, "zero");
		list2.add(1, "one");
		list2.addLast("last");
		assertEquals(3, list2.size());
	}

	/**
	 * Sets up add tests
	 */
	@Test
	public void testLast() {
		list2.add(0, "zero");
		list2.add(1, "one");
		list2.addLast("last");
		assertEquals("last", list2.last());
	}

	/**
	 * Sets up add tests
	 */
	@Test
	public void testAddFirst() {
		list2.add(0, "one");
		list2.addFirst("first");
		assertEquals("first", list2.get(0));
	}

	/**
	 * Sets up first tests
	 */
	@Test
	public void testFirst() {
		list2.add(0, "one");
		list2.addFirst("first");
		assertEquals("first", list2.first());
	}

	/**
	 * Sets up iterator tests
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();
		
		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try{
			it.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");
		
		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));
		
		// Create an iterator for the list that has 1 element
		it = list.iterator();
		
		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		try{
			it.next();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		itList.add(0, "9");
		itList.add(0, "8");
		itList.add(0, "7");
		itList.add(0, "6");
		itList.add(0, "4");
		itList.add(0, "3");
		itList.add(0, "2");
		itList.add(0, "1");
		itList.add(0, "0");
		itList.add(itList.size() - 1, "10");
		itList.add(5, "5");
		assertEquals(11, itList.size());
		Iterator<String> iter = itList.iterator();
		iter.next();
		iter.next();
		iter.remove();
		assertEquals(10, itList.size());
		
	}

	/**
	 * Sets up remove tests
	 */
	@Test
	public void testRemoveIndex() {
		list2.add(0, "zero");
		list2.add(1, "one");
		list2.add(2, "two");
		list2.remove(1);
		assertEquals(2, list2.size());
	}

	/**
	 * Sets up remove tests
	 */
	@Test
	public void testRemoveFirst() {
		list2.add(0, "zero");
		list2.add(1, "one");
		list2.add(2, "two");
		assertEquals("zero", list2.removeFirst());
		assertEquals(2, list2.size());
		assertEquals("one", list2.get(0));
	}

	/**
	 * Sets up remove tests
	 */
	@Test
	public void testRemoveLast() {
		list2.add(0, "zero");
		list2.add(1, "one");
		list2.add(2, "two");
		list2.removeLast();
		assertEquals(2, list2.size());
		assertEquals("one", list2.get(1));
	}

	/**
	 * Sets up set test
	 */
	@Test
	public void testSet() {
		list2.add(0, "zero");
		list2.add(1, "one");
		list2.addLast("last");
		list2.set(0, "notZero");
		assertEquals("notZero", list2.get(0));
	}
	
}
