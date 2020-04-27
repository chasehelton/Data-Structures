package edu.ncsu.csc316.dsa.queue;


import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests ArrayBasedQueue.
 * @author Chase Helton
 */
public class ArrayBasedQueueTest {

	/** The queue to test. */
    private Queue<String> queue;
    
    /**
     * Sets up the tests.
     */
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }
    
    /**
     * Tests the enqueue method.
     */
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("first");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("fourth");
        queue.enqueue("fifth");
        queue.enqueue("sixth");
        queue.enqueue("seventh");
        queue.enqueue("eigth");
        queue.enqueue("ninth");
        queue.enqueue("tenth");
        queue.enqueue("eleventh");
        assertEquals(11, queue.size());
    }
    
    /**
     * Tests the dequeue method.
     */
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        assertEquals(6, queue.size());
        
        assertEquals("one",  queue.dequeue());
        assertEquals(5, queue.size());
        
        assertEquals("two", queue.dequeue());
        assertEquals("three", queue.dequeue());
        assertEquals("four", queue.dequeue());
        assertEquals("five", queue.dequeue());
        assertEquals("six", queue.dequeue());
        
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");       
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
    
    /**
     * Tests the front method.
     */
    @Test
    public void testFront() {
    	queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        assertEquals("one", queue.front());
    }

}