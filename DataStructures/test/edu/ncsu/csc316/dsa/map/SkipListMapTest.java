package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the SkipListMap class.
 * @author Chase Helton
 */
public class SkipListMapTest {

	/** Map to test. */
	private Map<Integer, String> map;
	/** Student Map to test. */
	private Map<Student, Integer> studentMap;
	
	/**
	 * Sets up the tests.
	 */
	@Before
	public void setUp() {
		map = new SkipListMap<Integer, String>();
		studentMap = new SkipListMap<Student, Integer>();
	}
	
	/**
	 * Tests the put() method.
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SkipListMap[3]", map.toString());
		assertEquals(1, map.size());
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
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		assertEquals("string1", map.get(1));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		assertEquals("string1", map.put(1, "string1"));
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
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		map.remove(1);
		assertEquals("SkipListMap[2, 3, 4, 5]", map.toString());
	}
	
	/**
	 * Tests the student map functionality.
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		
		studentMap.put(s1,  100);
		assertEquals(1, studentMap.size());
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
	}
	
	/**
	 * Tests the entrySet() method.
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
		assertTrue(it.hasNext());
	}
}