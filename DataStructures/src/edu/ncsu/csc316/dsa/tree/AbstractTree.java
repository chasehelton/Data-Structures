package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
//import edu.ncsu.csc316.dsa.list.List;
//import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;

/**
 * Abstract class for the Tree.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
	/**
     * Returns true if the node has one or more children.
     * @param p the position to check
     * @return true if the node has one or more children
     */
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    /**
     * Returns true if the node has no children.
     * @param p the position to check
     * @return true if the node has no children
     */
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    /**
     * Returns true if the node is the root of the tree.
     * @param p the position to check
     * @return true if the node is the root of the tree
     */
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    /**
     * Returns true if the tree is empty; otherwise, returns false.
     * @return true if the tree is empty; otherwise, returns false
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * A class to represent a specific node in the tree.
     * @author Chase Helton
     *
     * @param <E> the generic type
     */
    protected abstract static class AbstractNode<E> implements Position<E> {

    	/** The element of the node. */
        private E element;
        
        /**
         * Constructs the AbstractNode.
         * @param element the element to set as the node
         */
        public AbstractNode(E element) {
            setElement(element);
        }
        
        /**
         * Returns the element at the tree node.
         * @return the element at the tree node
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the element of a node.
         * @param element the element to set
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Returns the node as a String.
     * @return the node as a String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Aids the toString method.
     * @param sb the StringBuilder parameter
     * @param indent indents the string
     * @param root the position to use as the root
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
    
    /**
     * Returns the positions of entries in the pre order traversal.
     * @return the positions of entries in the pre order traversal
     */
	public Iterable<Position<E>> preOrder() {
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
	/**
	 * Helps the preOrder traversal method.
	 * @param p the position to check
	 * @param traversal the list of positions to traverse
	 */
    private void preOrderHelper(Position<E> p, List<Position<E>> traversal) {
        traversal.addLast(p);
        for (Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
	}

	/**
     * Returns the positions of entries in the post order traversal.
     * @return the positions of entries in the post order traversal
     */
	public Iterable<Position<E>> postOrder() {
        List<Position<E>> list = new SinglyLinkedList<Position<E>>();
        if(!isEmpty()) {
            postOrderHelper(root(), list);
        }
        return list;
    }
    
	/**
	 * Helps the postOrder traversal method.
	 * @param p the position to check
	 * @param traversal the list of positions to traverse
	 */
    private void postOrderHelper(Position<E> p, List<Position<E>> list) {
    	for (Position<E> c : children(p)) {
        	postOrderHelper(c, list);
        }
    	list.addLast(p);
    }

	/**
     * Returns the positions of entries in the level order traversal.
     * @return the positions of entries in the level order traversal
     */
	public Iterable<Position<E>> levelOrder() {
		List<Position<E>> l = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			levelOrderHelper(root(), l);
        }
		return l;
	}
	
	/**
	 * Helps the postOrder traversal method.
	 * @param p the position to check
	 * @param traversal the list of positions to traverse
	 */
    private void levelOrderHelper(Position<E> p, List<Position<E>> list) {
    	ArrayBasedQueue<Position<E>> q = new ArrayBasedQueue<Position<E>>();
		if (p == null) { 
			return;
		}
		q.enqueue(p);
		while (!q.isEmpty()) {
			Position<E> p2 = q.dequeue();
			list.addLast(p2);
			for (Position<E> c : children(p2)) {
				q.enqueue(c);
			}
		}
    }
    
    /**
     * Used to iterate over the elements in the tree.
     * @author Chase Helton
     */
    protected class ElementIterator implements Iterator<E> {
        
    	/** Iterator for the positions. */
    	private Iterator<Position<E>> it;
        
    	/**
    	 * Constructs an ElementIterator.
    	 * @param iterator the iterator to set
    	 */
        public ElementIterator(Iterator<Position<E>> iterator) {
            it = iterator;
        }

        /**
         * Returns true if the iterator has a next item.
         * @return true if the iterator has a next item
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * Returns the next element.
         * @return the next element
         */
        @Override
        public E next() {
            return it.next().getElement();
        }
        
        /**
         * Not supported.
         */
        /*@Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }*/
    }
    
    
}
