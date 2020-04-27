package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * 
 * @param <E> the generic type of data to sort
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/**
     * InsertionSorter constructor with a comparator as a parameter
     * @param comparator the comparator to set
     */
    public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * InsertionSorter constructor with no parameters
     */
    public InsertionSorter() {
        super(null);
    }
	
	/**
	 * Interface method used to sort a given generic array
	 * @param data the generic array sorted
	 */
	public void sort(E[] data) {
		E x;
		int j = 0;
		for (int i = 1; i <= data.length - 1; i++) {
			x = data[i];
			j = i - 1;
			while (j >= 0 && super.compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = x;
		}
	}
}
