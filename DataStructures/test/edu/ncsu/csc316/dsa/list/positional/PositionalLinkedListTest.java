package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test Class for PositionalLinkedList
 * @author Bryce Junkins
 *
 */
public class PositionalLinkedListTest {

	private PositionalList<String> list;
	
	/**
	 * Set up for tests
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();
	}
	
	/**
	 * Tests first method
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		try{
			list.first();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		Position<String> first = list.addFirst("a");
		assertEquals(1, list.size());
		assertEquals(first, list.first());
		
		Position<String> first2 = list.addFirst("b");
		assertEquals(2, list.size());
		assertEquals(first2, list.first());
	}
	
	/**
	 * Tests last method
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		try{
			list.last();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		Position<String> last = list.addLast("a");
		assertEquals(1, list.size());
		assertEquals(last, list.first());
		
		Position<String> last2 = list.addLast("b");
		assertEquals(2, list.size());
		assertEquals(last2, list.last());
	}
	
	/**
	 * Tests addFirst method
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());
		assertFalse(list.isEmpty());
		
		Position<String> second = list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals(second, list.first());
		assertFalse(list.isEmpty());
	}
	
	/**
	 * Tests addLast method
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(first, list.last());
		assertEquals(1, list.size());
		
		Position<String> second = list.addLast("two");
		assertEquals(second, list.last());
		assertEquals(2, list.size());
	}
	
	/**
	 * Tests before method
	 */
	@Test
	public void testBefore() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		
		try{
			list.before(first);
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		Position<String> second = list.addLast("two");
		assertEquals(first, list.before(second));
	}
	
	/**
	 * Tests after method
	 */
	@Test
	public void testAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		
		try{
			list.after(first);
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		Position<String> second = list.addLast("two");
		assertEquals(second, list.after(first));
	}
	
	/**
	 * Tests addBefore method
	 */
	@Test
	public void testAddBefore() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addBefore(first, "two");
		
		assertEquals(second, list.before(first));
		assertEquals(first, list.after(second));
	}
	
	/**
	 * Tests addAfter method
	 */
	@Test
	public void testAddAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addAfter(first, "two");
		
		assertEquals(first, list.before(second));
		assertEquals(second, list.after(first));
	}
	
	/**
	 * Tests set method
	 */
	@Test
	public void testSet() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		String temp = list.set(first, "two");
		assertEquals(temp, "one");
	}
	
	/**
	 * Tests remove method
	 */
	@Test
	public void testRemove() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		String temp = list.remove(first);
		assertEquals(temp, "one");
		assertTrue(list.isEmpty());
		
		first = list.addFirst("two");
		Position<String> second = list.addLast("three");
		temp = list.remove(second);
		assertEquals(temp, "three");
		assertEquals(first, list.first());
		
	}
	
	/**
	 * Tests iterator method
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
		Position<String> first = list.addLast("one");
		
		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals(first, list.last());
		
		// Create an iterator for the list that has 1 element
		it = list.iterator();
		
		//Make sure you still can't remove
		try{
			it.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals(first.getElement(), it.next());
		assertFalse(it.hasNext());
		try{
			it.next();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	
		//More thorough tests
		list.addLast("two"); list.addLast("three"); list.addLast("four");
		
		//test remove
		it = list.iterator();
		assertEquals("one", it.next());
		assertEquals("two", it.next());
		it.remove();
		assertEquals("three", it.next());
		assertEquals("four", it.next());
		it.remove();
		
		it = list.iterator();
		assertEquals("one", it.next());
		assertEquals("three", it.next());
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests positions method
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		
		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());
		
		it = list.positions().iterator();
		
		try{
			it.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		it.next();
		it.remove();
		assertEquals(2, list.size());
		assertEquals(second, list.first());
	}

}