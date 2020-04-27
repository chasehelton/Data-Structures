package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * Queue using an array as its adt.
 * @author Chase Helton
 *
 * @param <E> the generic type of data
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	/** The array of elements. */
	private E[] data;
	/** The front of data. */
	private int front;
	/** The size of data. */
	private int size;
	
	/** The default capacity of the array. */
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * The constructor for the ArrayBasedQueue with an initial capacity.
	 * @param initialCapacity the initial capacity of the array
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity) {
		data = (E[])(new Object[initialCapacity]);
		size = 0;
	}
	
	/**
	 * The default constructor with no parameter.
	 */
	public ArrayBasedQueue(){
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Adds the element to the back of the queue.
	 * @param value the value to add to the queue
	 */
	@Override
	public void enqueue(E value) {
		ensureCapacity(data.length);
		int avail = (front + size) % data.length;
		data[avail] = value;
		size++;
	}

	/**
     * Removes and returns the element at the front of the queue.
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
	@Override
	public E dequeue() {
		if (size == 0) throw new NoSuchElementException();
		else {
			E ele = data[front];
			data[front] = null;
			front = (front + 1) % data.length;
			size--;
			return ele;
		}
	}

	/**
     * Returns, but does not remove, the element at the front of the queue.
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
	@Override
	public E front() {
		if (isEmpty()) throw new NoSuchElementException();
		return data[front];
	}

	/**
     * Returns the number of elements in the queue.
     * @return the number of elements in the queue
     */
	@Override
	public int size() {
		return size;
	}
	
	private void ensureCapacity(int cap) {
		if (size == cap) {
			@SuppressWarnings("unchecked")
			E[] q = (E[]) (new Object[cap * 2]);
			for (int i = 0; i < size; i++) {
				q[i] = data[front];
				front = (front + 1);
			}
			front = 0;
			data = q;
		}
	}
}