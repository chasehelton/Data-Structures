package edu.ncsu.csc316.dsa.map;

/**
 * The generic Map interface.
 * @author Chase Helton
 *
 * @param <K> key for the Map instance
 * @param <V> value for the Map instance
 */
public interface Map<K, V> extends Iterable<K> {
	
	/**
	 * Allows for iterating over the map.
	 * @return the Entry given the key and value
	 */
	Iterable<Entry<K, V>> entrySet();
	
	/**
	 * Returns the key.
	 * @param key the key to get
	 * @return the key if present, otherwise returns null
	 */
	V get(K key);
	
	/**
	 * Returns true if the map is empty, otherwise returns false.
	 * @return true if the map is empty, otherwise returns false
	 */
	boolean isEmpty();
	
	/**
	 * Adds the entry with key K and value V to the map.
	 * If the key already exists, update the key K to have 
	 * the new value V, and return the original value that was replaced
	 * @param key the key to add
	 * @param value the value to add
	 * @return null if the key does not exist already
	 */
	V put(K key, V value);
	
	/**
	 * Removes the entry with key K.
	 * @param key the key to remove
	 * @return the value V associated with that key K
	 */
	V remove(K key);
	
	/**
	 * Returns the number of entries in the map.
	 * @return the number of entries in the map
	 */
	int size();
	
	/**
	 * Returns the values to iterate through.
	 * @return the values to iterate through
	 */
	Iterable<V> values();
	
	/**
	 * The specific entry in the Map.
	 * @author Chase Helton
	 *
	 * @param <K> the key at the entry
	 * @param <V> the value at the entry
	 */
	interface Entry<K, V> {
		
		/**
		 * Returns the key at the entry.
		 * @return the key at the entry
		 */
		K getKey();
		
		/**
		 * Returns the value at the entry.
		 * @return the value at the entry
		 */
		V getValue();
		
		/**
		 * Sets the key at the entry.
		 * @param key the key to set
		 * @return the key set
		 */
		K setKey(K key);
		
		/**
		 * Sets the value at the entry.
		 * @param value the value to set
		 * @return the value set
		 */
		V setValue(V value);
	}
}