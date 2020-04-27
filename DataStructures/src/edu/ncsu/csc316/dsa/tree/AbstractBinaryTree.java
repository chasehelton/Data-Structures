package edu.ncsu.csc316.dsa.tree;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * Abstract class for the BinaryTree.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTreeCollection<E> {

	/**
	 * InOrder traversal.
	 * @return a list of positions of the tree in order
	 */
    @Override
    public Iterable<Position<E>> inOrder() {
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        return traversal;
    }

    /**
     * Helps the inOrder() method.
     * @param node the position to help
     * @param traversal list of positions
     */
    private void inOrderHelper(Position<E> node, List<Position<E>> traversal) {
        if (left(node) != null) {
        	inOrderHelper(left(node), traversal);
        }
        traversal.addLast(node);
        if (right(node) != null) {
        	inOrderHelper(right(node), traversal);
        }
    }
    
    /**
     * Returns the sibling of the position.
     * Cited from page 320 of the textbook.
     * @param p the position of the Node
     * @return the sibling of the position
     */
    @Override
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if (parent == null) return null;
        if (p == left(parent)) return right(parent);
        else return left(parent);
    }
    
    /**
     * Validates the Node to make sure it is legal.
     * @param p the position to check
     * @return the validated Node
     */
    private AbstractNode<E> validate(Position<E> p) {
        if(!(p instanceof AbstractNode)) {
            throw new IllegalArgumentException("Position is not a valid binary tree node");
        }
        return (AbstractNode<E>)(p);
    }
    
    /**
     * Returns the number of children at the position.
     * Cited from page 320 of the textbook.
     * @param p the position to check
     * @return the number of children at the position
     */
    @Override
    public int numChildren(Position<E> p) {
        int count = 0;
        if (left(p) != null) count++;
        if (right(p) != null) count++;
        return count;
    }
    
    /**
     * Sets the value at a position.
     * @param p the position to set the value at
     * @param value the value to set
     * @return the original element at the position
     */
    @Override
    public E set(Position<E> p, E value) {
        AbstractNode<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    /**
     * Returns the iterator for the BinaryTree.
     * @return the iterator for the BinaryTree
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(inOrder().iterator());
    }
    
    /**
     * Returns a list of positions for the children at a position.
     * @param p the position to check
     * @return a list of positions for the children at a position
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractNode<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        if(left(node) != null) {
            ret.addLast(left(node));
        }
        if(right(node) != null) {
            ret.addLast(right(node));
        }
        return ret;
    }
}