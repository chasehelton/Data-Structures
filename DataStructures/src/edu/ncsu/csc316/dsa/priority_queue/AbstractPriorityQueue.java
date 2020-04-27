package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * Abstract Class for Priority Queue.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	/**
	 * The comparator to use.
	 */
	private Comparator<K> comparator;

	/**
	 * The constructor with a comparator.
	 * @param c the comparator to use.
	 */
	public AbstractPriorityQueue(Comparator<K> c) {
		setComparator(c);
	}
	
	/**
	 * Sets the comparator.
	 * @param c the comparator to set
	 */
	private void setComparator(Comparator<K> c) {
		if(c == null) {
			c = new NaturalOrder();
		}
		comparator = c;
	}

	/**
	 * Returns the Natural Order of a Priority Queue.
	 * @author Chase Helton
	 */
	public class NaturalOrder implements Comparator<K> {
		
		/**
		 * Compares the keys to return in Natural Order.
		 * @param first the first key to compare
		 * @param second the second key to compare
		 * @return -1 if first is less than second
		 * 			0 if first is greater than second
		 *  		1 if first is equal to second
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}

	/**
	 * Compares 2 keys.
	 * @param data1 the first key to compare
	 * @param data2 the second key to compare
	 * @return -1 if first is less than second
	 * 			0 if first is greater than second
	 *  		1 if first is equal to second
	 */
	public int compare(K data1, K data2) {
		return comparator.compare(data1, data2);
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

    /**
     * Entry for Priority Queues.
     * @author Chase Helton
     *
     * @param <K> the key
     * @param <V> the value
     */
	public static class PQEntry<K, V> implements Entry<K, V> {

		/** The key to use. */
		private K key;
		/** The value to use. */
		private V value;

		/**
		 * Constructs an entry.
		 * @param key the key to construct
		 * @param value the value to construct
		 */
		public PQEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * Sets the key.
		 * @param key the key to set
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * Sets the value.
		 * @param value the value to set
		 */
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
	}
	
    /**
     * Factory method for constructing a new priority queue entry object	
     * @param key the key to use
     * @param value the value to use
     * @return the entry created
     */
	protected Entry<K, V> createEntry(K key, V value) {
		return new PQEntry<K, V>(key, value);
	}
}