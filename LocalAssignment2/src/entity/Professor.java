package entity;

import java.util.ArrayList;

public class Professor extends Person{
	// Student and result objects as attributes (Association)
	ArrayList<Student> studentList = new ArrayList<Student>();
	ArrayList<Result> resultList = new ArrayList<Result>();
		
	// Constructor 
	public Professor(String name, String matric, String email) {
		super(name, matric, email);
	}
	
	// Accessor
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	public ArrayList<Result> getResultList() {
		return resultList;
	}
	
	// Other Methods
	public void addStudent(Student newStudent) {
		studentList.add(newStudent);
	}
	public void addResult(Result newResult) {
		resultList.add(newResult);
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
