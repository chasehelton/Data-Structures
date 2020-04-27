package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

//import edu.ncsu.csc316.dsa.list.ArrayBasedList;
//import edu.ncsu.csc316.dsa.list.List;

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
	
//	private List<E> strList;
//	
//	public MergeSorter(Comparator<E> comparator, List<E> input) {
//		super(comparator);
//		strList = input;
//    }
//     
//    public void listSort() {
//        strList = listMergeSort(strList);
//    }
// 
//    public List<E> listMergeSort(List<E> data) {
//    	List<E> left = new ArrayBasedList<E>();
//    	List<E> right = new ArrayBasedList<E>();
//        int center;
// 
//        if (data.size() == 1) {    
//            return data;
//        } else {
//            center = data.size() / 2;
//            for (int i = 0; i < center; i++) {
//                left.add(i, data.get(i));
//            }
//            for (int i = center; i < data.size(); i++) {
//            	right.add(i, data.get(i));
//            }
//            left  = listMergeSort(left);
//            right = listMergeSort(right);
//            listMerge(left, right, data);
//        }
//        return data;
//    }
// 
//    private void listMerge(List<E> left, List<E> right, List<E> whole) {
//        int leftIndex = 0;
//        int rightIndex = 0;
//        int wholeIndex = 0;
//        while (leftIndex < left.size() && rightIndex < right.size()) {
//            if ( (left.get(leftIndex).compareTo(right.get(rightIndex))) < 0) {
//                whole.set(wholeIndex, left.get(leftIndex));
//                leftIndex++;
//            } else {
//                whole.set(wholeIndex, right.get(rightIndex));
//                rightIndex++;
//            }
//            wholeIndex++;
//        }
// 
//        List<E> rest;
//        int restIndex;
//        if (leftIndex >= left.size()) {
//            rest = right;
//            restIndex = rightIndex;
//        } else {
//            rest = left;
//            restIndex = leftIndex;
//        }
//        for (int i = restIndex; i < rest.size(); i++) {
//            whole.set(wholeIndex, rest.get(i));
//            wholeIndex++;
//        }
//    }
//    
//    
//    

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
