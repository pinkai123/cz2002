package entity;

import java.util.ArrayList;

public class Student extends Person{
	// Course and result objects as attributes (Association)
	ArrayList<Course> courseRegistered = new ArrayList<Course>();
	ArrayList<Result> resultList = new ArrayList<Result>();
	
	// Constructor
	public Student(String name, String matric, String email) {
		super(name, matric, email);
	}
	
	// Accessor
	public ArrayList<Course> getCourseRegistered() {
		return courseRegistered;
	}
	public ArrayList<Result> getCourseResult() {
		return resultList;
	}
	
	// Mutator
	public void addCourse(Course newCourse) {
		courseRegistered.add(newCourse);
	}
	public void addResult(Result newResult) {
		resultList.add(newResult);
	}
	
	// Check naming convention of STUDENT matric is valid
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
