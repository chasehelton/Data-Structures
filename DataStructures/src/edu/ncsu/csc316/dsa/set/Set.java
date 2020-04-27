package edu.ncsu.csc316.dsa.set;

/**
 * Interface for the Set DS.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public interface Set<E> extends Iterable<E> {
	
	/**
	 * Adds a value to the set.
	 * @param value the value to add
	 */
    void add(E value);
    
    /**
     * Checks if the set contains a value.
     * @param value the value to check
     * @return true if the set contains the value, false otherwise
     */
    boolean contains(E value);
    
    /**
     * Removes the value.
     * @param value the value to remove
     * @return the removed element
     */
    E remove(E value);
    
    /**
     * Returns true if the set is empty.
     * @return true if the set is empty
     */
    boolean isEmpty();
    
    /**
     * Returns the size of the set.
     * @return the size of the set
     */
    int size();
    
    /**
     * Adds all elements from one set to another.
     * @param other the other set to add
     */
    void addAll(Set<E> other);
    
    /**
     * Retains all from one set to another.
     * @param other the other set to retain
     */
    void retainAll(Set<E> other);
    
    /**
     * Removes all from one set to another.
     * @param other the other set to remove
     */
    void removeAll(Set<E> other);
}
