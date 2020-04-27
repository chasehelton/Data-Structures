package edu.ncsu.csc316.dsa.priority_queue;

/**
 * Interface for the AdaptablePriorityQueue.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

	/**
	 * Removes the entry.
	 * @param entry the entry to remove
	 */
	void remove(Entry<K, V> entry);
	
	/**
	 * Replaces a key.
	 * @param entry the entry to get
	 * @param key the key to replace
	 */
	void replaceKey(Entry<K, V> entry, K key);
	
	/**
	 * Replaces a value.
	 * @param entry the entry to get
	 * @param value the value to replace
	 */
	void replaceValue(Entry<K, V> entry, V value);	
}