package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import fileIO.*;
import others.*;

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
	
	public void addCourse(Course newCourse) {
		courseRegistered.add(newCourse);
	}
	public void addResult(Result newResult) {
		resultList.add(newResult);
	}

	
	// Other Methods
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
	
	// Check whether a student exists in the database
	public static boolean isExisting(String matricNum){
		// Retrieve all Student Objects in text file
		ArrayList<Student> studentList = new ArrayList<Student>();
		FileIO retrieve = new StudentIO();
		try {
			studentList = retrieve.readData();
		} catch(IOException e) {
			return false;
		}
		
		// Check whether matric number is existing
		for (int i = 0; i < studentList.size(); i ++) {
			Person temp = (Student) studentList.get(i);
			if (Objects.equals(matricNum, temp.getMatric())) {
				return true;
			}
		}
		return false;
		
	}
}
