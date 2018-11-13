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
		System.out.println(x);
		if (isExisting) {
			System.out.println("Student already exists.");
			return;
		}
		
		// Add student to database
		al.add(newStudent);
		try {
			IO.saveData(al);
			System.out.println("Student successfully added.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Print all students
	}
	
	// 2. ADD A COURSE
	public boolean checkCourse(String courseID, String profMatric) {
		boolean flag = true;
		
		// retrieve course from text file

		// Check courseID
		
		// Check whether prof in database
		boolean isExisting = Professor.checkExisting(profMatric);
		if (!isExisting) {
			System.out.print("Professor doesn't exist.");
			flag = false;
		}
		
		return flag;
	}
	public void addCourse() {
		
	}
	
	// 10. Print Student Transcript 
	public void printStudentTranscript(String matric) {
		// Retrieve result according to matric
		
		// Print individual overall course mark and grade, individual component marks, weightages
		
		// Account for student in no course or no result given yet
	}
}
