package others;

import java.util.ArrayList;
import java.util.Objects;

import entity.*;

public class MainController {
	// 1. ADD A STUDENT
	// add student to database
	public static boolean addStudent(String name, String matric, String email) {
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
	
	// print student 
	public static void printAllStudent() {
		// Retrieve students from database
		ArrayList<Student> al = Database.getAllStudent();
		
		System.out.println("Matric No.    Name\n"
				+ "########################");
		// Print each student name and matric no.
		for (int i = 0; i < al.size(); i ++ ) {
			Student student = (Student) al.get(i);
			System.out.println(student.getMatric() + "     " + student.getName());
		}
	}
	
	
	// 2. ADD A COURSE
	// Check course and prof
	public static boolean checkCourse(String courseID, String profMatric) {
		// Get course from database
		Course newCourse = Database.getCourse(courseID);
		if (newCourse != null) {
			System.out.println("Course already exists.");
			return false;
		}
		
		// Get prof from database
		Professor prof = Database.getProfessor(profMatric);
		if (prof == null) {
			System.out.println("Professor doesn't exist.");
			return false;
		}
		return true;
	}
	
	// Create course object
	public static Course newCourse(String courseID, String courseName, String profMatric, int lecVac) {
		// Find professor
		Professor prof = Database.getProfessor(profMatric);
		
		// Create course object
		Course newCourse = new Course(courseID,courseName,prof,lecVac);
		return newCourse;
	}
	
	// Add lesson to course
	public static void addCourse(Course tempC) {
		Database.addCourse(tempC);
		return;
	}
	
	// Print all courses
	public static void printAllCourse() {		
		ArrayList<Course> courseList = Database.getAllCourse();
		System.out.println("CourseID, CourseName, CourseCoordinatorID\n"
				+ "########################");
		for (int i = 0; i < courseList.size(); i ++ ) {
			Course course = (Course) courseList.get(i);
			System.out.println(course.getCourseID() + "  " + course.getCourseName() + "  " 
					+ course.getCourseCoordinator().getMatric());
		}
	}
	
	// 3. REGISTER STUDENT FOR A COURSE
	// Check whether student and course exists
	public static boolean checkCanRegister(String matric, String courseID) {
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
	public static boolean addStudentToCourse(String matric, String courseID, int tutIndex, int labIndex) {
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
		
		// Add objects in objects
		tempC.addStudent(tempS);
		Lesson lab = tempC.getLessonList().get(labIndex-1);
		Lesson tut = tempC.getLessonList().get(tutIndex-1);
		lab.addStudentToLesson(tempS);
		tut.addStudentToLesson(tempS);
		tempS.addCourse(tempC);
		
		// Update course
		Database.addCourse(tempC);
		Database.addStudent(tempS);
		System.out.println("Student registered.");
		return true;
	}
	
	public static void updateVacancy(String matric, String courseID, int tutIndex, int labIndex) {
		Course tempC = Database.getCourse(courseID);
		// Decrement Vacancy
		tempC.decrementVacancy();
		if (labIndex != 0)
			tempC.getLesson(labIndex).decrementVacancy();
		if (tutIndex != 0)
			tempC.getLesson(tutIndex).decrementVacancy();
	}
	
	// 4. FIND VACANCY
	public static void findVacancy(String courseID, int classIndex) {
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
	
	// 5. 
	
	
	
	// 9. Print course statistics
	// Show percentage overall, exam only, coursework only
	public static void printCourseStat(String courseID) {
		// Get course object
		Course tempC = Database.getCourse(courseID);
		if (tempC == null) {
			System.out.println("Course does not exist");
			return;
		}
		System.out.println("Student, Overall percentage, Exam percentage, Coursework percentage");
		// Get results for course and weightage
		ArrayList<Result> resultList = tempC.getResultList();
		Weightage w = tempC.getCourseWeightage();
		for (int i = 0; i < resultList.size(); i++) {
			double overallMark = w.calculateMark(resultList.get(i));
			double examMark = w.getExamMark(resultList.get(i));
			double courseworkMark = w.getCourseworkMark(resultList.get(i));
			String matric = resultList.get(i).getStudent().getMatric();
			System.out.println(matric + " " + overallMark + " " + examMark + " " + courseworkMark);
		}
	}
	
	public void printCourseAnalysis(String courseID) {
		// Get course object
		Course tempC = Database.getCourse(courseID);
		if (tempC == null) {
			System.out.println("Course does not exist");
			return;
		}
		// For each overall, exam only, coursework only
		
	}
	
	// 10. Print Student Transcript 
	public static void printStudentTranscript(String matric) {
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