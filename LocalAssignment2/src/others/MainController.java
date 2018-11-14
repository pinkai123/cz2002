package others;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import entity.*;
import fileIO.*;

public class MainController {
	
	// 1. ADD A STUDENT
	public boolean addStudent(String name, String matric, String email) {		
		// Create student object
		Student newStudent = new Student(name, matric, email);
		
		// Check whether existing 
		boolean isExisting = Student.isExisting(matric);
		if (isExisting) {
			System.out.println("Student already exists.");
			return false;
		}
		
		// Add student to database
		Database.addStudent(newStudent);
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
		Professor prof = IOController.findProfessor(profMatric);
		
		// Create course object
		Course newCourse = new Course(courseID,courseName,prof,lecVac);
		return newCourse;
	}
	
	public void addCourse(Course newCourse) {
		// Add course into database
		FileIO courseIO = new CourseIO();
		try {
			ArrayList<Course> al = courseIO.readData();
			al.add(newCourse);
			courseIO.saveData(al);
		} 
		catch (IOException e) {
			System.err.println("IO Exception in adding Course.");
		}
	}
	
	public void printCourses() {
		FileIO courseIO = new CourseIO();
		ArrayList al;
		try {
			al = courseIO.readData();
			System.out.println(al.toString());
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
	public boolean addStudenttoCourse(Student tempS, Course tempC, int tutIndex, int labIndex) {
		Lesson tempTut = null;
		Lesson tempLab = null;
		
		// If there is lab, check vacancy
		if (labIndex != 0) {
			tempLab = tempC.getLesson(labIndex);
			if (tempLab.getVacancy() == 0) {
				System.out.println("No vacacncy in lab.");
				return false;
			}
		}
		// If there is tut, check vacancy
		if (tutIndex != 0) {
			tempTut = tempC.getLesson(tutIndex);
			if (tempTut.getVacancy() == 0) {
				System.out.println("No vacancy in tutorial.");
				return false;
			}
		}
		
		// Change vacancy
		// Course Vacancy
		tempC.decrementVacancy();
		// Tutorial vacancy
		int i = tempC.getLessonList().indexOf(tempTut);
		if (i != -1) {
			tempC.getLessonList().get(i).decrementVacancy();
		}
		// Lab vacancy
		i = tempC.getLessonList().indexOf(tempLab);
		if (i != -1) {
			tempC.getLessonList().get(i).decrementVacancy();
		}
		
		// Add into database
		tempC.addStudent(tempS);
		FileIO IO = new CourseIO();
		try {
			ArrayList<Course> insert = IO.readData();
			insert.add(tempC);
			IO.saveData(insert);
			System.out.println("Student registered.");
			return true;
		}	
		catch(IOException e) {
			return false;
		}
	}
	
	// 10. Print Student Transcript 
	public void printStudentTranscript(String matric) {
		// Retrieve result according to matric
		
		// Print individual overall course mark and grade, individual component marks, weightages
		
		// Account for student in no course or no result given yet
	}
}
