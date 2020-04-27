package edu.ncsu.csc316.dsa.queue;

/**
 * AbstractQueue class to provide an isEmpty method.
 * @author Chase Helton
 *
 * @param <E> the generic type of data
 */
public abstract class AbstractQueue<E> implements Queue<E> {

	/**
	 * Returns true if empty, false if not.
	 * @return true if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}