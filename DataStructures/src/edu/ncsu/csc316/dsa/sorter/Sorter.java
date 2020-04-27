package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * 
 * @author Dr. King
 * 
 * @param <E> the generic type of sorting arrays
 */
public interface Sorter<E> {
    
	/** 
	 * Used to sort in sorter classes. 
	 * @param data the array to sort
	 */
	void sort(E[] data);
}