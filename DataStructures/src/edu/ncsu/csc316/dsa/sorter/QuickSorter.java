package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This class serves as a sorting method after the QuickSort algorithm.
 * @author Chase Helton
 *
 * @param <E> the generic data type
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** The first element to select. */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
	/** The last element to select. */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
	/** The middle element to select. */
	public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
	/** The random element to select. */
	public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
	
	/** The selector for the QuickSorter. */
	private PivotSelector selector;
	
	/**
	 * Constructs the QuickSorter with a comparator and PivotSelector.
	 * @param comparator the object to compare
	 * @param p the PivotSelector
	 */
	public QuickSorter(Comparator<E> comparator, PivotSelector p) {
		super(comparator);
		setSelector(p);
	}
	
	/**
	 * Constructs the QuickSorter with a comparator.
	 * @param comparator the object to compare
	 */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
		selector = new RandomElementSelector();
	}
	
	/**
	 * Constructs the QuickSorter with a PivotSelector.
	 * @param p the PivotSelector
	 */
	public QuickSorter(PivotSelector p) {
		this(null, p);
	}
	
	/**
	 * Constructs the QuickSorter with no parameter.
	 */
	public QuickSorter() {
		this(null, null);
		selector = new RandomElementSelector();
	}
	
	/**
	 * Sets the PivotSelector.
	 * @param p the PivotSelector to set
	 */
	private void setSelector(PivotSelector p) {
		if(selector == null) {
            selector = new RandomElementSelector();
        }
		this.selector = p;
	}

	/**
	 * Sorts the data.
	 * @param data the data to sort
	 */
	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
	}
	
	/**
	 * The main method for quick sorting.
	 * @param data the data to sort
	 * @param low the low index to sort
	 * @param high the high index to sort
	 */
	private void quickSort(E[] data, int low, int high) {
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}
	
	/**
	 * Partitions the data.
	 * @param data the data to partition
	 * @param low the low index
	 * @param high the high index
	 * @return the index of the pivot element, after moving values < pivot to be before the pivot index
	 * and values > pivot to be after the pivot index
	 */
	private int partition(E[] data, int low, int high) {
		
		int pivotIndex = selector.selectPivot(low, high);
		
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}
	
	/**
	 * Helps partition the data.
	 * @param data the data to partition
	 * @param low the low index
	 * @param high the high index
	 * @return the index of the pivot element, after moving values < pivot to be before the pivot index 
	 * and values > pivot to be after the pivot index
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int index = low;
		for (int i = low; i < high; i++) {
			if (this.compare(data[i], pivot) == -1) {
				swap(data, index, i);
				index++;
			}
		}
		swap(data, index, high);
		
		return index;
	}
	
	/**
	 * Swaps the elements.
	 * @param data the array of data to swap inside
	 * @param low the first element to swap
	 * @param high the second element to swap
	 */
	private void swap(E[] data, int low, int high) {
		E temp = data[low];
		data[low] = data[high];
		data[high] = temp;
	}
	
	
	
	
	
	/**
	 * Returns the lowest index to consider.
	 * @author Chase Helton
	 *
	 */
	public static class FirstElementSelector implements PivotSelector {
		
		/**
		 * Constructor for the class.
		 */
		public FirstElementSelector() {
			// Constructs FirstElementSelector
		}
		
		/**
		 * Selects the low index.
		 * @param low the lowest index to consider
		 * @param high the highest index to consider 
		 * @return the index of the selected pivot element
		 */
		public int selectPivot(int low, int high) {
			return low;
		}
	}
	
	/**
	 * Returns the highest index to consider.
	 * @author Chase Helton
	 *
	 */
	public static class LastElementSelector implements PivotSelector {
		
		/**
		 * Constructor for the class.
		 */
		public LastElementSelector() {
			// Constructs FirstElementSelector
		}
		
		/**
		 * Selects the high index.
		 * @param low the lowest index to consider
		 * @param high the highest index to consider 
		 * @return the index of the selected pivot element
		 */
		public int selectPivot(int low, int high) {
			return high;
		}
	}
	
	/**
	 * Returns the middle index.
	 * @author Chase Helton
	 *
	 */
	public static class MiddleElementSelector implements PivotSelector {
		
		/**
		 * Constructor for the class.
		 */
		public MiddleElementSelector() {
			// Constructs FirstElementSelector
		}
		
		/**
		 * Selects the middle index.
		 * @param low the lowest index to consider
		 * @param high the highest index to consider 
		 * @return the index of the selected pivot element
		 */
		public int selectPivot(int low, int high) {
			return (high + low) / 2;
		}
	}
	
	/**
	 * Returns a random index.
	 * @author Chase Helton
	 *
	 */
	public static class RandomElementSelector implements PivotSelector {
		
		/**
		 * Constructor for the class.
		 */
		public RandomElementSelector() {
			// Constructs FirstElementSelector
		}
		
		/**
		 * Selects a random index.
		 * @param low the lowest index to consider
		 * @param high the highest index to consider 
		 * @return the index of the selected pivot element
		 */
		public int selectPivot(int low, int high) {
			int range = (high - low) + 1;     
		    return (int) (Math.random() * range) + low;
		}
	}
	
	/**
	 * Interface for the PivotSelector.
	 * @author Chase Helton
	 *
	 */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * @param low - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
	
	
}
