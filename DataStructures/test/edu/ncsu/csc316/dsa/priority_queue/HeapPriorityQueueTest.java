package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests the HeapPriorityQueue class.
 * @author Chase Helton
 */
public class HeapPriorityQueueTest {

	/** Heap to test. */
	private PriorityQueue<Integer, String> heap;
	
	/**
	 * Sets up the tests.
	 */
	@Before
	public void setUp() {
		heap = new HeapPriorityQueue<Integer, String>();
	}
	
	/**
	 * Tests the insert() method.
	 */
	@Test
	public void testInsert() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);
		
		heap.insert(8, "eight");
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(8, (int)heap.min().getKey());
		
		heap.insert(1, "one");
		heap.insert(3, "three");
		heap.insert(2, "two");
		heap.insert(4, "four");
		
		assertEquals(5, heap.size());
		
	}
	
	/**
	 * Tests the min() method.
	 */
	@Test
	public void testMin() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);
		
		assertNull(heap.min());
		
		heap.insert(1, "one");
		heap.insert(3, "three");
		heap.insert(2, "two");
		heap.insert(4, "four");
		
		assertEquals(4, heap.size());
	}
	
	/**
	 * Tests the deleteMin() method.
	 */
	@Test 
	public void deleteMin() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		assertNull(heap.deleteMin());
		
		heap.insert(2, "two");
		heap.insert(1, "one");
		heap.deleteMin();
		
		assertEquals(1, heap.size());
		
		heap.insert(1, "one");
		heap.insert(3, "three");
		heap.insert(2, "two");
		heap.insert(4, "four");
		heap.insert(5, "five");
		heap.insert(7, "seven");
		heap.insert(8, "eight");
		heap.insert(6, "six");
		
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		
		assertEquals(3, heap.size());
		
	}
	
	/**
	 * Tests a student heap.
	 */
	@Test
	public void testStudentHeap() {
		PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
		
		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());
		
		sHeap.insert(s1, "studentOne");
		sHeap.insert(s2, "studentTwo");
		sHeap.insert(s3, "studentThree");
		sHeap.insert(s4, "studentFour");
		sHeap.insert(s5, "studentFive");
		
		sHeap.deleteMin();
		
		assertEquals(4, sHeap.size());
	}
}