package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Interface for making a generic sorter for bubble, insertion, and selection
 * 
 * @author Chase Helton
 *
 * @param <E> the generic comparable type
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** Field for the specific comparator */
    private Comparator<E> comparator;
    
    /**
     * Used to designate which comparator in the constructor
     * @param comparator the comparator to use
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Sets the NaturalOrder sort if no comparator is provided to set
     * @param comparator the comparator to set
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
           comparator = new NaturalOrder();
        }
        this.comparator = comparator;
    }     
    
    /**
     * Sorts in NaturalOrder
     */
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * Compares two generic objects to each other
     * @param data1 generic object one
     * @param data2 generic object two
     * @return an integer based on which object comes first
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}