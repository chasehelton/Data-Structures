package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the LinearProbingHashMap class.
 * @author Chase Helton
 *
 */
public class LinearProbingHashMapTest {

	/** Map to test. */
    private Map<Integer, String> map;
    private Map<Integer, String> map2;
    private Map<Integer, String> map3;
    private Map<Integer, String> map4;
    
    /**
     * Sets up the tests.
     */
    @Before
    public void setUp() {
    	map = new LinearProbingHashMap<Integer, String>(7, true);
    	map2 = new LinearProbingHashMap<Integer, String>();
    	map3 = new LinearProbingHashMap<Integer, String>(true);
    	map4 = new LinearProbingHashMap<Integer, String>(7);
    }
    
    /**
     * Tests the put method.
     */
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be index 4
                
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be index 4
        assertEquals(4, (int)it.next().getKey()); // should be index 5
        
        map2.entrySet();
        map3.entrySet();
        map4.entrySet();
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
        assertNull(map.put(6, "string6"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        assertEquals(6, (int)it.next().getKey()); // should be index 0
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