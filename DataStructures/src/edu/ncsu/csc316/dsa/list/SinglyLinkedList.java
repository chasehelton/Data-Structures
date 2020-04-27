package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Creates a SinglyLinkedList ADT.
 * @author Chase Helton
 * @author Bryce Junkins
 * @param <E> the generic data type
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/** The front of the list. */
	private LinkedListNode<E> front;
	/** The back of the list. */
	private LinkedListNode<E> tail;
	/** The size of the list. */
	private int size;

	/**
	 * The constructor for a SinglyLinkedList.
	 */
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	/**
	 * Returns the last element.
	 * @return the last element
	 */
	@Override
    public E last() {
        if(isEmpty()) return null;
		return tail.getElement();
    }
    
	/**
	 * Adds to the last of the list.
	 * @param value the value to add
	 */
    @Override
    public void addLast(E value) {
    	if (size == 0) {
    		front.setNext(new LinkedListNode<E>(value));
    		tail = front.getNext();
    	} else {
    		tail.setNext(new LinkedListNode<E>(value));
    		tail = tail.getNext();
    	}
        size++;
    }
	
    /**
     * Adds a value to an index.
     * @param index the index to add at
     * @param value the value to add
     */
	@Override
	public void add(int index, E value) {
		checkIndexForAdd(index);
		if (index == 0) {
			front.setNext(new LinkedListNode<E>(value, front.getNext()));
			if (size == 0) tail = front.getNext();
		}
		else if (index == size) {
			tail.setNext(new LinkedListNode<E>(value));
			tail = tail.getNext();
		}
		else {
			LinkedListNode<E> current = front.getNext();
			for (int i = 0; i < index - 1; i++) {
				current = current.getNext();
			}
			current.setNext(new LinkedListNode<E>(value, current.getNext()));
		}
		size++;
	}

	/**
	 * Returns the element at an index.
	 * @param index the index at which to return the element
	 * @return the element at an index
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		if (isEmpty()) return null;
		if (index == size - 1) {
			return last();
		}
		if (index == 0) {
			return front.getNext().getElement();
		}
		else {
			LinkedListNode<E> current = front.getNext();
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			return current.getElement();
		}
	}

	/**
	 * Removes the element at an index.
	 * @param index the index at which to remove the element
	 * @return the element at the index
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);
		LinkedListNode<E> temp = front.getNext();
		E data = null;
		if (index == 0) {
			data = temp.getElement();
			front.setNext(temp.getNext());
		} else {
			for (int i = 0; i < index - 1; i++) temp = temp.getNext();
			data = temp.getNext().getElement();
			if (index == size - 1) {
				temp.setNext(null);
				tail = temp;
			} else temp.setNext(temp.next.getNext());
		}
		size--;
		return data;
	}

	/**
	 * Sets the value at an index
	 * @param index the index to set
	 * @param value the value to set
	 * @return the changed value
	 */
	@Override
	public E set(int index, E value) {
	    checkIndex(index); 
		LinkedListNode<E> temp = front.getNext();
		for (int i = 0; i < index; i++) {
			temp = temp.getNext();
		}
		E data = temp.getElement();
		temp.setData(value);
		return data;
	}

	/**
	 * Returns the size of the list
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
    /**
     * Returns the iterator for the list.
     * @return the iterator for the list
     */
	@Override
	public Iterator<E> iterator() {
	    return new ElementIterator(front.getNext());
	}
	
	/**
	 * Private inner class to provide an iterator for the list.
	 * @author Chase Helton
	 */
	private class ElementIterator implements Iterator<E> {
	    /** Keep track of the next node that will be processed. */
	    private LinkedListNode<E> current;
	    /** Keep track of the node that was processed on the last call to 'next'. */
	    private LinkedListNode<E> previous;
	    /** 
	     * Keep track of the previous-previous node that was processed 
	     * so that we can update 'next' links when removing.
	     */
	    private LinkedListNode<E> previousPrevious;
	    
	    /** Determines if the iterator can remove or not. */
	    private boolean removeOK;

	    /**
	     * Constructor for the iterator.
	     * @param start the start of the iterating
	     */
	    public ElementIterator(LinkedListNode<E> start) {
	        current = start;
	        removeOK = false;
            
	        if (start == null) return;
	    	
	    	previous = SinglyLinkedList.this.front;
	    	previousPrevious = SinglyLinkedList.this.front;
	    	if (!current.equals(previous.getNext())) {
	    		previous = previous.getNext();
	    		while (!previous.getNext().equals(current)) {
		    		previous = previous.getNext();
		    		previousPrevious = previousPrevious.getNext();
		    	}
	    	}
	    }

	    /**
	     * Returns true if the iterator has a next item.
	     */
	    public boolean hasNext() {
	        return current != null;
	    }

	    /**
         * Next method for ElementIterator.
         * @return the element removed
         */
	    public E next() {
	    	if (!hasNext()) throw new NoSuchElementException();
	    	E rem = current.getElement();
	    	current = current.getNext();
	    	previous = previous.getNext();
	    	if (!previousPrevious.getNext().equals(previous)) {
	    		previousPrevious = previousPrevious.getNext();
	    	}
	    	removeOK = true;
	        return rem;
	    }
	    
	    /**
	     * Removes the current item from the list.
	     * @throws IllegalStateException if removeOK is false
	     */
	    public void remove() {
	        if (removeOK) {
	        	previousPrevious.setNext(previous.getNext());
	        	previous = previousPrevious;
	        	removeOK = false;
	        	size--;
	        } else throw new IllegalStateException();
	    }
	}
	
	/**
	 * The Node within the SinglyLinkedList.
	 * @author Chase Helton
	 * @param <E> the generic type of data
	 */
	private static class LinkedListNode<E> {
	    /** The specific data of the node. */
		private E data;
		/** The succeeding node. */
	    private LinkedListNode<E> next;
	    
	    /**
	     * The constructor for the LinkedListNode with just the data provided
	     * @param data the data to set
	     */
	    public LinkedListNode(E data) {
	        this.data = data;
	    }
	    
	    /**
	     * The constructor for the LinkedListNode with data and the next node
	     * @param data the data to set
	     * @param next the next node
	     */
	    public LinkedListNode(E data, LinkedListNode<E> next) {
	    	this.data = data;
	    	this.next = next;
	    }
	     
	    /**
	     * Returns the data at the node.
	     * @return the data at the node
	     */
	    public E getElement() {
	    	return data;
	    }
	     
	    /**
	     * Sets the data at the node.
	     * param data the data at the node
	     */
	    public void setData(E data) {
	    	this.data = data;
	    }
	     
	    /**
	     * Gets the next node.
	     * @return the next node
	     */
	    public LinkedListNode<E> getNext() {
	    	return next;
	    }
	     
	    /**
	     * Sets the next node.
	     * @param next the next node
	     */
	    public void setNext(LinkedListNode<E> next) {
	    	this.next = next;
	    }
    }
	
}
