package edu.ncsu.csc316.dsa.stack;

/**
 * Abstract class for the stacks.
 * @author Chase Helton
 *
 * @param <E> the generic type of data
 */
public abstract class AbstractStack<E> implements Stack<E> {
	
	/**
	 * Common abstract class for stacks to check if the size is 0.
	 * @return true if the size is 0, false if not
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}