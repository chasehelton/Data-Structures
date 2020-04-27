package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The UpTreeDisjointSetForest class.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

    /**
     * A secondary map to quickly locate an entry within the forest of up-trees.
     */
    private Map<E, UpTreeNode<E>> map;
    
    /**
     * Constructor for the class.
     */
    public UpTreeDisjointSetForest() {
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

    @Override
    public Position<E> makeSet(E value) {
    	UpTreeNode<E> n = new UpTreeNode<E>(value);
        map.put(value, n);
        return n;
    }

    @Override
    public Position<E> find(E value) {
        Position<E> p = map.get(value);
        UpTreeNode<E> n = validate(p);
        while (n.getParent() != n) {
        	n = n.getParent();
        }
        return n;
    }

    @Override
    public void union(Position<E> s, Position<E> t) {
        // Instead of trusting the client to give us the roots
        // of two up-trees, we will verify by finding the root 
        // of the up-tree that contains the element in positions s and t
        UpTreeNode<E> a = validate(find(s.getElement()));
        UpTreeNode<E> b = validate(find(t.getElement()));
    	if (a.getCount() > b.getCount()) {
    		b.setParent(a);
    		a.setCount(a.getCount() + b.getCount());
    	} else {
    		a.setParent(b);
    		b.setCount(b.getCount() + a.getCount());
    	}
    }
    
    /**
     * Validates an UpTreeNode.
     * @param p the position to validate
     * @return the validate Node.
     */
    private UpTreeNode<E> validate(Position<E> p) {
        if(!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>)p;
    }
    
    /**
     * Node used in the UpTreeDisjointSetForest.
     * @author Chase Helton
     *
     * @param <E> the generic type
     */
    private static class UpTreeNode<E> implements Position<E> {
        
    	/** Element of the Node. */
        private E element;
        /** Parent of the Node. */
        private UpTreeNode<E> parent;
        /** The count of all Nodes in the tree. */
        private int count;
        
        /**
         * Constructs an UpTreeNode.
         * @param element the element to set
         */
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }
        
        /**
         * Sets the element.
         * @param element the element to set
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         * Returns the element.
         * @return the element
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the parent.
         * @param parent the parent Node to set
         */
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }
        
        /**
         * Returns the parent.
         * @return the parent
         */
        public UpTreeNode<E> getParent() {
            return parent;
        }
        
        /**
         * Sets the count.
         * @param count the count to set
         */
        public void setCount(int count) {
            this.count = count;
        }
        
        /**
         * Returns the count.
         * @return the count
         */
        public int getCount() {
            return count;
        }
    }
}