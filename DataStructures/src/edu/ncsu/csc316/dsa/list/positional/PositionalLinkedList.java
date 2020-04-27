package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * Functions as a DoublyLinkedList using positions.
 * Some code was taken from the textbook regarding method implementation.
 * @author Chase Helton
 * @param <E> the generic type of data
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/** The front of the list. */
	private PositionalNode<E> front;
	/** The end of the list. */
	private PositionalNode<E> tail;
	/** The size of the list. */
	private int size;

	/**
	 * The constructor for the PositionalLinkedList.
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null, null, null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}
	
	/**
	 * Iterates over the elements in PositionalLinkedList
	 * @author Chase Helton
	 */
    private class ElementIterator implements Iterator<E> {

    	/** The iterator for the elements. */
        private Iterator<Position<E>> it;

        /**
         * Constructor for the ElementIterator.
         * @param start the start of the iterating
         */
        public ElementIterator(PositionalNode<E> start) {
            it = new PositionIterator(start);
        }

        /**
         * HasNext method for ElementIterator.
         * @return true if there is a next element
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * Next method for ElementIterator.
         * @return the element removed
         */
        @Override
        public E next() {
            return it.next().getElement();
        }

        /**
         * Remove method for ElementIterator.
         */
        @Override
        public void remove() {
            it.remove();
        }
    }
	
	/**
	 * Serves as the iterator for the PositionalLinkedList
	 * @author Chase Helton
	 */
    private class PositionIterator implements Iterator<Position<E>> {

    	/** Position of the current element. */
        private Position<E> current;
        /** Boolean to decide if it is okay to remove an element. */
        private boolean removeOK;

        /**
         * The constructor for the PositionIterator.
         * @param start the position to start at
         */
        public PositionIterator(PositionalNode<E> start) {
            current = start;
            removeOK = false;
        }

        /**
         * Returns true if the iterator has a next element.
         * @return true if the iterator has a next element
         */
        @Override
        public boolean hasNext() {
            if (current == null || current == tail) return false;
            return true;
        }

        /**
         * Returns the position of the next element.
         * @return the position of the next element
         * @throws NoSuchElementException if the list is empty
         */
        @Override
        public Position<E> next() {
            if (!hasNext()) throw new NoSuchElementException();
            Position<E> temp = current;
            current = validate(current).getNext();
            removeOK = true;
            return temp;
        }

        /**
         * Removes the element.
         * @throws IllegalStateException if removeOK is false
         */
        @Override
        public void remove() {
            if (removeOK) {
            	PositionalLinkedList.this.remove(validate(current).getPrevious());
            	removeOK = false;
            } else throw new IllegalStateException();
        }
    }

	/**
	 * Serves as the nested static class to provide nodes for the linked list
	 * @author Chase Helton
	 * @param <E> the generic type
	 */
    private static class PositionalNode<E> implements Position<E> {

    	/** Reference to the element stored at this node. */
        private E element;
        /** Reference to the subsequent node in the list. */
        private PositionalNode<E> next;
        /** Reference to the previous node in the list. */
        private PositionalNode<E> previous;

        /**
    	 * Constructor with only a value.
    	 * @param value the value to set the node
    	 */
//        private PositionalNode(E value) {
//            this(value, null);
//        }

        /**
    	 * Constructor with a value and next node.
    	 * @param value the value to set the node
    	 * @param next the next node
    	 */
//        private PositionalNode(E value, PositionalNode<E> next) {
//            this(value, next, null);
//        }

        /**
    	 * Constructor with a value, next node, and previous node.
    	 * @param value the value to set the node
    	 * @param next the next node
    	 * @param prev the previous node
    	 */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            this.element = value;
            this.next = next;
            this.previous = prev;
        }

        /**
    	 * Sets the previous node.
    	 * @param prev the node to set
    	 */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
    	 * Returns the previous node.
    	 * @return the previous node
    	 */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
    	 * Sets the next node.
    	 * @param next the node to set
    	 */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
    	 * Returns the next node.
    	 * @return the next node
    	 */
        public PositionalNode<E> getNext() {
            return next;
        }

        /**
    	 * Returns the element at the node.
    	 * @return the element at the node
    	 */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
    	 * Sets the previous node.
    	 * @param prev the node to set
    	 */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Private class to help with positions.
     * @author Chase Helton
     */
    private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator(front.getNext());
        }
    }
    
    /**
	 * Returns a new instance of the PositionIterable iterator.
	 * @return a new instance of the PositionIterable iterator
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

    /**
	 * The iterator for the PositionalLinkedList
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(front.getNext());
	}
	

    /**
	 * Checks to make sure the position is valid.
	 * @param p the position to check
	 */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
	
	/**
	 * Adds a value between a next and previous node.
	 * @param value the value to add
	 * @param next the next node
	 * @param prev the previous node
	 * @return the position of the new element
	 */
	private Position<E> addBetween(E value, PositionalNode<E> next, PositionalNode<E> prev) {
        PositionalNode<E> p = new PositionalNode<E>(value, next, prev);
        prev.setNext(validate(p));
        p.setNext(validate(next));
        p.setPrevious(validate(prev));
        next.setPrevious(validate(p));
        size++;
        return p;
    }

	/**
	 * Inserts a new element in the list, just after position p
	 * @param p the position to add after
	 * @param value the value to add
	 * @return the position of the new element
	 * @throws IllegalArgumentException is validate(p) doesn't work
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E value) {
		PositionalNode<E> pN = validate(p);
		return addBetween(value, pN.getNext(), pN);
	}

	/**
	 * Inserts a new element in the list, just before position p
	 * @param p the position to add before
	 * @param value the value to add
	 * @return the position of the new element
	 * @throws IllegalArgumentException is validate(p) doesn't work
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E value) {
		PositionalNode<E> pN = validate(p);
		return addBetween(value, pN, pN.getPrevious());
	}

	/**
	 * Inserts a new element value at the front of the list
	 * @param value the value to add to the front
	 * @return the position of the new element
	 */
	@Override
	public Position<E> addFirst(E value) {
		return addBetween(value, front.getNext(), front);
	}

	/**
	 * Inserts a new element value at the end of the list
	 * @param value the value to add to the end
	 * @return the position of the new element
	 */
	@Override
	public Position<E> addLast(E value) {
		return addBetween(value, tail, tail.getPrevious());
	}

	/**
	 * Returns the position of the element immediately after the position p
	 * @param p the position to check what's after it
	 * @return the position directly after p
	 * @throws NoSuchElementException if no element after
	 * @throws IllegalArgumentException if validate(p) doesn't work
	 */
	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> pN = validate(p);
		if (pN.getNext() == null) throw new NoSuchElementException();
		return pN.getNext();
	}

	/**
	 * Returns the position of the element immediately before the position p
	 * @param p the position to check what's before it
	 * @return the position directly before p
	 * @throws NoSuchElementException if no element before
	 * @throws IllegalArgumentException if validate(p) doesn't work
	 */
	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> pN = validate(p);
		if (pN.getPrevious() == null) throw new NoSuchElementException();
		return pN.getPrevious();
	}

	/**
	 * Returns the position of the first element of the list.
	 * @return the position of the first element of the list
	 * @throws NoSuchElementException if list is empty
	 */
	@Override
	public Position<E> first() {
		if (isEmpty()) throw new NoSuchElementException();
		return front.getNext();
	}

	/**
	 * Returns true if the list does not contain any elements.
	 * @return true if the list does not contain any elements
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Returns the position of the last element of the list.
	 * @return the position of the last element of the list
	 * @throws NoSuchElementException if list is empty
	 */
	@Override
	public Position<E> last() {
		if (isEmpty()) throw new NoSuchElementException();
		return tail.getPrevious();
	}

	/**
	 * Removes and returns the element at position p in the list, invalidating the position
	 * @param p the position to remove
	 * @return the element at p
	 */
	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> pN = validate(p);
		PositionalNode<E> prev = pN.getPrevious();
		PositionalNode<E> next = pN.getNext();
		prev.setNext(next);
		next.setPrevious(prev);
		size--;
		E ele = pN.getElement();
//		pN.setElement(null);
//		pN.setNext(null);
//		pN.setPrevious(null);
		return ele;
	}

	/**
	 * Replaces the element at position p with element value, returning the element formerly at p
	 * @param p the element to replace
	 * @param value the value to replace p with
	 * @return the element that was changed
	 * @throws IllegalArgumentException if validate(p) doesn't work
	 */
	@Override
	public E set(Position<E> p, E value) {
		PositionalNode<E> pN = validate(p);
		E ele = pN.getElement();
		pN.setElement(value);
		return ele;
	}

	/**
	 * Returns the number of elements in the list.
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

}