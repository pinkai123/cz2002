package others;

import java.util.ArrayList;
import java.util.Objects;

import entity.*;
import entity.Grade.gradeType;

/**
 * Main Controller to handle SCRAME
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 */
public class MainController {
	// 1. ADD A STUDENT
	/**
	 * Add student into the data.
	 * Return a boolean to inform whether adding is successfully.
	 * Unsuccessful adding occurs as the student already exists in the database.
	 * @param name Student's name
	 * @param matric Student's matriculation number
	 * @param email Student's school email
	 * @return true if adding of student is successful
	 */
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
	
	/**
	 * Print all students in the school.
	 */
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
	/**
	 * Check whether it is valid to add a course.
	 * Invalid if course already exists.
	 * Invalid if course coordinator does not exist.
	 * @param courseID Course's primary key
	 * @param profMatric Professor matriculation number
	 * @return true if valid to add a course
	 */
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
	
	/**
	 * Create a new course with given information regarding course.
	 * @param courseID Course's primary key
	 * @param courseName Course's name
	 * @param profMatric Professor matriculation number
	 * @param lecVac Total vacancy in course
	 * @return New course
	 */
	public static Course newCourse(String courseID, String courseName, String profMatric, int lecVac) {
		// Find professor
		Professor prof = Database.getProfessor(profMatric);
		
		// Create course object
		Course newCourse = new Course(courseID,courseName,prof,lecVac);
		return newCourse;
	}
	
	/**
	 * Add course into database.
	 * @param tempC Course
	 */
	public static void addCourse(Course tempC) {
		Professor prof = tempC.getCourseCoordinator();
		prof.addCourse(tempC);
		
		Database.addProfessor(prof);
		Database.addCourse(tempC);
		return;
	}
	
	/**
	 * Print all courses in the school.
	 */
	public static void printAllCourse() {		
		ArrayList<Course> courseList = Database.getAllCourse();
		System.out.println("############################################\n" +
		"CourseID, CourseName, CourseCoordinatorID");
		for (int i = 0; i < courseList.size(); i ++ ) {
			Course course = (Course) courseList.get(i);
			System.out.println(course.getCourseID() + "  " + course.getCourseName() + "  " 
					+ course.getCourseCoordinator().getMatric());
		}
		System.out.println("############################################");
	}
	
	// 3. REGISTER STUDENT FOR A COURSE
	/**
	 * Check whether it is valid to register student into course.
	 * Invalid when either student or course does not exist.
	 * Invalid if student has already registered for course.
	 * @param matric Student matriculation number
	 * @param courseID Course primary key
	 * @return true if valid to register student 
	 */
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
			if (s.getMatric().equals(tempS.getMatric())) {
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
	
	/**
	 * Try to register student into a course. 
	 * Will not register is no vacancy in lab or tutorial.
	 * @param matric Student matriculation number
	 * @param courseID Course's primary key
	 * @param tutIndex Tutorial's primary key
	 * @param labIndex Laboratory's primary key
	 * @return true if successfully register student into course
	 */
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
		try {
			Lesson lab = tempC.getLessonList().get(labIndex-1);
			lab.addStudentToLesson(tempS);
		} catch (IndexOutOfBoundsException e) {}
		try {
			Lesson tut = tempC.getLessonList().get(tutIndex-1);
			tut.addStudentToLesson(tempS);
		} catch (IndexOutOfBoundsException e) {}

		tempS.addCourse(tempC);
		
		// Update course
		Database.addCourse(tempC);
		Database.addStudent(tempS);
		System.out.println("Student registered.");
		return true;
	}
	
	/**
	 * Update vacancy of course, laboratory and tutorial
	 * @param courseID Course's primary key
	 * @param tutIndex Tutorial's primary key
	 * @param labIndex Laboratory's primary key
	 */
	public static void updateVacancy(String courseID, int tutIndex, int labIndex) {
		Course tempC = Database.getCourse(courseID);
		// Decrement Vacancy
		tempC.decrementVacancy();
		if (labIndex != 0)
			tempC.getLesson(labIndex).decrementVacancy();
		if (tutIndex != 0)
			tempC.getLesson(tutIndex).decrementVacancy();
	}
	
