package edu.ncsu.csc316.dsa.stack;

/**
 * The Generic Stack Interface.
 * @author Chase Helton
 *
 * @param <E> the generic list type
 */
public interface Stack<E> {

	/**
	 * Adds the element to the top of the stack.
	 * @param value the value to push
	 */
    void push(E value);
    
    /**
     * Removes and returns the element at the top of the stack.
     * @return the element at the top of the stack
     */
    E pop();
    
    /**
     * Returns, but does not remove, the element at the top of the stack.
     * @return the element at the top of the stack
     */
    E top();
    
    /**
     * Returns the number of elements in the stack.
     * @return the number of elements in the stack
     */
    int size();
    
    /**
     * Returns true if the stack is empty; otherwise, returns false.
     * @return true if the stack is empty; otherwise, returns false
     */
    boolean isEmpty();
}