package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable. Students have a first name, last
 * name, id number, number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 *
 */
public class Student implements Comparable<Student>, Identifiable {

	/** The Student's first name */
	private String first;
	/** The Student's last name */
	private String last;
	/** The Student's ID */
	private int id;
	/** The Student's credit hours */
	private int creditHours;
	/** The Student's GPA */
	private double gpa;
	/** The Student's Unity ID */
	private String unityID;

	/**
	 * Constructor for Student objects
	 * 
	 * @param first       the Student's first name
	 * @param last        the Student's last name
	 * @param id          the Student's ID
	 * @param creditHours the Student's credit hours
	 * @param gpa         the Student's GPA
	 * @param unityID     the Student's Unity ID
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityID = unityID;
	}

	/**
	 * Compares the Students based on last, first, then id
	 * 
	 * @param o the other Student to compare
	 * @return an integer based on whether the other Student comes before or after
	 *         the current one
	 */
	public int compareTo(Student o) {
		if (last.compareTo(o.last) > 0)
			return 1;
		else if (last.compareTo(o.last) < 0)
			return -1;
		else {
			if ((first.compareTo(o.first)) > 0)
				return 1;
			else if ((first.compareTo(o.first)) < 0)
				return -1;
			else {
				if (id < o.id)
					return 1;
				else if (id > o.id)
					return -1;
				else
					return 0;
			}
		}
	}

	/**
	 * Returns the Student as a string
	 * 
	 * @return the Student as a string
	 */
	public String toString() {
		return "Last: " + last + ", First: " + first + ", Student ID: " + id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Student other = (Student) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

	/**
	 * Returns the Student's first name
	 * 
	 * @return the Student's first name
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the Student's first name
	 * 
	 * @param first the Student's new first name
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Returns the Student's last name
	 * 
	 * @return the Student's last name
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets the Student's last name
	 * 
	 * @param last the Student's new last name
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Returns the Student's ID
	 * 
	 * @return the Student's ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the Student's ID
	 * 
	 * @param id the Student's new ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the Student's credit hours
	 * 
	 * @return the Student's credit hours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets the Student's credit hours
	 * 
	 * @param creditHours the Student's new credit hours
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Returns the Student's GPA
	 * 
	 * @return the Student's GPA
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets the Student's GPA
	 * 
	 * @param gpa the Student's new GPA
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Returns the Student's Unity ID
	 * 
	 * @return the Student's Unity ID
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Sets the Student's Unity ID
	 * 
	 * @param unityID the Student's new Unity ID
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

}
