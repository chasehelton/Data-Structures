package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * LinkedList version of the UnsortedMap.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** The list being used by the class. */
	private PositionalList<Entry<K, V>> list;
	
	/**
	 * Constructs the UnorderedLinkedMap object.
	 */
	public UnorderedLinkedMap() {
		this.list = new PositionalLinkedList<Entry<K, V>>();
	}
	
	/**
	 * Looks up the key in the list.
	 * @param key the key to find
	 * @return the position of the key
	 */
	private Position<Entry<K, V>> lookUp(K key)
	{
		try {
			Position<Entry<K, V>> pos = list.first();
			Iterator<Entry<K, V>> iterator = list.iterator();
			KeyIterator it = new KeyIterator(iterator);
			while(it.hasNext()) {
				if (it.next() == key) {
					return pos;
				}
				pos = list.after(pos);
			}
			return null;
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * Returns the value of the key.
	 * @param key the key to get
	 * @return the value of the key
	 */
	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) return null;
		else {
			V val = p.getElement().getValue();
			moveToFront(p);
			return val;
		}
		
	}
	
	/**
	 * Moves the position to the front when called.
	 * @param position the position to move
	 */
	private void moveToFront(Position<Entry<K, V>> position) {
		list.addFirst(position.getElement());
		list.remove(position);
	}

	/**
	 * Puts the value in the list given a key.
	 * @param key the key to put in
	 * @param value the value to add
	 * @return the value of the key moved to the front
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public V put(K key, V value) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) {
			list.addFirst(new MapEntry(key, value));
			return null;
		} else {
			V val = p.getElement().getValue();
			moveToFront(p);
			return val;
		}
	}
	
	/**
	 * Removes the value at a key.
	 * @param key the key to look for
	 * @return the value of the key removed
	 */
	@Override
	public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if (p == null) return null;
       else {
    	   return list.remove(p).getValue();
       }
	}
	
	/**
	 * Returns the size of the list.
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return list.size();
	}
	
	/**
	 * The EntrySet of the list.
	 * @return the set of entries as Iterator values
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		PositionalList<Entry<K, V>> set = new PositionalLinkedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
	}
	
	/**
	 * Returns the list as a String to test.
	 * @return the list as a String to test
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while(it.hasNext()) {
			sb.append(it.next().getKey());
			if(it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}