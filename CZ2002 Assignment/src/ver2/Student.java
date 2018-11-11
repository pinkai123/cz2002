package ver2;

import java.util.ArrayList;
import java.util.Objects;

public class Student extends Person{
	
	// Constructor
	public Student(String name, String matric, String email) {
		super(name, matric, email);
	}
	
	// Validation
	public static boolean checkStudentMatric(String matric) {
		int lengthOfMatric = 9;
		// Check correct length
		if (matric.length() != lengthOfMatric)
			return false;
		// Check first and last char are alphabet
		if (!Character.isLetter(matric.charAt(0)) | !Character.isLetter(matric.charAt(lengthOfMatric-1)))
			return false;
		// Check the rest are digits
		for (int i = 1; i < 8; i ++) {
			if (!Character.isDigit(matric.charAt(i)))
				return false;
		}
		return true;
	}
	
	public boolean checkExisting() {
		// Retrieve all Student Objects in text file
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		// Compare matric number 
		for (int i = 0; i < studentList.size(); i ++) {
			if (Objects.equals(this.matric, studentList.get(i).getMatric())) {
				return true;
			}
		}
		return false;
	}
}
