package edu.ncsu.csc316.dsa.queue;

/**
 * Queue class.
 * @author Chase Helton
 *
 * @param <E> the generic type of data
 */
public interface Queue<E> {

	/**
	 * Adds the element to the back of the queue.
	 * @param value the value to add to the queue
	 */
    void enqueue(E value);
    
    /**
     * Removes and returns the element at the front of the queue.
     * @return the element at the front of the queue
     */
    E dequeue();
    
    /**
     * Returns, but does not remove, the element at the front of the queue.
     * @return the element at the front of the queue
     */
    E front();
    
    /**
     * Returns the number of elements in the queue.
     * @return the number of elements in the queue
     */
    int size();
    
    /**
     * Returns true if the queue is empty; otherwise, returns false.
     * @return true if the queue is empty; otherwise, returns false
     */
    boolean isEmpty();
}