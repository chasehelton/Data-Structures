package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Represents the BinaryTree as a LinkedList.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/** The root Node for the tree. */
    private Node<E> root;
    /** The size of the tree. */
    private int size;

    /**
     * The LinkedBinaryTree constructor.
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Validates the Node.
     * @param p the position to check
     * @return the validated Node
     */
    protected Node<E> validate(Position<E> p) {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a valid linked binary node");
        }
        return (Node<E>) p;
    }

    /**
     * Class to represent a specific Node in the tree.
     * @author Chase Helton
     *
     * @param <E> the generic type
     */
    public static class Node<E> extends AbstractNode<E> {
        
    	/** The parent Node. */
    	private Node<E> parent;
    	/** The left Node. */
        private Node<E> left;
        /** The right Node. */
        private Node<E> right;

        /**
         * Constructs the Node.
         * @param element the element to construct the Node with.
         */
        public Node(E element) {
            this(element, null);
        }

        /**
         * Constructs a Node with a parent.
         * @param element the element the Node is set with
         * @param parent the parent of the Node
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Gets the left Node.
         * @return the left Node
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * Gets the right Node.
         * @return the right Node
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * Sets the left Node.
         * @param left the Node to set
         */
        public void setLeft(Node<E> left) {
            this.left = left;
        }

        /**
         * Sets the right Node.
         * @param right the Node to set
         */
        public void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * Gets the parent.
         * @return the parent of the Node
         */
        public Node<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent.
         * @param parent the parent of the Node
         */
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }

    /**
     * Returns the position of the Node to the left.
     * @param p the position to check
     * @return the left position
     */
    @Override
    public Position<E> left(Position<E> p) {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Returns the position of the Node to the right.
     * @param p the position to check
     * @return the right position
     */
    @Override
    public Position<E> right(Position<E> p) {
        Node<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Adds a Node to the left.
     * Cited from textbook page 328.
     * @param p the position to add to the left of
     * @param value the value to set
     * @return the position of the Node
     */
    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        Node<E> child = createNode(value, node, null, null);
        node.setLeft(child);
        size++;
        return child;
    }

    /**
     * Adds a Node to the right.
     * Cited from textbook page 328.
     * @param p the position to add to the right of
     * @param value the value to set
     * @return the position of the Node
     */
    @Override
    public Position<E> addRight(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        Node<E> child = createNode(value, node, null, null);
        node.setRight(child);
        size++;
        return child;
    }

    /**
     * Returns the root.
     * @return the root
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the position of the parent at a position.
     * @param p the position to check
     * @return the position of the parent at a position
     */
    @Override
    public Position<E> parent(Position<E> p) {
        Node<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Adds a root.
     * @param value the value to add
     * @return the position of the root
     */
    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }

    /**
     * Removes the Node at a position p.
     * Citing from textbook page 329.
     * @param p the position to check
     * @return the element of the Node removed
     */
    @Override
    public E remove(Position<E> p) {
        if (numChildren(p) == 2){
            throw new IllegalArgumentException("The node has two children");
        }
        Node<E> node = validate(p);
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) child.setParent(node.getParent());
        if (node == root) root = child;
        else {
        	Node<E> parent = node.getParent();
        	if (node == parent.getLeft()) parent.setLeft(child);
        	else parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
    }

    /**
     * The size of the tree.
     * @return the size of the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Creates a Node.
     * @param e the element to add
     * @param parent the parent of the Node
     * @param left the left Node
     * @param right the right Node
     * @return the Node created
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        Node<E> newNode = new Node<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    /**
     * Sets the root.
     * @param p the position to set at
     * @return the position of the root
     */
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}