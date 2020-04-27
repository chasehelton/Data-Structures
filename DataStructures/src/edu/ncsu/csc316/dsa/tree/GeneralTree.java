package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * The GeneralTree class which uses the AbstractTree class.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public class GeneralTree<E> extends AbstractTree<E> implements GeneralTreeCollection<E> {

	/** The root Node */
    private Node<E> root;
    /** The size of the tree */
    private int size;
    
    /**
     * Constructs a GeneralTree.
     */
    public GeneralTree() {
        root = null;
        size = 0;
    }
    
    /**
     * Checks to make sure the position is legal.
     * @param p the position to check
     * @return the Node validated
     */
    private Node<E> validate(Position<E> p) {
        if(!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a legal general tree node");
        }
        return (Node<E>)p;
    }
    
    /**
     * Returns the position of the root.
     * @return the position of the root
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the parent of the Node at a position.
     * @param p the position to check
     * @return the position of the parent
     */
    @Override
    public Position<E> parent(Position<E> p) {
        return validate(p).getParent();
    }

    /**
     * Returns the children of the Node at a position.
     * @param p the position to check
     * @return a list of children of the parent
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        Node<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        for(Position<Node<E>> n : node.getChildren().positions())
        {
            ret.addLast(n.getElement());
        }
        return ret;
    }

    /**
     * Returns the number of children.
     * @param p the position to check how many children there are
     * @return the number of children
     */
    @Override
    public int numChildren(Position<E> p) {
        Node<E> node = validate(p);
        return node.getChildren().size();
    }
    
    /**
     * Adds root to the tree.
     * @param value the value of the root to add
     * @return the position of the root
     */
    @Override
    public Position<E> addRoot(E value) {
        if(root != null) {
            throw new IllegalArgumentException("Tree already has a root");
        }
        this.root = createNode(value);
        size = 1;
        return root;
    }   

    /**
     * Adds child to the tree.
     * @param p the position of the node to add to
     * @param value the value of the root to add
     * @return the position of the root
     */
    @Override
    public Position<E> addChild(Position<E> p, E value) {
        Node<E> node = validate(p);
        Node<E> newNode = createNode(value);
        node.getChildren().addLast(newNode);
        newNode.setParent(node);
        size++;
        return newNode;
    }

    /**
     * Sets the node's value at a position.
     * @param p the position to set
     * @param value the value to set
     * @return the element of the original node
     */
    @Override
    public E set(Position<E> p, E value) {
        Node<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    /**
     * Creates a Node with a value.
     * @param element the value of the element to create
     * @return the Node created
     */
    public Node<E> createNode(E element) {
        return new Node<E>(element);
    }
	
    /**
     * Returns the size of the Tree.
     * @return the size of the Tree
     */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Creates an iterator.
	 * @return the iterator created
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(preOrder().iterator());
	}

	/**
	 * Removes the Node at a position p.
	 * @param p the position to remove
	 * @return the element of the position removed
	 */
	@Override
	public E remove(Position<E> p) {
		// We will only support removal of a node that only has 1 child
	    if(numChildren(p) > 1) {
	        throw new IllegalArgumentException("The node has more than 1 child.");
	    }
	    // Handle special case if the node being removed is the root
	    if(isRoot(p)) {
	        E original = p.getElement();
	        if(numChildren(p) == 0) {
	            this.root = null;
	        } else {
	            Node<E> replacement = validate(p).getChildren().first().getElement();
	            replacement.setParent(null);
	            this.root = replacement;
	        }
	        size--;
	        return original;
	    }
	    // Handle the case where the node being removed is NOT the root
	    Node<E> node = validate(p);
	    Node<E> parent = validate(parent(p));
	    // Create an iterator over the parent node's children positions
	    Iterator<Position<Node<E>>> it = parent.getChildren().positions().iterator();
	    while(it.hasNext()) {
	        // Find the position of the node to be removed
	        Position<Node<E>> current = it.next();
	        if(current.getElement() == node) {
	            if(numChildren(p) == 1) {
	                // If the node being removed has 1 child, replace it
	                // in the parent's list of children
	                Node<E> replacement = node.getChildren().first().getElement();
	                replacement.setParent(parent);
	                parent.getChildren().set(current, replacement);
	            } else {
	                // If the node being removed has 0 children, remove it
	                parent.getChildren().remove(current);
	            }
	        }
	    }
	    size--;
	    return node.getElement();
	}

	/**
	 * Private inner class for the Tree's Node.
	 * @author Chase Helton
	 *
	 * @param <E> the generic type
	 */
    private static class Node<E> extends AbstractNode<E> {

    	/** The parent node. */
        private Node<E> parent;
        
        /** A list of children Nodes. */
        private PositionalList<Node<E>> children;
        
        /**
         * Constructs a Node.
         * @param element the element of the Node
         */
        public Node(E element) {
            this(element, null);
        }
        
        /**
         * Constructs a Node with an element and a parent.
         * @param element the element of the Node
         * @param parent the parent of the Node
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
            children = new PositionalLinkedList<Node<E>>();
        }
        
        /**
         * Sets the parent of the Node.
         * @param p the Node to set as the parent
         */
        public void setParent(Node<E> p) {
            parent = p;
        }
        
        /**
         * Returns the parent of the Node.
         * @return the parent of the Node
         */
        public Node<E> getParent() {
            return parent;
        }
        
        /**
         * Returns a list of Nodes as the children of a Node.
         * @return a list of Nodes as the children of a Node
         */
        public PositionalList<Node<E>> getChildren() {
            return children;
        }
    }
}