package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * Heap Adaptable Priority Queue class.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<K, V>
		implements AdaptablePriorityQueue<K, V> {

    /**
     * AdaptablePQEntry class.
     * @author Chase Helton
     *
     * @param <K> the key
     * @param <V> the value
     */
	public static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {

		/** Index of the entry. */
		private int index;

		/**
		 * Constructor for the AdaptablePQEntry.
		 * @param key the key to set
		 * @param value the value to set
		 * @param index the index to set
		 */
		public AdaptablePQEntry(K key, V value, int index) {
			super(key, value);
			setIndex(index);
		}

		/**
		 * Returns the index.
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * Sets the index.
		 * @param index the index to set
		 */
		public void setIndex(int index) {
			this.index = index;
		}
	}
	
	/**
	 * Constructs a HeapAdaptablePriorityQueue with a comparator.
	 * @param c the comparator to set
	 */
	public HeapAdaptablePriorityQueue(Comparator<K> c) {
		super(c);
	}
	
	/**
	 * Constructs a HeapAdaptablePriorityQueue.
	 */
	public HeapAdaptablePriorityQueue() {
		this(null);
	}
	
	/**
	 * Factory method for creating a new adaptable PQ entry.
	 */
	@Override
	protected AdaptablePQEntry<K, V> createEntry(K key, V value) {
		// A new adaptable PQ Entry added to the heap will be at index size()
		AdaptablePQEntry<K, V> temp = new AdaptablePQEntry<K, V>(key, value, size());
		return temp;
	}
	
    // Make sure the entry is a valid Adaptable PQ Entry and is located within the heap structure	
	private AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
		if(!(entry instanceof AdaptablePQEntry)){
			throw new IllegalArgumentException("Entry is not a valid adaptable priority queue entry.");
		}
		AdaptablePQEntry<K, V> temp = (AdaptablePQEntry<K, V>)entry;
		if(temp.getIndex() >= list.size() || list.get(temp.getIndex()) != temp) {
			throw new IllegalArgumentException("Invalid Adaptable PQ Entry.");
		}
		return temp;
	}
	
	@Override
	public void swap(int index1, int index2) {
		// Delegate to the super class swap method
		super.swap(index1, index2);
		// But then update the index of each entry so that they remain location-aware
		((AdaptablePQEntry<K, V>)list.get(index1)).setIndex(index1);
		((AdaptablePQEntry<K, V>)list.get(index2)).setIndex(index2);
	}

	@Override
	public void remove(Entry<K, V> entry) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		int j = temp.getIndex();
		if (j == list.size() - 1) list.remove(list.size() - 1);
		else {
			swap(j, list.size() - 1);
			list.remove(list.size() - 1);
			bubble(j);
		}
	}
	
	private void bubble(int index) {
		/*if(index > 0 && compare(list.get(index).getKey(), list.get(parent(index)).getKey()) < 0) {
			upHeap(index);
		} else {
			downHeap(index);
		}*/
		int parentIndex = (index - 1) / 2;
		if (list.get(index).getKey().compareTo(list.get(parentIndex).getKey()) < 0) {
			upHeap(index);
		} else {
			downHeap(index);
		}
	}

	@Override
	public void replaceKey(Entry<K, V> entry, K key) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		temp.setKey(key);
		bubble(temp.getIndex());
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		temp.setValue(value);
	}
}