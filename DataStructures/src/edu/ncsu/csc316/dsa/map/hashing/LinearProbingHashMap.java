package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * LinearProbingHashMap class.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

    /** An array of TableEntry objects. */
    private TableEntry<K, V>[] table;
    /** The size of the array. */
    private int size;

    /**
     * Constructs a LinearProbingHashMap.
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * Constructs a LinearProbingHashMap.
     * @param isTesting checks if testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }
    
    /**
     * Constructs a LinearProbingHashMap.
     * @param capacity sets the capacity
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }
    
    /**
     * Constructs a LinearProbingHashMap.
     * @param capacity sets the capacity
	 * @param isTesting checks if testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	ArrayBasedList<Entry<K, V>> buffer = new ArrayBasedList<Entry<K, V>>();
    	for (int i = 0; i < table.length; i++) {
    		if (!isAvailable(i)) buffer.addLast(table[i]);
    	}
    	return buffer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }
    
    /** 
     * Helper method to determine whether a bucket has an entry or not.
     * @param index index to check
     * @return returns true if available
     */
    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    /**
     * Helper method to find the bucket for an entry.
     * If the entry *is* in the map, returns the index of the bucket
     * If the entry is *not* in the map, returns -(a + 1) to indicate 
     * that the entry should be added at index a
     * Cited from textbook page 427.
     * @param index the index to find
     * @param key the key to check
     * @return index of the bucket
     */
    private int findBucket(int index, K key) {
        int a = -1;
        int j = index;
        do {
        	if (isAvailable(j)) {
        		if (a == -1) a = j;
        		if (table[j] == null) break;
        	} else if (table[j].getKey().equals(key)) {
        		return j;
        	}
        	j = (j + 1) % table.length;
        } while (j != index);
        return -(a + 1);
    }
    
    @Override
    public V bucketGet(int hash, K key) {
        int index = findBucket(hash, key);
        if (index < 0) return null;
        return table[index].getValue();
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        int index = findBucket(hash, key);
        if (index >= 0) return table[index].setValue(value);
        table[-(index + 1)] = new TableEntry<>(key, value);
        size++;
        return null;
    }   

    @Override
    public V bucketRemove(int hash, K key) {
        int index = findBucket(hash, key);
        if (index < 0) return null;
        V answer = table[index].getValue();
        table[index].setDeleted(true);
        size--;
        return answer;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }
    
    /**
     * Private inner class to store new elements.
     * @author Chase Helton
     *
     * @param <K> the key
     * @param <V> the value
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

    	/** Checks if deleted */
        private boolean isDeleted;

        /**
         * Constructs a TableEntry.
         * @param key the key to set
         * @param value the value to set
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * Returns true if deleted false if not.
         * @return true if deleted false if not
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /**
         * Sets the deleted boolean.
         * @param deleted the deleted boolean to set
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}