	// 4. FIND VACANCY
	/**
	 * Print the vacancy of a lesson in a particular course.
	 * Accounts for non-existent course and lesson.
	 * @param courseID Course's primary key
	 * @param classIndex Lesson's primary key
	 */
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
	
	
	// 9. Print course statistics	
	/**
	 * Display course statistics.
	 * Show percentage overall, exam only, coursework only for each student.
	 * @param courseID Course's primary key
	 */
	public static void printCourseStat(String courseID) {
		// Check if course exists
		Course tempC = Database.getCourse(courseID);
		if (tempC == null) {
			System.out.println("Course does not exist");
			return;
		}
		
		// If no student
		if (tempC.getStudentList().size() == 0) {
			System.out.println("No student in course.");
			return;
		}
		// check if no results
		else if (tempC.getResultList().size() == 0) {
			System.out.println("This course has no results yet.");
			return;
		}
		System.out.println("Printing course statistics");
		System.out.println("(-1 means incomplete data)");
		
		// Get results for course and weightage
		Weightage w = tempC.getCourseWeightage();
		ArrayList<Result> resultList = tempC.getResultList();
		ArrayList<Student> studentList = tempC.getStudentList();
		for (int i = 0; i < studentList.size(); i ++) {
			for (int j = 0; j < resultList.size(); j ++) {
				Result tempR = resultList.get(j);
				String matric = tempR.getStudent().getMatric();
				if (Objects.equals(studentList.get(i).getMatric(), matric)) {
					double courseworkMark = w.getCourseworkMark(tempR);
					double examMark = w.getExamMark(tempR);
					double overallMark = w.getOverallMark(examMark, courseworkMark);
					
					System.out.println("Matric: " + matric + ", Overall: " + overallMark + "%, Exam: " + examMark + "%, Coursework: " + courseworkMark
							+ "%");
					break;
				}
			}
		}
		
	}
	
	/**
	 * Print course analysis.
	 * Print minimum, maximum and average of results.
	 * @param courseID Course's primary key
	 */
	public static void printCourseAnalysis(String courseID) {
		// Get course object
		Course tempC = Database.getCourse(courseID);
		if (tempC == null) {
			System.out.println("Course does not exist");
			return;
		}
		
		// check if no results
		if (tempC.getResultList().size() == 0) {
			System.out.println("This course has no results yet.");
			return;
		}
		
		Weightage w = tempC.getCourseWeightage();		
		
		// {overall, exam, coursework}
		double[] min = {101,101,101};
		double[] max = {-1,-1,-1};
		double[] total = {-1,-1,-1};
		int[] count = {0,0,0};
		
		// For each overall, exam only, coursework only
		ArrayList<Result> resultList = tempC.getResultList();
		for (int i = 0; i < resultList.size(); i ++) {
			Result tempR = resultList.get(i);
			double courseworkMark = w.getCourseworkMark(tempR);
			double examMark = w.getExamMark(tempR);
			double overallMark = w.getOverallMark(examMark, courseworkMark);
			
			if (courseworkMark != -1) {
				if (courseworkMark < min[2])
					min[2] = courseworkMark;
				if (courseworkMark > max[2])
					max[2] = courseworkMark;
				count[2] ++;
				total[2] += courseworkMark;
			}
			
			if (examMark != -1) {
				if (examMark < min[1])
					min[1] = examMark;
				if (examMark > max[1])
					max[1] = examMark;
				count[1] ++;
				total[1] += examMark;
			}
			
			if (overallMark != -1) {
				if (overallMark < min[0])
					min[0] = overallMark;
				if (overallMark > max[0])
					max[0] = overallMark;
				count[0] ++;
				total[0] += overallMark;
			}
		}
		
		// Print results
		System.out.println(tempC.getCourseID());
		System.out.println("overall, exam, coursework");
		System.out.println("Min: " + min[0] + " ," + min[1] + " ," + min[2]);
		System.out.println("Max: " + max[0] + " ," + max[1] + " ," + max[2]);
		double[] average = {total[0]/count[0],total[1]/count[1],total[2]/count[2]};
		System.out.println("Average: " + average[0] + " ," + average[1] + " ," + average[2]);
		
	}
	
	// 10. Print Student Transcript
	/**
	 * Print Student Transcript.
	 * For one student, print their overall grade and marks, and marks of each components for every course he/she registered in.
	 * @param matric Student matriculation number
	 */
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
			
			double examMark = w.getExamMark(tempR);
			double courseworkMark = w.getCourseworkMark(tempR);
			double overallMark = w.getOverallMark(examMark, courseworkMark);
			System.out.println("Printing student transcript");
			System.out.println("COURSE: " + sResult.get(j).getCourse().getCourseID());
			if (overallMark == -1) {
				System.out.println("Unable to generate overallmark as courseworkMark or examMark is incomplete.");
			}
			else {
				String overallGrade = w.calculateGrade(overallMark);
				System.out.println("Overall Marks: " + overallMark + ", Overall Grade: " + overallGrade);
			}
			
			// Print results
			w.printMarks(tempR);
			
		}
	}
	
	
	/**
	 * Add result into database, along with grade.
	 * @param result Result 
	 * @param type Type of grade 
	 * @param name Name of grade
	 * @param mark Mark of grade
	 */
	public static void addResult(Result result, gradeType type, String name, double mark) {
		result.addGrade(type, name, mark);
		
		// link to student
		String matric = result.getStudent().getMatric();
		Student tempS = Database.getStudent(matric);
		
		tempS.addResult(result);
		
		// link to course
		String courseID = result.getCourse().getCourseID();
		Course tempC = Database.getCourse(courseID);
		
		tempC.addResult(result);
		
		Database.addCourse(tempC);
		Database.addResult(result);
		Database.addStudent(tempS);
	}
}
