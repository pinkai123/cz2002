package others;

import java.io.IOException;
import java.util.ArrayList;

import entity.*;
import fileIO.*;

public class MainController {
	
	// 1. ADD A STUDENT
	public void addStudent(String matric, String name, String email) {
		FileIO IO = new StudentIO();
		ArrayList al = new ArrayList();
		
		// Create student object
		Person newStudent = new Student(matric, name, email);
		
		// Check whether existing 
		boolean isExisting = Student.checkExisting(matric);
		if (isExisting) {
			System.out.println("Student already exists.");
			return;
		}
		
		// Add student to database
		al.add(newStudent);
		try {
			IO.saveData("student.txt",al);
			System.out.println("Student successfully added.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 2. ADD A COURSE
	public void addCourse() {
		
	}
	
	// 10. Print Student Transcript 
	
}
