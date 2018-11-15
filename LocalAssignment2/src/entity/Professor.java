package entity;

import java.util.ArrayList;

/**
 * Represents a professor teaching or doing research in the school.
 * A professor can be a course coordinator for multiple courses.
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 *
 */
public class Professor extends Person{
	// Course objects as attributes (Association)
	/**
	 * All courses that the professor is in charge of. 
	 */
	private ArrayList<Course> courseList = new ArrayList<Course>();
		
	// Constructor 
	/**
	 * Create a new professor.
	 * @param name This professor's name
	 * @param matric This professor's matriculation
	 * @param email This professor's school email
	 */
	public Professor(String name, String matric, String email) {
		super(name, matric, email);
	}
	
	/**
	 * Add new course that the professor is the course coordinator.
	 * @param newC This professor's new course coordinating
	 */
	public void addCourse(Course newC) {
		courseList.add(newC);
	}
	/**
	 * Get all courses that the professor is in-charge of.
	 * @return All courses the professor is coordinating
	 */
	public ArrayList<Course> getCourse() {
		return courseList;
	}
	
	/**
	 * Check whether the format of the matriculation number is valid.
	 * @param matric this professor's matriculation number
	 * @return true if the format of the matriculation number is valid
	 */
	public static boolean isValidMatric(String matric) {
		int lengthOfMatric = 9;
		// Check correct length
		if (matric.length() != lengthOfMatric)
			return false;
		// Check last char is an alphabet
			if (!Character.isLetter(matric.charAt(lengthOfMatric-1)))
				return false;
		// Check first char is 'P'
			if (!(matric.charAt(0) == 'P'))
				return false;
		// Check the rest are digits
		for (int i = 1; i < 8; i ++) {
			if (!Character.isDigit(matric.charAt(i)))
				return false;
		}
		return true;
	}
}
