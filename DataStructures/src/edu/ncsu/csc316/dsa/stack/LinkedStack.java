package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * Linked Stack class extending the AbstractStack
 * @author Chase Helton
 *
 * @param <E> the generic type of data
 */
public class LinkedStack<E> extends AbstractStack<E> {
	
	/** The list for the stack. */
	private SinglyLinkedList<E> list;
	
	/**
	 * The constructor for a LinkedStack.
	 */
	public LinkedStack() {
		list = new SinglyLinkedList<E>();
	}

	/**
	 * Adds the element to the top of the stack.
	 * @param value the value to push
	 */
	@Override
	public void push(E value) {
		list.addFirst(value);
	}

	/**
     * Removes and returns the element at the top of the stack.
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
	@Override
	public E pop() {
		if (list.isEmpty()) throw new EmptyStackException();
		E ele = list.first();
		list.removeFirst();
		return ele;
	}

	/**
     * Returns, but does not remove, the element at the top of the stack.
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
	@Override
	public E top() {
		if (list.isEmpty()) throw new EmptyStackException();
		return list.first();
	}

	/**
     * Returns the number of elements in the stack.
     * @return the number of elements in the stack
     */
	@Override
	public int size() {
		return list.size();
	}

}
