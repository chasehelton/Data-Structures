package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * SearchTableMap implementation using the AbstractSortedMap class.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	/** The list to use for the search table. */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructs a SearchTableMap object.
	 */
	public SearchTableMap() {
		this(null);
	}
	
	/**
	 * Constructs a SearchTableMap with a comparator.
	 * @param compare the comparator to use
	 */
	public SearchTableMap(Comparator<K> compare) {
		super(compare);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * Looks up the key in the list to see where it should be added.
	 * @param key the key to look for
	 * @return the index of the key in the list, -1 if not present
	 */
	private int lookUp(K key) {
		int x = binarySearchHelper(0, list.size() - 1, key);
		if (x < 0) return -1 * (x + 1);
		else return x;
	}

	/**
	 * Aids the binary search method.
	 * @param min the minimum index to check
	 * @param max the maximum index to check
	 * @param key the key to search for
	 * @return the index of the entry if it already exists
	 * 		   otherwise, return a negative number
	 */
	private int binarySearchHelper(int min, int max, K key) {
		if (min > max) return (-1 * (min + 1));
		int mid = (max + min) / 2;
		if (list.get(mid).getKey() == key) {
			return mid;
		} else if (compare(list.get(mid).getKey(), key) > 0) {
			return binarySearchHelper(min, mid - 1, key);
		} else {
			return binarySearchHelper(mid + 1, max, key);
		}
	}

	/**
	 * Returns the size of the list.
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Returns the vale of the key.
	 * @param key the key to get the value of
	 * @return the value of the key passed in
	 */
	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index == list.size() || compare(key, list.get(index).getKey()) != 0) return null;
		return list.get(index).getValue();
	}

	/**
	 * Creates an entry set of Iterator entries.
	 * @return an entry set of Iterator entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
	}

	/**
	 * Adds the value to a certain key in the value.
	 * @param key the key to add to
	 * @param value the value to add at the key
	 * @return the value of the key
	 */
	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index < list.size() && (compare(key, list.get(index).getKey()) == 0)) {
			return list.get(index).setValue(value);
		} else {
			list.add(index, new MapEntry<K, V>(key, value));
			return null;
		}
	}

	/**
	 * Removes the key from the list.
	 * @param key the key to remove
	 * @return the value of the key removed
	 */
	@Override
	public V remove(K key) {
		int index = lookUp(key);
        if (index == list.size() || compare(key, list.get(index).getKey()) != 0) return null;
        else return list.remove(index).getValue();
	}
	
	/**
	 * Returns the list as a String.
	 * @return the list as a String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while(it.hasNext()) {
			sb.append(it.next().getKey());
			if(it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}