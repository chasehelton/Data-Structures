package edu.ncsu.csc316.dsa.list.positional;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface for PositionalLinkedList
 * @author Chase Helton
 * @param <E> the generic data type
 */
public interface PositionalList<E> extends Iterable<E> {
	/**
	 * Adds after.
	 * @param p position
	 * @param value value 
	 * @return position
	 */
	Position<E> addAfter(Position<E> p, E value);
	
	/**
	 * Adds before.
	 * @param p position
	 * @param value value
	 * @return position
	 */
	Position<E> addBefore(Position<E> p, E value);
	
	/**
	 * Adds first.
	 * @param value value
	 * @return position
	 */
	Position<E> addFirst(E value);
	
	/**
	 * Adds last.
	 * @param value value
	 * @return position
	 */
	Position<E> addLast(E value);
	
	/**
	 * Returns item in list after.
	 * @param p position
	 * @return position
	 */
	Position<E> after(Position<E> p);
	
	/**
	 * Returns item in list before.
	 * @param p position
	 * @return position
	 */
	Position<E> before(Position<E> p);
	
	/**
	 * Returns first item in list.
	 * @return first item in list
	 */
	Position<E> first();
	
	/**
	 * Returns true if list is empty.
	 * @return true if list is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns last item in list.
	 * @return last item in list
	 */
	Position<E> last();
	
	/**
	 * Returns the positions.
	 * @return the positions
	 */
	Iterable<Position<E>> positions();
	
	/**
	 * Removes the element.
	 * @param p the position of the element
	 * @return the element
	 */
	E remove(Position<E> p);
	
	/**
	 * Sets the value.
	 * @param p position
	 * @param value value to set
	 * @return the changed element
	 */
	E set(Position<E> p, E value);
	
	/**
	 * Returns size of list.
	 * @return size of list
	 */
	int size();
}