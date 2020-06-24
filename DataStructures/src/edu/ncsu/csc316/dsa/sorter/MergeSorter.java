package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Serves as a sorting algorithm using the MergeSort algorithm.
 * @author Chase Helton
 *
 * @param <E> the generic type of data
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructor for the MergeSorter.
	 * @param comparator the object to compare
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructor for the MergeSorter with no parameter.
	 */
	public MergeSorter() {
		this(null);
	}

	/**
	 * Main sorting algorithm.
	 * @param data the data to sort
	 */
	@Override
	public void sort(E[] data) {
		if (data.length < 2) return;
		int mid = data.length / 2;
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, data.length);
		
		sort(left);
		sort(right);
		
		merge(left, right, data);
	}
	
	/**
	 * Merges the different arrays together.
	 * @param left the left side of the array
	 * @param right the right side of the array
	 * @param data the merged data
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int leftIndex = 0;
		int rightIndex = 0;
		while ((leftIndex + rightIndex) < data.length) {
			if (rightIndex == right.length || ((leftIndex < left.length) && this.compare(left[leftIndex], right[rightIndex]) == -1)) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex++;
			} else {
			    data[leftIndex + rightIndex] = right[rightIndex];
			    rightIndex++;
			}
		}
	}
	
}
