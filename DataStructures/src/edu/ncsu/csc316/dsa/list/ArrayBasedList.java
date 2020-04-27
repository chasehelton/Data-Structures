package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class serves as the array-based list.
 * 
 * @author Chase Helton
 * @author Bryce Junkins
 * @param <E> the generic type of list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	/** The default capacity of the array. */
	private final static int DEFAULT_CAPACITY = 10;
	/** The generic array of data. */
	private E[] data;
    /** The size of the array. */
	private int size;

	/**
	 * The default constructor for ArrayBasedList.
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * The constructor for ArrayBasedList that sets a specific starting capacity.
	 * @param capacity the capacity of the array
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}

	/**
	 * Adds the certain value at a given index.
	 * @param index the index to add to
	 * @param value the value to add
	 */
	@Override
	public void add(int index, E value) {
		ensureCapacity(DEFAULT_CAPACITY);
		this.checkIndexForAdd(index);
		if (data.length == size) {
			@SuppressWarnings("unchecked")
			E[] temp = (E[]) (new Object[data.length * 2]);
			for (int i = 0; i < data.length; i++) temp[i] = data[i];
			data = temp;
		}
		if (index < size) {
			for (int i = size; i > index; i--) data[i] = data[i - 1];
		}
		if (value != null) {
			data[index] = value;
			size++;
		}
	}

	/**
	 * Returns the item at a given index.
	 * @param index the index to return
	 * @return the item at a given index
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	/**
	 * Removes the item at a given index.
	 * @param index the index to remove
	 * @return the item at the index
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);
		E temp = get(index);
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		if (size < data.length) data[size - 1] = data[size];
		size--;
		return temp;
	}

	/**
	 * Sets the value of an element at a given index.
	 * @param index the index to set
	 * @param value the value to set
	 * @return the value that was changed
	 */
	@Override
	public E set(int index, E value) {
		checkIndex(index);
		data[index] = value;
		return data[index];
	}

	/**
	 * Returns the size of the list.
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/**
	 * Ensures the capacity of the array is allowed.
	 * @param minCapacity the minimum capacity allowed
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    /**
     * Private inner class to give ArrayBasedList an iterator.
     * 
     * @author ChaseHelton
     */
    private class ElementIterator implements Iterator<E> {
        
    	/** The position of the iterator in the list, replaces index. */
    	private int position;
        /** Boolean to determine if it is okay to remove the item at the position. */
    	private boolean removeOK;

    	/**
    	 * Constructor for the ElementIterator.
    	 */
        public ElementIterator() {
            position = 0;
            removeOK = false;
        }

        /**
         * Returns true if the iteration has more elements, false if not. 
         * Replaces the need to throw an exception.
         * @return true if the iteration has more elements, false if not
         */
        public boolean hasNext() {
            return position < size;
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         * @throws NoSuchElementException if the client attempts to call next() with no more elements
         */
        public E next() {
        	if (!hasNext()) throw new NoSuchElementException();
            position++;
            removeOK = true;
            return ArrayBasedList.this.get(position - 1);
        }
        
        /**
         * Removes from the underlying collection the last element returned by this iterator.
         * This method can be called only once per call to next().
         * @throws IllegalStateException if the next method has not yet been called, or remove
         * 		   has already been called after the last call to the next method
         */
        public void remove() {
            if (removeOK) {
                ArrayBasedList.this.remove(position - 1);
                position--;
                removeOK = false;
            } else throw new IllegalStateException();
        }
    }
}