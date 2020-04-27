package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the GeneralTree class.
 * @author Chase Helton
 */
public class GeneralTreeTest {

	/** Creates a tree. */
    private GeneralTree<String> tree;
    /** Creates a tree. */
    private GeneralTree<String> emptyTree;
    /** Creates a tree. */
    private GeneralTree<String> tree2;
    
    /** Creates a position. */
    private Position<String> one;
    /** Creates a position. */
    private Position<String> two;
    /** Creates a position. */
    private Position<String> three;
    /** Creates a position. */
    private Position<String> four;
    /** Creates a position. */
    private Position<String> five;
    /** Creates a position. */
    private Position<String> six;
    /** Creates a position. */
    private Position<String> seven;
    /** Creates a position. */
    private Position<String> eight;
    /** Creates a position. */
    private Position<String> nine;
    /** Creates a position. */
    private Position<String> ten;
    
    /**
     * InvalidPosition class to test validate.
     * @author Chase Helton
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
        tree = new GeneralTree<String>();
        emptyTree = new GeneralTree<String>();
        tree2 = new GeneralTree<String>();
    }
    
    /**
     * Helper method to construct a sample tree
     *
     * One
     * -> Two
     *   -> Six
     *   -> Five
     *   -> Ten
     *     -> Seven
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     *
     * Or, visually:
     *                 one
     *            /            \
     *         two                three
     *      /   |     \             |   
     *   six   five   ten          four
     *                  |         /    \
     *                seven     eight  nine              
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addChild(one, "two");
        three = tree.addChild(one,  "three");
        six = tree.addChild(two, "six");
        five = tree.addChild(two, "five");
        ten = tree.addChild(two,  "ten");
        seven = tree.addChild(ten,  "seven");
        four = tree.addChild(three,  "four");
        eight = tree.addChild(four,  "eight");
        nine = tree.addChild(four,  "nine");
    }
    
    /**
     * Creats a tree with two Nodes.
     */
    private void createTree2() {
        one = tree2.addRoot("one");
        two = tree2.addChild(one, "two");
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
        assertEquals(3, tree.numChildren(two));
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
     * Tests the isEmpty() method.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(emptyTree.isEmpty());
        createTree();
        assertFalse(tree.isEmpty());
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
        assertEquals(five, pre.next());
        assertEquals(ten, pre.next());
        assertEquals(seven, pre.next());
        assertEquals(three, pre.next());
        assertEquals(four, pre.next());
        assertEquals(eight, pre.next());
        assertEquals(nine, pre.next());
    }
    
    /**
     * Tests the iterator() method.
     */
    @Test
    public void testIterator() {
        createTree();
        Iterator<String> pre = tree.iterator();
        assertEquals("one", pre.next());
    }
    
    /**
     * Tests the postOrder() method.
     */
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> post = tree.postOrder().iterator();
        assertEquals(six, post.next());
        assertEquals(five, post.next());
        assertEquals(seven, post.next());
        assertEquals(ten, post.next());
        assertEquals(two, post.next());
        assertEquals(eight, post.next());
        assertEquals(nine, post.next());
        assertEquals(four, post.next());
        assertEquals(three, post.next());
        assertEquals(one, post.next());
    }
    
    /**
     * Tests the levelOrder() method.
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> level = tree.levelOrder().iterator();
        assertEquals(one, level.next());
        assertEquals(two, level.next());
        assertEquals(three, level.next());
        assertEquals(six, level.next());
        assertEquals(five, level.next());
        assertEquals(ten, level.next());
        assertEquals(four, level.next());
        assertEquals(seven, level.next());
        assertEquals(eight, level.next());
        assertEquals(nine, level.next());
    }
    
    /**
     * Tests the addChild() method.
     */
    @Test
    public void testAddChild() {
        assertTrue(tree.isEmpty());
        one = tree.addRoot("one");
        assertEquals(1, tree.size());
        assertNull(tree.parent(one));
        assertEquals("GeneralTree[\none\n]", tree.toString());
    }
    
    /**
     * Tests the remove() method.
     */
    @Test
    public void testRemove() {
        createTree();
        assertEquals(10, tree.size());
        assertEquals(2, tree.numChildren(four));
        tree.remove(nine);
        assertEquals("GeneralTree[\none\n two\n  six\n  five\n  ten\n   seven\n three\n  four\n   eight\n]", tree.toString());
        assertEquals(9, tree.size());
        assertEquals(1, tree.numChildren(four));
        
        try {
        	tree.remove(two);
        	fail();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
        createTree2();
        tree2.remove(one);
        assertEquals(1, tree2.size());
        tree2.remove(two);
        assertEquals(0, tree2.size());
    }
    
    /**
     * Tests the emptyTree() method.
     */
    @Test
    public void testEmptyTree() {
        Tree<String> bTree = new GeneralTree<String>();
        assertTrue(bTree.isEmpty());
    }
    
}
