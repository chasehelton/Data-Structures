package edu.ncsu.csc316.dsa.priority_queue;

/**
 * Priority Queue Interface.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public interface PriorityQueue<K, V> {

	/**
	 * Entry Interface for a Priority Queue.
	 * @author Chase Helton
	 *
	 * @param <K> the key
	 * @param <V> the value
	 */
	interface Entry<K, V> {
		
		/**
		 * Gets the key.
		 * @return the key
		 */
		K getKey();
		
		/**
		 * Gets the value.
		 * @return the value
		 */
		V getValue();
	}
	
	/**
	 * Inserts into the queue.
	 * @param key the key to insert
	 * @param value the value to insert
	 * @return the entry inserted
	 */
	Entry<K, V> insert(K key, V value);
	
	/**
	 * Returns the minimum value.
	 * @return the minimum value
	 */
	Entry<K, V> min();
	
	/**
	 * Returns the entry of the deleted minimum value.
	 * @return the entry of the deleted minimum value
	 */
	Entry<K, V> deleteMin();
	
	/**
	 * Returns the size.
	 * @return the size
	 */
	int size();
	
	/**
	 * Returns true if the queue is empty.
	 * @return true if the queue is empty
	 */
	boolean isEmpty();
}