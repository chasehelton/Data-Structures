package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

/**
 * The class that implements the Map interface.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * The specific entry in the map.
	 * @author Chase Helton
	 *
	 * @param <K> the key
	 * @param <V> the value
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {

		/** The key in the entry. */
		private K key;
		/** The value in the entry. */
		private V value;

		/**
		 * Constructs the MapEntry given a key and value.
		 * @param key the MapEntry's key
		 * @param value the MapEntry's value
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * Returns the key.
		 * @return the key
		 */
		@Override
		public K getKey() {
			return key;
		}

		/**
		 * Returns the value.
		 * @return the value
		 */
		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Sets the key.
		 * @param key the key to set
		 * @return the key set
		 */
		public K setKey(K key) {
			this.key = key;
			return this.key;
		}

		/**
		 * Sets the value.
		 * @param value the value to set
		 * @return the value set
		 */
		@Override
		public V setValue(V value) {
			V original = this.value;
			this.value = value;
			return original;
		}
	}

	/**
	 * The KeyIterator class.
	 * @author Chase Helton
	 */
	protected class KeyIterator implements Iterator<K> {
		
		/** Iterator for the keys. */
		private Iterator<Entry<K, V>> it;
        
		/**
		 * Constructs the Key Iterator.
		 * @param iterator the iterator for the keys
		 */
        public KeyIterator(Iterator<Entry<K, V>> iterator) {
            it = iterator;
        }
        
        /**
         * Returns true if there is another key, false if not.
         * @return true if there is another key, false if not
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * Returns the next key in the list.
         * @return the next key in the list
         */
        @Override
        public K next() {
            return it.next().getKey();
        }
        
        /**
         * Removes the key.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
	}
	
	/**
	 * The ValueIterator class.
	 * @author Chase Helton
	 */
	protected class ValueIterator implements Iterator<V> {
		
		/** Iterator for the values. */
		private Iterator<Entry<K, V>> it;
        
		/**
		 * Constructs the Value Iterator.
		 * @param iterator the iterator for the value
		 */
        public ValueIterator(Iterator<Entry<K, V>> iterator) {
            it = iterator;
        }
        
        /**
         * Returns true if there is another value, false if not.
         * @return true if there is another value, false if not
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * Returns the next value in the list.
         * @return the next value in the list
         */
        @Override
        public V next() {
            return it.next().getValue();
        }
        
        /**
         * Removes the value.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
	}

	/**
	 * Returns true if the map is empty, otherwise returns false.
	 * @return true if the map is empty, otherwise returns false
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Iterator for the keys.
	 * @return the iterator for the keys
	 */
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator(entrySet().iterator());
	}
	
	/**
	 * Returns the values to iterate through.
	 * @return the values to iterate through
	 */
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}
	
	/**
	 * The ValueIterable class.
	 * @author Chase Helton
	 */
    private class ValueIterable implements Iterable<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator(entrySet().iterator());
        }
    }
	
}