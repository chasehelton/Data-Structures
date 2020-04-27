package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * 
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/**
     * SelectionSorter constructor with a comparator as a parameter
     * @param comparator the comparator to set
     */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    /**
     * SelectionSorter constructor with no parameters
     */
    public SelectionSorter() {
    	super(null);
    }
	
    /**
	 * Interface method used to sort a given generic array
	 * @param data the generic array sorted
	 */
	public void sort(E[] data) {
		E x;
		int min = 0;
		for (int i = 0; i <= data.length - 1; i++) {
			min = i;
			for (int j = i + 1; j <= data.length - 1; j++) {
				if (super.compare(data[j], data[min]) < 0) {
					min = j;
				}
			}
			if (i != min) {
				x = data[i];
				data[i] = data[min];
				data[min] = x;
			}
		}
	}
}