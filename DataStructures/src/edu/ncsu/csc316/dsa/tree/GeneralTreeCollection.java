package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * General Tree Collection Interface.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public interface GeneralTreeCollection<E> extends Tree<E>, Iterable<E> {
	
	/**
	 * Adds an element as the root of the tree, then returns a reference
	 * to the new root.
	 * @param value the value to add to the tree
	 * @return the position the root was added
	 */
    Position<E> addRoot(E value);
    
    /**
     * Adds an element as the child of position p, then returns a reference
     * to the new child.
     * @param p the position to add to
     * @param value the value of the child added
     * @return the position of the child added
     */
    Position<E> addChild(Position<E> p, E value);
    
    /**
     * Removes the position p from the tree, and returns the value that was
     * stored in the removed position.
     * @param p the position to remove
     * @return the value of the removed node
     */
    E remove(Position<E> p);
    
    /**
     * Updates the element stored at position p, and returns the original
     * element that was overridden.
     * @param p the position to set
     * @param value the value to set
     * @return the original element that was overriden
     */
    E set(Position<E> p, E value);
}