package entity;

import java.util.ArrayList;

public class Professor extends Person{
	// Course objects as attributes (Association)
	private ArrayList<Course> courseList = new ArrayList<Course>();
		
	// Constructor 
	public Professor(String name, String matric, String email) {
		super(name, matric, email);
	}
	
	public void addCourse(Course newC) {
		courseList.add(newC);
	}
	public ArrayList<Course> getCourse() {
		return courseList;
	}
	
	// Check naming convention of PROF matric is valid
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
