package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface for DisjointSetForest.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public interface DisjointSetForest<E> {

	/**
	 * Makes a set and returns a position of the element.
	 * @param value the value to make a set with
	 * @return the position of the element
	 */
    Position<E> makeSet(E value);
    
    /**
     * Finds the position of a value.
     * @param value the value to find the position of
     * @return the position of a value
     */
    Position<E> find(E value);
    
    /**
     * Combines s and t.
     * @param s first position to unionize
     * @param t second position to unionize
     */
    void union(Position<E> s, Position<E> t);
}