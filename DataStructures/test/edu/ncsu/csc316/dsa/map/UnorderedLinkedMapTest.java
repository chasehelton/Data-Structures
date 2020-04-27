package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the UnorderedLinkedMap class.
 * @author Chase Helton
 */
public class UnorderedLinkedMapTest {

	/** Map to test. */
	private Map<Integer, String> map;
	
	/**
	 * Sets up the test.
	 */
	@Before
	public void setUp() {
		map = new UnorderedLinkedMap<Integer, String>();
	}
	
	/**
	 * Tests put() method.
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedLinkedMap[3]", map.toString());
		assertEquals(1, map.size());
		assertNull(map.put(5, "string5"));
		assertEquals("UnorderedLinkedMap[5, 3]", map.toString());
		assertEquals(2, map.size());
		map.put(5, "newString5");
		assertEquals("UnorderedLinkedMap[5, 3]", map.toString());
	}
	
	/**
	 * Tests get() method.
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		assertEquals(null, map.get(0));
	}
	
	/**
	 * Tests remove() method.
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string1", map.remove(1));
		assertEquals(4, map.size());
		assertEquals("UnorderedLinkedMap[4, 2, 5, 3]", map.toString());
	}
	
	/**
	 * Tests iterator() method.
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Integer> it = map.iterator();
		assertTrue(it.hasNext());
	}
	
	/**
	 * Tests entrySet() method.
	 */
	@Test
	public void testEntrySet() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(1, (int)(entry.getKey()));
		assertEquals("string1", (String)(entry.getValue()));
	}
	
	/**
	 * Tests values() method.
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<String> it = map.values().iterator();
		assertTrue(it.hasNext());
	}
}