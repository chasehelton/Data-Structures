package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Heap Priority Queue class.
 * @author Chase Helton
 *
 * @param <K> the key
 * @param <V> the value
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

    /** List to use. */
	protected ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructs a HeapPriorityQueue with a comparator.
	 * @param comparator the comparator to use
	 */
	public HeapPriorityQueue(Comparator<K> comparator) {
		super(comparator);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * Constructs a HeapPriorityQueue without a comparator.
	 */
	public HeapPriorityQueue() {
		this(null);
	}

	@Override
	public Entry<K, V> insert(K key, V value) {
		Entry<K, V> temp = createEntry(key, value);
		list.addLast(temp);
		upHeap(list.size() - 1);
		return temp;
	}

	@Override
	public Entry<K, V> min() {
		if (list.isEmpty()) {
			return null;
		}
		return list.first();
	}

	@Override
	public Entry<K, V> deleteMin() {
		if (list.isEmpty()) {
			return null;
		}
		Entry<K, V> temp = list.first();
		swap(0, list.size() - 1);
		list.removeLast();
		downHeap(0);
		return temp;
	}

	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Moves up the heap when triggered.
	 * @param index the index to start at
	 */
	protected void upHeap(int index) {
		/*while (index > 0) {
			int p = parent(index);
			if (compare(list.get(index).getKey(), list.get(p).getKey()) >= 0) break;
			swap(index, p);
			index = p;
		}*/
		if (index > 0) {
			int parentIndex = (index - 1) / 2;
			if (list.get(parentIndex).getKey().compareTo(list.get(index).getKey()) > 0) {
				swap(parentIndex, index);
				upHeap(parentIndex);
			}
		}
	}

	/**
	 * Swaps two nodes.
	 * @param index1 the first index to swap
	 * @param index2 the second index to swap
	 */
	protected void swap(int index1, int index2) {
		Entry<K, V> temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	/**
	 * Moves down the heap when triggered.
	 * @param index the index to start at
	 */
	protected void downHeap(int index) {
		/*while (hasLeft(index)) {
			int left = left(index);
			int smallChildIndex = left;
			if (hasRight(index)) {
				int right = right(index);
				if (compare(list.get(left).getKey(), list.get(right).getKey()) > 0) {
					smallChildIndex = right;
				}
			}
			if (compare(list.get(smallChildIndex).getKey(), list.get(index).getKey()) >= 0) {
				break;
			}
			swap(index, smallChildIndex);
			index = smallChildIndex;
		}*/
		int leftIndex = 2 * index + 1;
		int rightIndex = 2 * index + 2;
		if (leftIndex >= list.size()) {
			return;
		}
		int smallestChildIndex = leftIndex;
		if (rightIndex < list.size() && list.get(rightIndex).getKey().compareTo(list.get(leftIndex).getKey()) < 0) {
			smallestChildIndex = rightIndex;
		}
		if (list.get(smallestChildIndex).getKey().compareTo(list.get(index).getKey()) >= 0) {
			return;
		}
		swap(smallestChildIndex, index);
		downHeap(smallestChildIndex);
	}

	/**
	 * Returns parent "index".
	 * @param index the index to check the parent of
	 * @return the parent's index
	 */
	//protected int parent(int index) {
	//	return (index - 1) / 2;
	//}

	/**
	 * Returns the left index.
	 * @param index the index to check the left of
	 * @return the left index
	 */
//	protected int left(int index) {
//		return 2 * index + 1;
//	}

	/**
	 * Returns the right index.
	 * @param index the index to check the right of
	 * @return the right index
	 */
//	protected int right(int index) {
//		return 2 * index + 2;
//	}

	/**
	 * Returns true if the index has a left node.
	 * @param index the index to check
	 * @return true if the index has a left node
	 */
//	protected boolean hasLeft(int index) {
//		return left(index) < list.size();
//	}

	/**
	 * Returns true if the index has a right node.
	 * @param index the index to check
	 * @return true if the index has a right node
	 */
//	protected boolean hasRight(int index) {
//		return right(index) < list.size();
//	}
}