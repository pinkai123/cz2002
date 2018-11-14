package others;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import entity.*;

public class MainController {
	
	// 1. ADD A STUDENT
	public boolean addStudent(String name, String matric, String email) {
		// Create student object
		Person newStudent = new Student(name, matric, email);
		
		// Check whether existing 
		Person existing = Database.getStudent(matric);
		if (existing != null) {
			System.out.println("Student already exists.");
			return false;
		}
		
		// Add student to database
		Database.addStudent((Student)newStudent);
		return true;
	}
	
	public void printStudents() {
		ArrayList<Student> al = Database.getAllStudent();
	
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
		Course newCourse = Database.getCourse(courseID);
		if (newCourse != null) {
			System.out.println("Course already exists.");
			return false;
		}
		
		// Check whether prof in database
		Professor prof = Database.getProfessor(profMatric);
		if (prof == null) {
			System.out.println("Professor doesn't exist.");
			return false;
		}
		return true;
	}
	
	public Course newCourse(String courseID, String courseName, String profMatric, int lecVac) {
		// Find professor
		Professor prof = Database.getProfessor(profMatric);
		
		// Create course object
		Course newCourse = new Course(courseID,courseName,prof,lecVac);
		return newCourse;
	}
	
	public void addPrintCourse(Course newCourse) {
		Database.addCourse(newCourse);
		
		ArrayList<Course> courseList = Database.getAllCourse();
		System.out.println("CourseID, CourseName, CourseCoordinatorID\n"
				+ "########################");
		for (int i = 0; i < courseList.size(); i ++ ) {
			Course course = (Course) courseList.get(i);
			System.out.println(course.getCourseID() + "  " + course.getCourseName() + "  " 
					+ course.getCourseCoordinator().getMatric());
		}
	}
	
	
	
	
	
	// 3. Register student for a course
	// Check whether student and course exists
	public boolean checkStudentCourse(String matric, String courseID) {
		Student s = Database.getStudent(matric);
		Course c = Database.getCourse(courseID);
		if (s == null) {
			System.out.println("Student does not exist.");
			return false;
		}
		else if (c == null) {
			System.out.println("Course does not exist.");
			return false;
		}
		
		// Check whether student has registered for course
		for (int i = 0; i < c.getStudentList().size(); i ++) {
			Student tempS = c.getStudentList().get(i);
			if (Objects.equals(s, tempS)) {
				System.out.println("Student already registered.");
				return false;
			}
		}
		
		// Check for vacancy
		if (c.getVacancy() == 0) {
			System.out.println("No more vacancy in course.");
			return false;
		}
		return true;

	}
	
	// Add student to course
	public boolean addStudenttoCourse(String matric, String courseID, int tutIndex, int labIndex) {
		// Get course and student objects
		Course tempC = Database.getCourse(courseID);
		Student tempS = Database.getStudent(matric);
		
		// Check whether there is vacancy in lab
		if (labIndex != 0) {
			if (tempC.getLesson(labIndex).getVacancy() == 0) {
				System.out.println("No vacancy in lab.");
				return false;
			}
		}
		// Check whether there is vacancy in tut
		if (tutIndex != 0) {
			if (tempC.getLesson(tutIndex).getVacancy() == 0) {
				System.out.println("No vacancy in tutorial.");
				return false;
			}
		}
		
		// Decrement Vacancy
		tempC.decrementVacancy();
		if (labIndex != 0)
			tempC.getLesson(labIndex).decrementVacancy();
		if (tutIndex != 0)
			tempC.getLesson(tutIndex).decrementVacancy();
		
		// Add student
		tempC.addStudent(tempS);
		
		// Update course
		Database.addCourse(tempC);
		System.out.println("Student registered.");
		return true;
	}
	
	
	
	// 4. Find vacancy
	public void findVacancy(String courseID, int classIndex) {
		Course tempC = Database.getCourse(courseID);
		if (tempC == null) {
			System.out.println("CourseID does not exist.");
			return;
		}
		Lesson tempL = tempC.getLesson(classIndex);
		if (tempL == null) {
			System.out.println("ClassIndex does not exist.");
			return;
		}
		
		// Print Vacancy
		System.out.println("Vacancy: " + tempL.getVacancy() + "/" + tempL.getTotalSize());
	}
	
	
	// 9. Print course statistics
	// Show percentage overall, exam only, coursework only
	public void printCourseStat(String courseID) {
		// Get course object
		Course tempC = Database.getCourse(courseID);
		if (tempC == null) {
			System.out.println("Course does not exist");
			return;
		}
		// Get results for course
	}
	
	public void printCourseAnalysis(String courseID) {
		// Get course object
		Course tempC = Database.getCourse(courseID);
		if (tempC == null) {
			System.out.println("Course does not exist");
			return;
		}
	}
	
	// 10. Print Student Transcript 
	public void printStudentTranscript(String matric) {
		// Find student
		Student tempS = Database.getStudent(matric);
		// Result of student
		ArrayList<Result> sResult = new ArrayList<Result>();
		
		if (tempS == null) {
			System.out.println("Student does not exist.");
			return;
		}
		
		// Retrieve result according to matric
		ArrayList<Result> allResult = Database.getAllResult();
		for (int i = 0; i < allResult.size(); i ++) {
			Result tempR = allResult.get(i);
			if (tempR.isStudent(matric)) {
				sResult.add(tempR);
			}
		}
		if (sResult.isEmpty()) {
			System.out.println("Student has no results.");
			return;
		}
		
		// Print individual overall course mark and grade, individual component marks, weightages
		for (int j = 0; j < sResult.size(); j ++) {			
			// Obtain course weightage to calculate marks 
			Result tempR = sResult.get(j);
			Course tempC = tempR.getCourse();
			Weightage w = tempC.getCourseWeightage();
			double overallMark = w.calculateMark(tempR);
			String overallGrade = w.calculateGrade(overallMark);
			
			// Print results
			System.out.println(sResult.get(j).getCourse().getCourseID());
			System.out.println("Overall Marks: " + overallMark + ", Overall Grade; " + overallGrade);
			w.printMarks(tempR);
			
		}
	}
}
