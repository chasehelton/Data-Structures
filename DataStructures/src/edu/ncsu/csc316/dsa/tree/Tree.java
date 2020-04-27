package edu.ncsu.csc316.dsa.tree;
import edu.ncsu.csc316.dsa.Position;

/**
 * Tree interface.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public interface Tree<E> {

	/**
	 * Returns the root of the tree.
	 * @return the root of the tree
	 */
    Position<E> root();
    
    /**
     * Returns the parent of the given node p.
     * @param p the position to check
     * @return the parent of the given node p
     */
    Position<E> parent(Position<E> p);
    
    /**
     * Returns a list of children of the given node p.
     * @param p the position to check
     * @return a list of children of the given node p
     */
    Iterable<Position<E>> children(Position<E> p);
    
    /**
     * Returns the number of children of the given node p.
     * @param p the position to check
     * @return the number of children of the given node p
     */
    int numChildren(Position<E> p);
    
    /**
     * Returns true if the node has one or more children.
     * @param p the position to check
     * @return true if the node has one or more children
     */
    boolean isInternal(Position<E> p);
    
    /**
     * Returns true if the node has no children.
     * @param p the position to check
     * @return true if the node has no children
     */
    boolean isLeaf(Position<E> p);
    
    /**
     * Returns true if the node is the root of the tree.
     * @param p the position to check
     * @return true if the node is the root of the tree
     */
    boolean isRoot(Position<E> p);
    
    /**
     * Returns the number of entries in the tree.
     * @return the number of entries in the tree
     */
    int size();
    
    /**
     * Returns true if the tree is empty; otherwise, returns false.
     * @return true if the tree is empty; otherwise, returns false
     */
    boolean isEmpty();

    /**
     * Returns the positions of entries in the pre order traversal.
     * @return the positions of entries in the pre order traversal
     */
    Iterable<Position<E>> preOrder();
    
    /**
     * Returns the positions of entries in the post order traversal.
     * @return the positions of entries in the post order traversal
     */
    Iterable<Position<E>> postOrder();
    
    /**
     * Returns the positions of entries in the level order traversal.
     * @return the positions of entries in the level order traversal
     */
    Iterable<Position<E>> levelOrder();
}