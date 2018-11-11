package others;

import entity.Person;
import entity.Student;

public class MainController {
	
	// 1. ADD A STUDENT
	public void addStudent(String matric, String name, String email) {
		// Create student object
		Person newStudent = new Student(matric, name, email);
		
		// Check whether existing 
		boolean isExisting = newStudent.checkExisting();
		if (isExisting) {
			System.out.println("Student already exists.");
			return;
		}
		
		// Add student to database
		
	}
	
	// 2. ADD A COURSE
	public void addCourse() {
		
	}
	
	// 10. Print Student Transcript 
	
}
