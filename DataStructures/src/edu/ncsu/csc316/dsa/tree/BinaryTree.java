package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * BinaryTree interface.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public interface BinaryTree<E> extends Tree<E> {
	
	/**
	 * Returns the left child of the node p.
	 * @param p the position to check
	 * @return the left child of the node p
	 */
    Position<E> left(Position<E> p);
    
    /**
	 * Returns the right child of the node p.
	 * @param p the position to check
	 * @return the right child of the node p
	 */
    Position<E> right(Position<E> p);
    
    /**
	 * Returns the sibling of the node p.
	 * @param p the position to check
	 * @return the sibling of the node p
	 */
    Position<E> sibling(Position<E> p);
    
    /**
     * Returns the in order positions.
     * @return the in order positions
     */
    Iterable<Position<E>> inOrder();
}
