package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

//import edu.ncsu.csc316.dsa.map.search_tree.*;

/**
 * Tests SplayMapTree class.
 * @author Chase Helton
 *
 */
public class SplayTreeMapTest {
	
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Sets up the tests.
     */
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the put method.
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(3, "string3"));
        assertEquals(1, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
    }
    
    /**
     * Test the get method.
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
     * Test the remove method.
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
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals("one", tree.remove(1));
    }
}