package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * SplayTreeMap class.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Construct a SplayTreeMap.
	 */
	public SplayTreeMap() {
		super(null);
	}

	/**
	 * Construct a SplayTreeMap with comparator.
	 * @param compare the comparator
	 */
	public SplayTreeMap(Comparator<K> compare) {
		super(compare);
	}

	/**
	 * Splays the tree.
	 * @param p the position to splay
	 */
	private void splay(Position<Entry<K, V>> p) {
		while(!isRoot(p)) {
			Position<Entry<K, V>> parent = parent(p);
			Position<Entry<K, V>> grandparent = parent(parent);
			if (grandparent == null) {
				rotate(p);
			} else if ((parent == left(grandparent)) == (p == left(parent))) {
				rotate(parent);
				rotate(p);
			} else {
				rotate(p);
				rotate(p);
			}
		}
	}

	/**
	 * Checks when accessed.
	 * @param p the position to check
	 */
	@Override
	protected void actionOnAccess(Position<Entry<K, V>> p) {
		// If p is a dummy/sentinel node, move to the parent
		if(isLeaf(p)) {
			p = parent(p);
		}
		if(p != null) {
			splay(p);
		}
	}

	/**
	 * Checks when inserted.
	 * @param node the position to check
	 */
	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		splay(node);
	}

	/**
	 * Checks when deleted.
	 * @param p the position to check
	 */
	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if(!isRoot(p)) {
			splay(parent(p));
		}
	}
}