package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractSortedMap;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.tree.BinaryTree;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree;

/**
 * BinarySearchTreeMap class.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V>
        implements BinaryTree<Entry<K, V>> {

    /**
     * The BalanceableBinaryTree class is an inner class below.
     */
    private BalanceableBinaryTree<K, V> tree;

    /**
     * Constructs a BinarySearchTreeMap.
     */
    public BinarySearchTreeMap() {
        this(null);
    }

    /**
     * Construct a BinarySearchTreeMap.
     * @param compare the comparator
     */
    public BinarySearchTreeMap(Comparator<K> compare) {
        super(compare);
        tree = new BalanceableBinaryTree<K, V>();
        tree.addRoot(null);
    }

    /**
     * Return size.
     * @return the size
     */
    @Override
    public int size() {
        // Our search trees will all use dummy/sentinel leaf nodes,
        // so the actual number of elements in the tree will be (size-1)/2      
        return (tree.size() - 1) / 2;
    }

    /**
     * This method is used to add dummy/sentinel left and right children as leaves.
     * @param p the position
     * @param entry the entry
     */
    private void expandLeaf(Position<Entry<K, V>> p, Entry<K, V> entry) {
        // initially, p is a dummy/sentinel node,
        // so replace the null entry with the new actual entry      
        tree.set(p, entry);
        // Then add new dummy/sentinel children     
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    /**
     * This helper method traces a path down the tree to locate the position 
     * that contains an entry with the given key.
     * @param p the position
     * @param key the key
     * @return the position found
     */
    // Think of "lookUp" as returning the last node visited when tracing
    // a path down the tree to find the given key
    private Position<Entry<K, V>> lookUp(Position<Entry<K, V>> p, K key) {
        // If we have reached a dummy/sentinel node (a leaf), return that sentinel node
        if (isLeaf(p)) {
            return p;
        }
        int comp = compare(key, p.getElement().getKey());
        if (comp == 0) {
            // Return the position that contains the entry with the key         
            return p;
        } else if (comp < 0) {
            return lookUp(left(p), key);
        } else {
            return lookUp(right(p), key);
        }
    }

    /**
     * Return the value at a key.
     * @param key the key to check
     * @return the value at a key
     */
    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(tree.root(), key);
        // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use        
        actionOnAccess(p);
        if (isLeaf(p)) {
            return null;
        }
        return p.getElement().getValue();
    }

    /**
     * Put the key in the map.
     * @param key the key to put
     * @param value the value of the key
     * @return the value of the entry
     */
    @Override
    public V put(K key, V value) {
        // Create the new map entry     
        Entry<K, V> newEntry = new MapEntry<K, V>(key, value);
        // Get the last node visited when looking for the key       
        Position<Entry<K, V>> p = lookUp(root(), key);
        // If the last node visited is a dummy/sentinel node        
        if (isLeaf(p)) {
            expandLeaf(p, newEntry);
            // actionOnInsert is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnInsert(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            set(p, newEntry);
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnAccess(p);
            return original;
        }
    }

    /**
     * Remove key.
     * @param key the key to remove
     * @return the value of the key removed
     */
    @Override
    public V remove(K key) {
        // Get the last node visited when looking for the key       
        Position<Entry<K, V>> p = lookUp(root(), key);
        // If p is a dummy/sentinel node        
        if (isLeaf(p)) {
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnAccess(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            // If the node has two children (that are not dummy/sentinel nodes)         
            if (isInternal(left(p)) && isInternal(right(p))) {
                // Replace with the inorder successor               
                Position<Entry<K, V>> replacement = treeMin(right(p));
                set(p, replacement.getElement());
                // Move p to the replacement node in the right subtree              
                p = replacement;
            }
            // Get the dummy/sentinel node (in case the node has an actual entry as a child)...         
            Position<Entry<K, V>> leaf = (isLeaf(left(p)) ? left(p) : right(p));
            // ... then get its sibling (will be another sentinel or an actual entry node)          
            Position<Entry<K, V>> sib = sibling(leaf);
            // Remove the leaf NODE (this is your binary tree remove method)            
            remove(leaf);
            // Remove the NODE (this is your binary tree remove method)
            // which will "promote" the sib node to replace p           
            remove(p);
            // actionOnDelete is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnDelete(sib);
            return original;
        }
    }

    /**
     * Returns the inorder successor (the minimum from the right subtree).
     * @param node the node to check
     * @return the position
     */
    private Position<Entry<K, V>> treeMin(Position<Entry<K, V>> node) {
        Position<Entry<K, V>> current = node;
        while (isInternal(current)) {
            current = left(current);
        }
        return parent(current);
    }

    /**
     * Returns the tree as an iterable entry set.
     * @return the tree as an iterable entry set
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>(size());
        for (Position<Entry<K, V>> n : tree.inOrder()) {
            set.addLast(n.getElement());
        }
        return set;
    }

    /**
     * Returns tree as a string.
     * @return tree as a string
     */
    @Override
    public String toString() {
        return tree.toString();
    }

    /**
     * This is a "hook" method that will be overridden in your AVL,
     * Splay, and Red-Black tree implementations.
     * @param node the node to hook
     */
    protected void actionOnAccess(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    /**
     * This is a "hook" method that will be overridden in your AVL,
     * Splay, and Red-Black tree implementations.
     * @param node the node to hook
     */
    protected void actionOnInsert(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    /**
     * This is a "hook" method that will be overridden in your AVL,
     * Splay, and Red-Black tree implementations.
     * @param node the node to hook
     */
    protected void actionOnDelete(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    /**
     * BalanceableBinaryTree class.
     * @author Chase Helton
     *
     * @param <K> the key
     * @param <V> the value
     */
    protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

        /**
         * Relink is a helper method for trinode restructuring and rotations.
         * @param parent the parent
         * @param child the child
         * @param makeLeftChild true if should make it the left child or not
         */
        private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
            child.setParent(parent);
            if (makeLeftChild) {
            	parent.setLeft(child);
            } else {
            	parent.setRight(child);
            }
        }

        /**
         * Rotate is a helper method for handling rotations and relinking nodes.
         * Cited from textbook page 478.
         * @param p the position to rotate around
         */
        public void rotate(Position<Entry<K, V>> p) {
            Node<Entry<K, V>> node = validate(p);
            Node<Entry<K, V>> parent = node.getParent();
            Node<Entry<K, V>> grandparent = parent.getParent();
            if (grandparent == null) {
            	setRoot(node);
            	node.setParent(null);
            } else {
            	relink(grandparent, node, parent == grandparent.getLeft());
            }
            if (node == parent.getLeft()) {
            	relink(parent, node.getRight(), true);
            	relink(node, parent, false);
            } else {
            	relink(parent, node.getLeft(), false);
            	relink(node, parent, true);
            }
        }

        /**
         * Restructure is a helper method to perform a trinode restructuring
         * and trigger the appropriate rotations.
         * @param x the position to restructure at
         * @return the position restructured
         */
        public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        	Position<Entry<K, V>> parent = parent(x);
        	Position<Entry<K, V>> grandparent = parent(parent);
        	if ((x == right(parent)) == (parent == right (grandparent))) {
        		rotate(parent);
        		return parent;
        	} else {
        		rotate(x);
        		rotate(x);
        		return x;
        	}
        }

        /**
         * Create a node.
         * @param element the element
         * @param parent the parent
         * @param left the left node
         * @param right the right node
         * @return the node created
         */
        @Override
        protected Node<Entry<K, V>> createNode(Entry<K, V> element, Node<Entry<K, V>> parent, Node<Entry<K, V>> left,
                Node<Entry<K, V>> right) {
            BSTNode<Entry<K, V>> newNode = new BSTNode<Entry<K, V>>(element);
            newNode.setParent(parent);
            newNode.setLeft(left);
            newNode.setRight(right);
            newNode.setProperty(0);
            return newNode;
        }

        /**
         * A binary search tree node needs to keep track of some property.
         * @author Chase Helton
         *
         * @param <E> the generic type
         */
        // For example, for AVL trees the "property" is the height of the node.
        // For Red-Black trees, the "property" is the color of the node.
        protected static class BSTNode<E> extends Node<E> {

            private int property;

            /**
             * Construct a Node.
             * @param element the element to set
             */
            public BSTNode(E element) {
                super(element);
                setProperty(0);
            }

            /**
             * Set property.
             * @param height the height
             */
            public void setProperty(int height) {
                this.property = height;
            }

            /**
             * Get property.
             * @return the property
             */
            public int getProperty() {
                return property;
            }
        }

        /**
         * Get property.
         * @param p the position to get
         * @return an integer of the position
         */
        public int getProperty(Position<Entry<K, V>> p) {
            if (p == null) {
                return 0;
            }
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) p;
            return node.getProperty();
        }

        /**
         * Set property.
         * @param p the position to check
         * @param value the value to set
         */
        public void setProperty(Position<Entry<K, V>> p, int value) {
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) (p);
            node.setProperty(value);
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // All the methods below delegate to the BalanceableBinaryTree class, which extends 
    // your linked binary tree implementation
    /////////////////////////////////////////////////////////////////////////////

    @Override
    public Position<Entry<K, V>> root() {
        return tree.root();
    }

    @Override
    public Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
        return tree.parent(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> children(Position<Entry<K, V>> p) {
        return tree.children(p);
    }

    @Override
    public int numChildren(Position<Entry<K, V>> p) {
        return tree.numChildren(p);
    }

    @Override
    public boolean isInternal(Position<Entry<K, V>> p) {
        return tree.isInternal(p);
    }

    /**
     * Sets the position.
     * @param p the position to set
     * @param entry the entry to set
     * @return the old entry
     */
    public Entry<K, V> set(Position<Entry<K, V>> p, Entry<K, V> entry) {
        return tree.set(p, entry);
    }

    @Override
    public boolean isLeaf(Position<Entry<K, V>> p) {
        return tree.isLeaf(p);
    }

    @Override
    public boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> preOrder() {
        return tree.preOrder();
    }

    @Override
    public Iterable<Position<Entry<K, V>>> postOrder() {
        return tree.postOrder();
    }

    @Override
    public Iterable<Position<Entry<K, V>>> levelOrder() {
        return tree.levelOrder();
    }

    @Override
    public Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
        return tree.left(p);
    }

    /**
     * Removes an entry.
     * @param p the position to check
     * @return the entry removed
     */
    protected Entry<K, V> remove(Position<Entry<K, V>> p) {
        return tree.remove(p);
    }

    @Override
    public Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
        return tree.right(p);
    }

    @Override
    public Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> inOrder() {
        return tree.inOrder();
    }

    /**
     * Rotates the tree.
     * @param p the position to rotate around
     */
    protected void rotate(Position<Entry<K, V>> p) {
        tree.rotate(p);
    }

    /**
     * Restructures the tree.
     * @param x the position to check
     * @return the position of the restructured tree
     */
    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        return tree.restructure(x);
    }

    /**
     * Gets the property.
     * @param p the position to check
     * @return the value of the property
     */
    public int getProperty(Position<Entry<K, V>> p) {
        return tree.getProperty(p);
    }

    /**
     * Sets the property.
     * @param p the position to check
     * @param value the value to set
     */
    public void setProperty(Position<Entry<K, V>> p, int value) {
        tree.setProperty(p, value);
    }
}
