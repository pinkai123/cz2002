package entity;

import java.util.ArrayList;

/**
 * Represents a student enrolled in the school.
 * A student can be enrolled in many courses.
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 */
public class Student extends Person{
	// Course and result objects as attributes (Association)
	/**
	 * Consists of all courses that the student have registered.
	 */
	private ArrayList<Course> courseRegistered = new ArrayList<Course>();
	
	/**
	 * Contains all of the results of the courses the student has registered.
	 */
	private ArrayList<Result> resultList = new ArrayList<Result>();
	
	/**
	 * Creates a new Student with the given name.
	 * Name should be full name.
	 * @param name This student's name
	 * @param matric This student's matriculation number
	 * @param email This student's school email
	 */
	public Student(String name, String matric, String email) {
		super(name, matric, email);
	}
	
	/**
	 * Gets the list of courses that the student has registered.
	 * @return All student's registered courses
	 */
	public ArrayList<Course> getCourseRegistered() {
		return courseRegistered;
	}
	
	/**
	 * Gets the lists of results of the courses that the student has registered for.
	 * @return All of student's results
	 */
	public ArrayList<Result> getCourseResult() {
		return resultList;
	}
	
	/**
	 * Add in new course that the student has registered for.
	 * @param newCourse This student's new registered course
	 */
	public void addCourse(Course newCourse) {
		courseRegistered.add(newCourse);
	}
	
	/**
	 * Add in a result of the course that the student has registered for. 
	 * @param newResult This student's new result 
	 */
	public void addResult(Result newResult) {
		resultList.add(newResult);
	}
	
	/**
	 * Check whether the format of the matriculation number is valid.
	 * @param matric student's matriculation number
	 * @return true if the format of the matriculation number is correct
	 */
	public static boolean isValidMatric(String matric) {
		int lengthOfMatric = 9;
		// Check correct length
		if (matric.length() != lengthOfMatric)
			return false;
		// Check last char is an alphabet
		if (!Character.isLetter(matric.charAt(lengthOfMatric-1)))
			return false;
		// Check first char is 'U'
		if (!(matric.charAt(0) == 'U'))
			return false;
		// Check the rest are digits
		for (int i = 1; i < 8; i ++) {
			if (!Character.isDigit(matric.charAt(i)))
				return false;
		}
		return true;
	}
}
