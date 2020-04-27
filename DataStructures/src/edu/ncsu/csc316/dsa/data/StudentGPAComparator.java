package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA.
 * 
 * @author Dr. King
 */
public class StudentGPAComparator implements Comparator<Student> {

    /**
     * Compares students based on GPA in descending order.
     * @param one the first student to compare
     * @param two the second student to compare
     * @return an integer based on which student comes first
     */
	public int compare(Student one, Student two) {
        if (one.getGpa() < two.getGpa())
            return 1;
        else if (one.getGpa() > two.getGpa())
        	return -1;
        else
        	return 0;
    }

}
