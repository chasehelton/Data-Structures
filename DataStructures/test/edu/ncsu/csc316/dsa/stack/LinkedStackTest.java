package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the LinkedStack class
 * @author Chase Helton
 */
public class LinkedStackTest {

	/** Stack to test. */
	private Stack<String> stack;
	
	/**
	 * Sets up tests.
	 */
	@Before
	public void setUp() {
		stack = new LinkedStack<String>();
	}
	
	/**
	 * Tests push method.
	 */
	@Test
	public void testPush() {
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
		
		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
	}

	/**
	 * Tests pop method.
	 */
	@Test
	public void testPop() {
		assertEquals(0, stack.size());
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		stack.push("six");
		assertEquals(6, stack.size());
		
		assertEquals("six",  stack.pop());
		assertEquals(5, stack.size());
		
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		
		try {
			stack.pop();
			fail("EmptyStackException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}

	/**
	 * Tests top method.
	 */
	@Test
	public void testTop() {	
		assertEquals(0, stack.size());
		stack.push("one");
		assertEquals("one", stack.top());
	}

}
