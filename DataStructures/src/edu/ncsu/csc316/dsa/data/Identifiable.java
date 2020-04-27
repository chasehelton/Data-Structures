package edu.ncsu.csc316.dsa.data;

/**
 * An identifiable object that has an ID number (integer).
 * 
 * @author Dr. King
 */
public interface Identifiable {

    /** 
	 * Used for CountingSorter and RadixSorter
	 * @return the ID
     */
    public abstract int getId();

}
