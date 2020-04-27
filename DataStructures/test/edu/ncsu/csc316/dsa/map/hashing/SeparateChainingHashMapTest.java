package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the SeparateChainingHashMap class.
 * @author Chase Helton
 *
 */
public class SeparateChainingHashMapTest {

	/** The map to test with. */
	private Map<Integer, String> map;
	/** The map to test with. */
	private Map<Integer, String> map2;
	/** The map to test with. */
	private Map<Integer, String> map3;
	/** The map to test with. */
	private Map<Integer, String> map4;
	
	/**
	 * Sets up the tests.
	 */
	@Before
	public void setUp() {
		map = new SeparateChainingHashMap<Integer, String>(7, true);
		map2 = new SeparateChainingHashMap<Integer, String>(1);
		map3 = new SeparateChainingHashMap<Integer, String>(true);
		map4 = new SeparateChainingHashMap<Integer, String>();
	}
	
	/**
	 * Tests the put method.
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey()); // should be in a map in index 4
		
		
		assertNull(map.put(4, "string4"));
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		it = map.entrySet().iterator();
		assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
		assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
		
		map2.entrySet();
		map3.entrySet();
		map4.entrySet();
		
		map2.put(1, "1");
		map2.put(2, "2");
		map2.put(3, "3");
		map2.put(4, "4");
		
	}
	
	/**
	 * Tests the get method.
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
		assertEquals("string2", map.get(2));
		assertNull(map.get(0));
	}
	
	/**
	 * Tests the remove method.
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
		assertEquals("string3", map.remove(3));
	}
	
	/**
	 * Tests the iterator method.
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
	 * Tests the entrySet method.
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
		assertEquals(entry.getValue(), map.get(1));
	}
	
	/**
	 * Tests the values method.
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