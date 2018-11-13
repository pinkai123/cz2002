package others;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import entity.*;
import fileIO.*;

public class MainController {
	
	// 1. ADD A STUDENT
	public boolean addStudent(String name, String matric, String email) {
		FileIO studentIO = new StudentIO();
		ArrayList al = new ArrayList();
		
		// Create student object
		Person newStudent = new Student(name, matric, email);
		
		// Check whether existing 
		boolean isExisting = Student.isExisting(matric);
		if (isExisting) {
			System.out.println("Student already exists.");
			return false;
		}
		
		// Add student to database
		al.add(newStudent);
		try {
			studentIO.saveData(al);
			System.out.println("Student successfully added.");
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public void printStudents() {
		FileIO studentIO = new StudentIO();
		ArrayList<Student> al;
		try {
			al = studentIO.readData();
		}
		catch (IOException e) {
			System.out.println("No Student.");
			return;
		}
		System.out.println("Matric No.    Name\n"
				+ "########################");
		for (int i = 0; i < al.size(); i ++ ) {
			Student student = (Student) al.get(i);
			System.out.println(student.getMatric() + "     " + student.getName());
		}
		
	}
	// 2. ADD A COURSE
	public boolean checkCourse(String courseID, String profMatric) {
		boolean flag = true;
		
		// retrieve course from text file
		ArrayList al = new ArrayList();
		FileIO courseIO = new CourseIO();
		try {
			al = courseIO.readData();
		} catch (IOException e) {
			System.out.println("No file to read.");
		}
		
		// Check courseID
		for (int i = 0; i < al.size(); i ++) {
			Course course = (Course) al.get(i);
			if (Objects.equals(courseID, course.getCourseID())) {
				System.out.println("Course already exists.");
				flag = false;
			}
		}
		
		
		// Check whether prof in database
		boolean isExisting = Professor.isExisting(profMatric);
		if (!isExisting) {
			System.out.println("Professor doesn't exist.");
			flag = false;
		}
		
		return flag;
	}
	
	public Course newCourse(String courseID, String courseName, String profMatric, int lecVac) {
		// Find professor
		Professor prof = FindByID.findProfessor(profMatric);
		
		// Create course object
		Course newCourse = new Course(courseID,courseName,prof,lecVac);
		return newCourse;
	}
	
	public void addCourse(Course newCourse) {
		// Add course into database
		FileIO courseIO = new CourseIO();
		ArrayList<Course> al = new ArrayList<Course>();
		al.add(newCourse);
		try{
			courseIO.saveData(al);
		} catch (IOException e) {
			System.err.println("IO Exception in adding Course.");
		}
	}
	
	public void printCourses() {
		FileIO courseIO = new CourseIO();
		ArrayList al;
		try {
			al = courseIO.readData();
		}
		catch (IOException e) {
			System.out.println("No Course.");
			return;
		}
		System.out.println("CourseID, CourseName, CourseCoordinatorID\n"
				+ "########################");
		for (int i = 0; i < al.size(); i ++ ) {
			Course course = (Course) al.get(i);
			System.out.println(course.getCourseID() + "  " + course.getCourseName() + "  " 
					+ course.getCourseCoordinator().getMatric());
		}
		
	}
	
	// 3. Register student for a course
	
	// Add student to course
	public void 
	
	// 10. Print Student Transcript 
	public void printStudentTranscript(String matric) {
		// Retrieve result according to matric
		
		// Print individual overall course mark and grade, individual component marks, weightages
		
		// Account for student in no course or no result given yet
	}
}
