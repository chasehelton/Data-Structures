package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * BinaryTreeCollection interface.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public interface BinaryTreeCollection<E> extends BinaryTree<E>, Iterable<E> {
	
	/**
	 * Adds a root to the tree.
	 * @param value the value to add
	 * @return the position added
	 */
    Position<E> addRoot(E value);
    
    /**
     * Adds a root to the left.
     * @param p the position to add to the left of
     * @param value the value to add
     * @return the position added
     */
    Position<E> addLeft(Position<E> p, E value);
    
    /**
     * Adds a root to the right.
     * @param p the position to add to the right of
     * @param value the value to add
     * @return the position added
     */
    Position<E> addRight(Position<E> p, E value);
    
    /**
     * Removes the Node from the tree.
     * @param p the position to check
     * @return the element of the Node removed
     */
    E remove(Position<E> p);
    
    /**
     * Sets the Node at a position.
     * @param p the position to check
     * @param value the value to set
     * @return the original value of the Node
     */
    E set(Position<E> p, E value);
}
