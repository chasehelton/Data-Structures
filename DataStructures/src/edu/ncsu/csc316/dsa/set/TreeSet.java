package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap;

/**
 * Class for the TreeSet.
 * @author Chase Helton
 *
 * @param <E> the generic type
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {

	/** The Tree to use. */
    private Map<E, E> tree;
    
    /**
     * Constructs a TreeSet.
     */
    public TreeSet() {
        tree = new BinarySearchTreeMap<E, E>();
    }
    
    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public void add(E value) {
        tree.put(value, value);
    }

    @Override
    public boolean contains(E value) {
        return (tree.get(value) != null);
    }

    @Override
    public E remove(E value) {
        return tree.remove(value);
    }

    @Override
    public int size() {
        return tree.size();
    }
}