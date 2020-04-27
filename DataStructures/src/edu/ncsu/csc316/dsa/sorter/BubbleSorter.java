package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter uses the bubble sort algorithm to sort data
 * 
 * @author Chase Helton
 *
 * @param <E> the generic type of data to sort
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
     * BubbleSorter constructor with a comparator as a parameter
     * @param comparator the comparator to set
     */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
     * SelectionSorter constructor with no parameter
     */
	public BubbleSorter() {
		super(null);
	}

	/**
	 * BubbleSort method used to sort a given generic array
	 * @param data the generic array sorted
	 */
	public void sort(E[] data) {
		boolean r = true;
		E x;
		while (r) {
			r = false;
			for (int i = 1; i < data.length; i++) {
				if (super.compare(data[i], data[i - 1]) < 0) {
					x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
	}
	
}
