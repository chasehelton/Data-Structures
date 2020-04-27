package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * Implemntation for the SkipListMap type.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {
	
	/** The Random value used to see if it should be added. */
	private Random coinToss;
	/** The starting entry. */
	private SkipListEntry<K, V> start;
	/** The size of the list. */
	private int size;
	/** The height of the list. */
	private int height;

	/**
	 * Constructs a SkipListMap with no comparator.
	 */
	public SkipListMap() {
		this(null);
	}

	/**
	 * Constructs a SkipListMap with a comparator.
	 * @param compare the comparator to use
	 */
	public SkipListMap(Comparator<K> compare) {
		super(compare);
		coinToss = new Random();
		// Create a dummy head node for the left "-INFINITY" sentinel tower
		start = new SkipListEntry<K, V>(null, null);
		// Create a dummy tail node for the right "+INFINITY" sentinel tower		
		start.setNext(new SkipListEntry<K, V>(null, null));
		// Set the +INFINITY tower's previous to be the "start" node
		start.getNext().setPrevious(start);
		size = 0;
		height = 0;
	}
	
	/**
	 * Private inner class to create an entry in the list.
	 * @author Chase Helton
	 *
	 * @param <K> the key
	 * @param <V> the value
	 */
	private static class SkipListEntry<K, V> extends MapEntry<K, V> {

		/** The above entry. */
        private SkipListEntry<K, V> above;
        /** The below entry. */
        private SkipListEntry<K, V> below;
        /** The previous entry. */
        private SkipListEntry<K, V> prev;
        /** The next entry. */
        private SkipListEntry<K, V> next;

        /**
         * Constructs the SkipListEntry.
         * @param key the key
         * @param value the value
         */
        public SkipListEntry(K key, V value) {
            super(key, value);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }
        
        /**
         * Returns the below entry.
         * @return the below entry.
         */
        /*public SkipListEntry<K, V> getBelow() {
            return below;
        }
        
        /**
         * Returns the next entry.
         * @return the next entry.
         */
        public SkipListEntry<K, V> getNext() {
            return next;
        }
        
        /**
         * Returns the prev entry.
         * @return the prev entry.
         */
        public SkipListEntry<K, V> getPrevious() {
            return prev;
        }
        
        /**
         * Returns the above entry.
         * @return the above entry.
         */
        public SkipListEntry<K, V> getAbove() {
            return above;
        }
        
        /**
         * Sets the below entry.
         * @param down the entry below to set.
         */
        public void setBelow(SkipListEntry<K, V> down) {
            this.below = down;
        }
        
        /**
         * Sets the next entry.
         * @param next the entry next to set.
         */
        public void setNext(SkipListEntry<K, V> next) {
            this.next = next;
        }
        
        /**
         * Sets the previous entry.
         * @param prev the entry previous to set.
         */
        public void setPrevious(SkipListEntry<K, V> prev) {
            this.prev = prev;
        }
        
        /**
         * Sets the above entry.
         * @param up the entry above to set.
         */
        public void setAbove(SkipListEntry<K, V> up) {
            this.above = up;
        }
    }

    /**
     * Helper method to determine if an entry is one of the sentinel
     * -INFINITY or +INFINITY nodes (containing a null key).
     * @param entry the entry to check
     * @return true if the entry is sentinel, false if not
     */
	private boolean isSentinel(SkipListEntry<K, V> entry) {
		return entry.getKey() == null;
	}	

	/**
	 * Private look up method to help with get, put, and remove.
	 * @param key the key to look up
	 * @return the entry with the given key
	 */
    private SkipListEntry<K, V> lookUp(K key) {
        SkipListEntry<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

    /**
     * Gets the key.
     * @param key the key to get
     * @return the value of the key
     */
	@Override
	public V get(K key) {
		SkipListEntry<K, V> temp = lookUp(key);
		if (temp == null) {
			return null;
		}
		if (compare(key, temp.getKey()) == 0) {
			return temp.getValue();
		} else {
			return null;
		}
	}

	/**
	 * Inserts the key and value after the above node.
	 * @param prev the previous position
	 * @param down the below position
	 * @param key the key to add
	 * @param value the value to add
	 * @return the position that was added at
	 */
	private SkipListEntry<K, V> insertAfterAbove(SkipListEntry<K, V> prev, SkipListEntry<K, V> down, K key, V value) {
		SkipListEntry<K, V> newEntry = new SkipListEntry<K, V>(key, value);
		newEntry.setBelow(down);
		newEntry.setPrevious(prev);
		if (prev != null) {
			newEntry.setNext(prev.getNext());
			newEntry.getPrevious().setNext(newEntry);
		}
		if (newEntry.getNext() != null) {
			newEntry.getNext().setPrevious(newEntry);
		}
		if (down != null) {
			down.setAbove(newEntry);
		}
		return newEntry;
	}

	/**
	 * Puts the key and value in the map.
	 * @param key the key to add
	 * @param value the value to add
	 * @return the value of the key added
	 */
	@Override
	public V put(K key, V value) {
		SkipListEntry<K, V> temp = lookUp(key);
		if (compare(key, temp.getKey()) == 0) {
			V originalValue = temp.getValue();
			while (temp != null) {
				temp.setValue(value);
				temp = temp.getAbove();
			}
			return originalValue;
		}
		SkipListEntry<K, V> q = null;
		int currentLevel = -1;
		do {
			currentLevel++;
			if (currentLevel >= height) {
				height++;
				SkipListEntry<K, V> tail = start.getNext();
				start = insertAfterAbove(null, start, null, null);
				insertAfterAbove(start, tail, null, null);
			}
			q = insertAfterAbove(temp, q, key, value);
			while (temp.getAbove() == null) {
				temp = temp.getPrevious();
			}
			temp = temp.getAbove();
			coinToss = new Random();
		} while (coinToss.nextBoolean());
		size++;
		return null;
	}
	
	/**
	 * Removes the key from the map.
	 * @param key the key to remove
	 * @return the value of the key removed
	 */
    @Override
    public V remove(K key) {
        SkipListEntry<K, V> temp = lookUp(key);
        if (temp.getKey() != key) return null;
        V val = temp.getValue();
        while (temp != null) {
        	temp.getPrevious().setNext(temp.getNext());
        	temp.getNext().setPrevious(temp.getPrevious());
        	temp = temp.getAbove();
        }
        size--;
        return val;
    }	

    /**
     * Returns the size of the list.
     * @return the size of the list
     */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Returns the entry set of iterable entries.
	 * @return the entry set of iterable entries
	 */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
        SkipListEntry<K, V> current = start;
        while(current.below != null){
            current = current.below;
        }
        current = current.next;
        while(!isSentinel(current)) {
            set.addLast(current);
            current = current.next;
        }
        return set;
    }
	
    /**
     * Converts the map to a String.
     * @return the map as a String
     */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		SkipListEntry<K, V> cursor = start;
		while( cursor.below != null) {
			cursor = cursor.below;
		}
		cursor = cursor.next;
		while(cursor != null && cursor.getKey() != null) {
			sb.append(cursor.getKey());
			if(!isSentinel(cursor.next)) {
				sb.append(", ");
			}
			cursor = cursor.next;
		}
		sb.append("]");
		
		return sb.toString();
	}
}