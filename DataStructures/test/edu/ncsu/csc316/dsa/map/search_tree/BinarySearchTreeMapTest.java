package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Tests the BinarySearchTreeMap class.
 * @author Chase Helton
 *
 */
public class BinarySearchTreeMapTest {

	BinarySearchTreeMap<Integer, String> tree;
	
	/**
	 * Set up the tests.
	 */
	@Before
	public void setUp() {
		tree = new BinarySearchTreeMap<Integer, String>();
	}
	
	/**
	 * Tests the put method.
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(1, "one");
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(1, (int)tree.root().getElement().getKey());
	}
	
	/**
	 * Tests the get method.
	 */
	@Test
	public void testGet() {
		tree.put(1,  "one");
		assertEquals(1, tree.size());
	}
	
	/**
	 * Tests the remove method.
	 */
	@Test
	public void testRemove() {
		tree.put(1,  "one");
		assertEquals(1, tree.size());
		
		assertNull(tree.remove(10));
		assertEquals(1, tree.size());
		
		assertEquals("one", tree.remove(1));
		assertEquals(0, tree.size());
	}
	
	/**
	 * Tests the unused methods.
	 */
	@Test
	public void testTheRest() {
		Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
		assertFalse(it.hasNext());
		tree.toString();
		Position<Entry<Integer, String>> p = tree.root();
		tree.children(p);
		tree.numChildren(p);
		tree.preOrder();
		tree.postOrder();
		tree.inOrder();
		tree.levelOrder();
	}
}