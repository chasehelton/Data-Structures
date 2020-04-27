package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * AVLTreeMap class.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class AVLTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Constructs an AVLTreeMap.
	 */
	public AVLTreeMap() {
		super(null);
	}

	/**
	 * Constructs an AVLTreeMap with compare.
	 * @param compare a comparator
	 */
	public AVLTreeMap(Comparator<K> compare) {
		super(compare);
	}

    /**
     * Helper method to trace upward from position p checking to
     * make sure each node on the path is balanced. If a rebalance 
     * is necessary, trigger a trinode resturcturing.
     * @param p the position to rebalance
     */
	private void rebalance(Position<Entry<K, V>> p) {
		int oldHeight = 0;
		int newHeight = 0;
		do {
			oldHeight = getProperty(p);
			if (!isBalanced(p)) {
				Position<Entry<K, V>> child = tallerChild(p);
				Position<Entry<K, V>> grandchild = tallerChild(child);
				p = restructure(grandchild);
				recomputeHeight(left(p));
				recomputeHeight(right(p));
			}
			recomputeHeight(p);
			newHeight = getProperty(p);
			p = parent(p);
		} while (oldHeight != newHeight && p != null);
	}
	
    /**
     * Returns the child of p that has the greater height.
     * If both children have the same height, use the child 
     * that matches the parent's orientation	
     * @param p the position to check
     * @return the taller child
     */
	private Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
		if (getProperty(left(p)) < getProperty(right(p))) return right(p);
		if (getProperty(left(p)) > getProperty(right(p))) return left(p);
		if (isRoot(p)) return left(p);
		if (p == left(parent(p))) return left(p);
		else return right(p);
	}	

	/**
	 * Returns true if it is balanced.
	 * @param p the position to check
	 * @return true if it is balanced
	 */
	private boolean isBalanced(Position<Entry<K, V>> p) {
		return Math.abs(getProperty(left(p)) - getProperty(right(p))) <= 1;
	}
	
	/**
	 * Recomputes the height.
	 * @param p the position to check
	 */
	private void recomputeHeight(Position<Entry<K, V>> p) {
		int h = 1 + Math.max(getProperty(left(p)), getProperty(right(p)));
		setProperty(p, h);
	}

	/**
	 * Checks if insert.
	 * @param node the node to check
	 */
	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		rebalance(node);
	}

	/**
	 * Checks if delete.
	 * @param node the node to check
	 */
	@Override
	protected void actionOnDelete(Position<Entry<K, V>> node) {
		if(!isRoot(node))
		{
			rebalance(parent(node));
		}
	}
}
