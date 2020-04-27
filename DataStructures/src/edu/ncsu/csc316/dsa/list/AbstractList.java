package edu.ncsu.csc316.dsa.list;

/**
 * Abstract class used by list classes
 * 
 * @author Chase Helton
 * @param <E> the generic type of list
 */
public abstract class AbstractList<E> implements List<E> {
	
	/**
	 * Adds a value to the front of the list.
	 * @param value the value to add
	 */
	@Override
	public void addFirst(E value) {
		add(0, value);
	}
	
	/**
	 * Adds a value to the last of the list.
	 * @param value the value to add
	 */
	@Override
	public void addLast(E value) {
		add(size(), value);
	}

	/**
	 * Checks if the index is valid.
	 * @param index the index to check
	 * @throws IndexOutOfBoundsException if index < 0 or index >= size
	 */
	protected void checkIndex(int index)
	{
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}
	
	/**
	 * Checks the index to make sure if it can be added at.
	 * @param index the index to check
	 * @throws IndexOutOfBoundsException if index < 0 or index > size
	 */
	protected void checkIndexForAdd(int index)
	{
		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}
	
	/**
	 * Returns the first element in the list.
	 * @return the first element in the list
	 */
	@Override
	public E first() {
		return get(0);
	}
	
	/**
	 * Returns true if the list is empty, false otherwise
	 * @return true if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the last element in the list.
	 * @return the last element in the list
	 */
	@Override
	public E last() {
		return get(size() - 1);
	}
	
	/**
	 * Removes the first element in the list.
	 * @return the first element in the list
	 */
	@Override
	public E removeFirst() {
		return remove(0);
	}
	
	/**
	 * Removes the last element in the list.
	 * @return the last element in the list
	 */
	@Override
	public E removeLast() {
		return remove(size() - 1);
	}
}