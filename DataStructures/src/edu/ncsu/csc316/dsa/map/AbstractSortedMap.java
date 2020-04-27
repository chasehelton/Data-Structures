package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;

/**
 * Abstract class to provide methods for sorting Maps.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public abstract class AbstractSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

	/** Comparator to compare objects. */
	private Comparator<K> compare;

	/**
	 * Constructs the AbstractSortedMap object.
	 * @param compare the Comparator to set
	 */
	public AbstractSortedMap(Comparator<K> compare) {
		if (compare == null) {
			this.compare = new NaturalOrder();
		} else {
			this.compare = compare;
		}
	}

	/**
	 * Compares 2 keys.
	 * @param key1 first key
	 * @param key2 second key
	 * @return -1 if key1 < key2
	 * 			0 if key1 = key2
	 * 			1 if key1 > key2
	 */
	public int compare(K key1, K key2) {
		if (key1 == null || key2 == null) {
			return 1;
		}
		return compare.compare(key1, key2);
	}

	/**
	 * Private class to use NaturalOrder sorting.
	 * @author Chase Helton
	 *
	 */
	private class NaturalOrder implements Comparator<K> {
		
		/**
		 * Used to compare in natural order.
		 * @param first key
		 * @param second key
		 * @return -1 if key1 < key2
		 * 			0 if key1 = key2
		 * 			1 if key1 > key2
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}
}
