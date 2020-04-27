package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the LinkedBinaryTree class.
 * @author Chase Helton
 *
 */
public class LinkedBinaryTreeTest {

	/** Creates a tree. */
    private LinkedBinaryTree<String> tree;
    /** Creates a node. */
    private Position<String> one;
    /** Creates a node. */
    private Position<String> two;
    /** Creates a node. */
    private Position<String> three;
    /** Creates a node. */
    private Position<String> four;
    /** Creates a node. */
    private Position<String> five;
    /** Creates a node. */
    private Position<String> six;
    /** Creates a node. */
    private Position<String> seven;
    /** Creates a node. */
    private Position<String> eight;
    /** Creates a node. */
    private Position<String> nine;
    /** Creates a node. */
    private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p).
     * @param <E> the generic type
     */
    //private class InvalidPosition<E> implements Position<E> {

    	/**
    	 * Returns null.
    	 * @return null
    	 */
        //@Override
        //public E getElement() {
        //    return null;
        //}
        
    //}
    
    /**
     * Sets up the tests.
     */
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>();
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Tests the set() method.
     */
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.set(one, "ONE"));
        
        /*try {
            tree.set(new InvalidPosition<String>(), "invalid");
            fail();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }*/
    }
    
    /**
     * Tests the size() method.
     */
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
        assertFalse(tree.isEmpty());
        tree.remove(seven);
        assertEquals(9, tree.size());
    }
    
    /**
     * Tests the numChildren() method.
     */
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(two));
    }

    /**
     * Tests the parent() method.
     */
    @Test
    public void testParent() {
        createTree();
        assertEquals("one", tree.parent(two).getElement());
    }
    
    /**
     * Tests the iterator() method.
     */
    @Test
    public void testIterator() {
    	createTree();
        Iterator<String> pre = tree.iterator();
        assertEquals("six", pre.next());
    }
    
    /**
     * Tests the sibling() method.
     */
    @Test
    public void testSibling() {
        createTree();
        assertEquals(two, tree.sibling(three));
    }
    
    /**
     * Tests the isInternal() method.
     */
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(ten));
    }
    
    /**
     * Tests the isLeaf() method.
     */
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(seven));
    }
    
    /**
     * Tests the isRoot() method.
     */
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
    }
    
    /**
     * Tests the preOrder() method.
     */
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> pre = tree.preOrder().iterator();
        assertEquals(one, pre.next());
        assertEquals(two, pre.next());
        assertEquals(six, pre.next());
        assertEquals(ten, pre.next());
        assertEquals(seven, pre.next());
        assertEquals(five, pre.next());
        assertEquals(three, pre.next());
        assertEquals(four, pre.next());
        assertEquals(eight, pre.next());
        assertEquals(nine, pre.next());
    }
    
    /**
     * Tests the postOrder() method.
     */
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> post = tree.postOrder().iterator();
        assertEquals(six, post.next());
        assertEquals(seven, post.next());
        assertEquals(five, post.next());
        assertEquals(ten, post.next());
        assertEquals(two, post.next());
        assertEquals(eight, post.next());
        assertEquals(nine, post.next());
        assertEquals(four, post.next());
        assertEquals(three, post.next());
        assertEquals(one, post.next());
    }
    
    /**
     * Tests the inOrder() method.
     */
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> in = tree.inOrder().iterator();
        assertTrue(in.hasNext());
        assertEquals(six, in.next());
        assertEquals(two, in.next());
        assertEquals(seven, in.next());
        assertEquals(ten, in.next());
        assertEquals(five, in.next());
        assertEquals(one, in.next());
        assertEquals(eight, in.next());
        assertEquals(four, in.next());
        assertEquals(nine, in.next());
        assertEquals(three, in.next());
    }
    
    /**
     * Tests the levelOrder() method.
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> l = tree.levelOrder().iterator();
        assertEquals(one, l.next());
        assertEquals(two, l.next());
        assertEquals(three, l.next());
        assertEquals(six, l.next());
        assertEquals(ten, l.next());
        assertEquals(four, l.next());
        assertEquals(seven, l.next());
        assertEquals(five, l.next());
        assertEquals(eight, l.next());
        assertEquals(nine, l.next());
    }
    
    /**
     * Tests the emptyTree() method.
     */
    @Test
    public void testEmptyTree() {
        createTree();
        assertFalse(tree.isEmpty());
    }
    
    /**
     * Tests the addChildren() methods.
     */
    @Test
    public void testAddChildren() {
    	assertTrue(tree.isEmpty());
        one = tree.addRoot("one");
        assertEquals(1, tree.size());
        assertNull(tree.parent(one));
        tree.addLeft(one, "left");
        tree.addRight(one, "right");
        assertEquals(3, tree.size());
    }
    
    /**
     * Tests the remove() method.
     */
    @Test
    public void testRemove() {
        createTree();
        tree.remove(five);
        assertEquals(9, tree.size());
    }
}