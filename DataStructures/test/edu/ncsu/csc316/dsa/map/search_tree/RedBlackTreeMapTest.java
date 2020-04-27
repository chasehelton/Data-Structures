package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the RedBlackTreeMap class.
 * @author Chase Helton
 *
 */
public class RedBlackTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Sets up the tests.
     */
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Tests the put method.
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(4,  "four"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(7,  "seven"));
        assertEquals(2, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(12,  "twelve"));
        assertEquals(3, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
    }
    
    /**
     * Tests the get method.
     */
    @Test
    public void testGet() {
    	assertTrue(tree.isEmpty());
        assertNull(tree.put(3, "string3"));
        assertFalse(tree.isEmpty());
        
        assertEquals("string3", tree.get(3));
        assertEquals(null, tree.get(6));
        assertEquals(null, tree.get(0));
    }
    
    /**
     * Tests the remove method.
     */
    @Test
    public void testRemove() {
    	assertTrue(tree.isEmpty());
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(6, "six"));
        assertNull(tree.put(7, "seven"));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        
        assertNull(tree.remove(0));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals("seven", tree.remove(7));
        assertEquals("six", tree.remove(6));
        assertEquals("five", tree.remove(5));
        assertEquals("four", tree.remove(4));
        assertEquals("three", tree.remove(3));
        assertEquals("two", tree.remove(2));
        assertEquals("one", tree.remove(1));
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(6, "six"));
        assertNull(tree.put(7, "seven"));
        assertNull(tree.put(8, "eight"));
        assertNull(tree.put(9, "nine"));
        assertNull(tree.put(10, "ten"));
        assertNull(tree.put(11, "eleven"));
        assertNull(tree.put(12, "twelve"));
        assertNull(tree.put(13, "thirteen"));
        assertNull(tree.put(14, "fourteen"));
        
        assertEquals("seven", tree.remove(7));
        assertEquals("two", tree.remove(2));
        assertEquals("three", tree.remove(3));
        assertEquals("five", tree.remove(5));
        assertEquals("eleven", tree.remove(11));
        }
}
