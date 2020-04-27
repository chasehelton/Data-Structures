package edu.ncsu.csc316.dsa.list;

/**
 * Interface to delegate specific methods that the lists will use.
 * 
 * @author Chase Helton
 * @param <E> the generic type of List
 */
public interface List<E> extends Iterable<E> {
	
	/**
	 * Adds the certain value at a given index.
	 * @param index the index to add to
	 * @param value the value to add
	 */
	void add(int index, E value);
	
	/**
	 * Adds a value to the front of the list.
	 * @param value the value to add
	 */
	void addFirst(E value);
	
	/** 
	 * Adds a value to the last of the list.
	 * @param value the value to add
	 */
	void addLast(E value);
	
	/**
	 * Returns the first item in the list.
	 * @return the first item in the list
	 */
	E first();
	
	/**
	 * Returns the item at a given index.
	 * @param index the index to return
	 * @return the item at a given index
	 */
	E get(int index);
	
	/**
	 * Returns true if the list is empty, false otherwise
	 * @return true if empty, false if not
	 */
	boolean isEmpty();
	
	/**
	 * Returns the last item in the list.
	 * @return the last item in the list
	 */
	E last();
	
	/**
	 * Removes the item at a given index.
	 * @param index the index to remove
	 * @return the item at the index
	 */
	E remove(int index);
	
	/**
	 * Removes the first item in the list.
	 * @return the first item in the list
	 */
	E removeFirst();
	
	/**
	 * Removes the last item in the list.
	 * @return the last item in the list
	 */
	E removeLast();
	
	/**
	 * Sets the value of an element at a given index.
	 * @param index the index to set
	 * @param value the value to set
	 * @return the value that was changed
	 */
	E set(int index, E value);
	
	/**
	 * Returns the size of the list.
	 * @return the size of the list
	 */
	int size();
}