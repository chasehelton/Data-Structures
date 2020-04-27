package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.SkipListMap;

/**
 * SeparateChainingHashMap class.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {

	/** Table for the class. */
    private Map<K, V>[] table;
    /** Size of the class. */
    private int size;
    
    /**
     * Constructor for the SeparateChainingHashMap.
     */
    public SeparateChainingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * Constructor for the SeparateChainingHashMap.
     * @param isTesting checks if the user is testing
     */
    public SeparateChainingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }  	
	
    /**
     * Constructor for the SeparateChainingHashMap.
     * @param capacity the capacity of the map
     */
    public SeparateChainingHashMap(int capacity) {
        this(capacity, false);
    }    
    
    /**
     * Constructor for the SeparateChainingHashMap.
     * @param capacity the capacity of the map
     * @param isTesting checks if the user is testing
     */
    public SeparateChainingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
        for(int i = 0; i < capacity(); i++) {
            if(table[i] != null) {
                // Each bucket contains a map, so include
                // all entries in the entrySet for the map
                // at the current bucket
                for(Entry<K, V> entry : table[i].entrySet()) {
                    list.addLast(entry);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        // Example -- change this to whatever map you'd like        
        table = new SkipListMap[capacity];
        size = 0;
    }

    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table      
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist      
        if(bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value     
        return bucket.get(key);
    }

    /**
     * Cited from textbook page 425.
     */
    @Override
    public V bucketPut(int hash, K key, V value) {
        Map<K, V> bucket = table[hash]; 
        if (bucket == null) {
        	table[hash] = new SkipListMap<K, V>();
        	bucket = table[hash];
        }
        int oldSize = bucket.size();
        V answer = bucket.put(key, value);
        size += (bucket.size() - oldSize);
        return answer;
    }

    /**
     * Cited from textbook page 425.
     */
    @Override
    public V bucketRemove(int hash, K key) {
        Map<K, V> bucket = table[hash];
        if (bucket == null) return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(key);
        size -= (oldSize - bucket.size());
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
}