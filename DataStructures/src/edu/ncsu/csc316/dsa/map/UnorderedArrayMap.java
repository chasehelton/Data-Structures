package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Creates an unordered array of map entries.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class UnorderedArrayMap<K, V> extends AbstractMap<K, V> {

	/** List of entries. */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructor for the UnorderedArrayMap
	 */
	public UnorderedArrayMap() {
		this.list = new ArrayBasedList<Entry<K, V>>();
	}
	
	/**
	 * Looks for the key throughout the entries
	 * @param key the key to look for
	 * @return the index where the key is
	 */
	private int lookUp(K key) {
		Iterator<Entry<K, V>> iterator = list.iterator();
		KeyIterator it = new KeyIterator(iterator);
		int i = 0;
		while (it.hasNext()) {
			if (it.next() == key) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	/**
	 * Gets the value at a given key
	 * @param key the entry to look for
	 * @return the value at the key
	 */
	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index == -1) return null;
		V val = list.get(index).getValue();
		transpose(index);
		return val;
	}
	
	/**
	 * Puts the specific entry in the list.
	 * @param key the key to add
	 * @param value the value to add
	 * @return v the value of the transposed index
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index == -1) {
			list.addFirst(new MapEntry(key, value));
			return null;
		}
		if (list.get(index).getKey() == key) {
			V val = list.get(index).getValue();
			list.get(index).setValue(value);
			transpose(index);
			return val;
		}
		return null;
	}
	
	/**
	 * Removes the entry at a given key.
	 * @param key the key to get the entry at
	 * @return the value at the entry removed
	 */
	@Override
	public V remove(K key) {
        int index = lookUp(key);
        if (index == -1) return null;
        else return list.remove(index).getValue();
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
	 * Improves the average-case cost of lookup by moving up every time it is called
	 * @param index the index to move up
	 * @return the value at the index
	 */
	private V transpose(int index) {
		Entry<K, V> temp = list.get(index);
		if (index > 0) {
			list.set(index, list.get(index - 1));
			list.set(index - 1, temp);
		}
		return list.get(index).getValue();
	}
	
	/**
	 * Iterates over the entries
	 * @return the list of entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return list;
	}
	
	/**
	 * Builds the map as a String.
	 * @return the map as a String
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