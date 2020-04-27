package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests UnorderedArrayMap.
 * @author Chase Helton
 *
 */
public class UnorderedArrayMapTest {

	/** Map to test. */
	private Map<Integer, String> map;
	
	/**
	 * Sets up the tests.
	 */
	@Before
	public void setUp() {
		map = new UnorderedArrayMap<Integer, String>();
	}
	
	/**
	 * Tests the put() method.
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedArrayMap[3]", map.toString());
		assertEquals(1, map.size());
		map.put(3, "string2");
		assertEquals("UnorderedArrayMap[3]", map.toString());
		map.put(3, "string3");
		map.put(5, "string5");
		map.put(2, "string2");
		map.put(4, "string4");
		map.put(1, "string1");
		assertEquals("string5", map.put(5, "secondString5"));
	}
	
	/**
	 * Tests the get() method.
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
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
		assertEquals("string1", map.get(1));
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
		map.get(2);
		assertEquals("UnorderedArrayMap[1, 2, 4, 5, 3]", map.toString());
		map.put(3, "secondString3");
		assertEquals("UnorderedArrayMap[1, 2, 4, 3, 5]", map.toString());

	}
	
	/**
	 * Tests the remove() method.
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
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
	}
	
	/**
	 * Tests the iterator() method.
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
		int i = it.next();
		assertEquals(1, i);
	}
	
	/**
	 * Tests the entry set method.
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
	 * Tests the values() method.
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		Iterator<String> it = map.values().iterator();
		String string = it.next();
		assertEquals("string1", string);
	}
}