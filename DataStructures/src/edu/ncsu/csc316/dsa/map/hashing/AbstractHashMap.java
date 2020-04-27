package edu.ncsu.csc316.dsa.map.hashing;

import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractMap;

/**
 * AbstractHashMap class.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    /**
     * An initial capacity for the hash table.
     */
    protected static final int DEFAULT_CAPACITY = 17;
    
    /**
     * To reduce the chance of having large clusters, we will resize
     * when the load factor reaches 0.5.
     */
    private static final double MAX_LOAD_FACTOR = 0.5;
    
    /** The default prime number. */
    protected static final int DEFAULT_PRIME = 109345121;
    
    // Alpha and Beta values for MAD compression
    // This implementation uses a variation of the MAD method
    // where h(k) = ( (alpha * f(k) + beta) % prime) % capacity
    private long alpha;
    private long beta;
    
    // The prime number to use for compression strategy
    private int prime;
    
    /**
     * Constructs the AbstractHashMap with capacity and checks if testing.
     * @param capacity the capacity of the map
     * @param isTesting true if testing
     */
    public AbstractHashMap(int capacity, boolean isTesting) {
        if(isTesting) {
            alpha = 1;
            beta = 1;
            prime = 7;
        } else {
            Random rand = new Random();
            alpha = rand.nextInt(DEFAULT_PRIME - 1) + 1;
            beta = rand.nextInt(DEFAULT_PRIME);
            prime = DEFAULT_PRIME;
        }
        createTable(capacity);
    }
    
    /**
     * Compress the key.
     * @param key the key to compress
     * @return the integer of the compressed key
     */
    private int compress(K key) {
        return (int)((Math.abs(key.hashCode() * alpha + beta) % prime) % capacity());
    }

    @Override
    public V put(K key, V value) {
        V ret = bucketPut(compress(key), key, value);
        if( (double) size() / capacity() > MAX_LOAD_FACTOR){
            resize(2 * capacity() + 1);
        }
        return ret;
    }
    
    @Override
    public V get(K key) {
        return bucketGet(compress(key), key);
    }

    @Override
    public V remove(K key) {
        return bucketRemove(compress(key), key);
    }
    
    /**
     * Resizes the map.
     * @param newCapacity the new capacity to set
     */
    private void resize(int newCapacity) {
        List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
        for(Entry<K, V> entry : entrySet()) {
            list.addLast(entry);
        }
        createTable(newCapacity);
        for(Entry<K, V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * The capacity abstract method.
     * @return the capacity
     */
    protected abstract int capacity();
    
    /**
     * Creates a table.
     * @param capacity the capacity of the table
     */
    protected abstract void createTable(int capacity);
    
    /**
     * Gets the bucket.
     * @param hash hashes the bucket
     * @param key the key to get the bucket with
     * @return the bucket
     */
    protected abstract V bucketGet(int hash, K key);
    
    /**
     * Puts the bucket.
     * @param hash hashes the bucket
     * @param key the key to get the bucket with
     * @param value the value to put
     * @return the value of the bucket put
     */
    protected abstract V bucketPut(int hash, K key, V value);
    
    /**
     * Removes a bucket.
     * @param hash hashes the bucket
     * @param key the key to get the bucket with
     * @return the value of the bucket removed
     */
    protected abstract V bucketRemove(int hash, K key);
}
