package others;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import IO.*;
import entity.*;
/**
 * Database that retrieves data from IOfile and provides data to other classes.
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 */
public class Database {
	// Contains all data
	/**
	 * All courses from the university.
	 */
	private static ArrayList<Course> course = new ArrayList<Course>();
	/**
	 * All profesors from the university.
	 */
	private static ArrayList<Professor> prof = new ArrayList<Professor>();
	/**
	 * All results from every course and student.
	 */
	private static ArrayList<Result> result = new ArrayList<Result>();
	/**
	 * All students from the university.
	 */
	private static ArrayList<Student> student = new ArrayList<Student>();
	
	// Accessor
	/**
	 * Get all courses that are from the university.
	 * @return All courses
	 */
	public static ArrayList<Course> getAllCourse() {
		return course;
	}
	
	/**
	 * Get all professors who are from the university.
	 * @return All professors
	 */
	public static ArrayList<Professor> getAllProfessor() {
		return prof;
	}
	
	/**
	 * Get all results from every course and student.
	 * @return All results
	 */
	public static ArrayList<Result> getAllResult() {
		return result;
	}
	
	/**
	 * Get all students who are from the university.
	 * @return
	 */
	public static  ArrayList<Student> getAllStudent() {
		return student;
	}
	
	/**
	 * Find a course with a given course primary key.
	 * @param ID Course's primary key
	 * @return Course or null if no course 
	 */
	public static Course getCourse(String ID) {
		Course result = null;
		for (int i = 0; i < course.size(); i ++) {
			if (Objects.equals(ID, course.get(i).getCourseID())) {
				result = course.get(i);
				break;
			}
		}
		return result;
	}
	/**
	 * Find a professor with a given matriculation number.
	 * @param ID Professor matriculation number
	 * @return Professor or null if no professor
	 */
	public static Professor getProfessor(String ID) {
		Professor result = null;
		for (int i = 0; i < prof.size(); i ++) {
			if (Objects.equals(ID, prof.get(i).getMatric())) {
				result = prof.get(i);
				break;
			}
		}
		return result;
	}
	
	/**
	 * Find a student with a given matriculation number.
	 * @param ID Student matriculation number
	 * @return Student or null if no student
	 */
	public static Student getStudent(String ID) {
		Student result = null;
		for (int i = 0; i < student.size(); i ++) {
			if (Objects.equals(ID, student.get(i).getMatric())) {
				result = student.get(i);
				break;
			}
		}
		return result;
	}
	
	/**
	 * Find a result with given primary keys.
	 * @param matric Student matriculation number
	 * @param courseID Course's primary key
	 * @return Result or null if no result
	 */
	public static Result getResult(String matric, String courseID) {
		Result r = null;
		for (int i = 0; i < result.size(); i ++) {
			if (Objects.equals(matric, result.get(i).getStudent().getMatric()) & Objects.equals(courseID,result.get(i).getCourse().getCourseID())) {
				r = result.get(i);
				break;
			}
		}
		return r;
	}
	
	/**
	 * Find a lesson with given primary keys.
	 * @param Course Course
	 * @param LessonID Lesson's primary key
	 * @param Type Lesson type
	 * @return Lesson or null if no lesson
	 */
	public static Lesson getTutLab(Course Course, int LessonID, Lesson.TypeOfLesson Type) {
		ArrayList <Lesson> Lessons = null;
		Lessons = Course.getLessonList();
		for (int i = 0; i < Lessons.size(); i ++) {
			Lesson Lesson = Lessons.get(i);
			if(Type == Lesson.getLType() && LessonID == Lesson.getLessonIndex()) {
				return Lesson;
			}
		}
		return null;
	}
	
	// Mutator
	/**
	 * Add result into database.
	 * @param newR Result to be added in
	 */
	public static void addResult(Result newR) {
		// Check whether new
		int i;
		boolean isExisting = false;
		for (i = 0; i < result.size(); i ++) {
			Result tempR = result.get(i);
			if (Objects.equals(tempR.getStudent().getMatric(), newR.getStudent().getMatric()))
				if (Objects.equals(tempR.getCourse().getCourseID(), newR.getCourse().getCourseID())) {
					isExisting = true;
					break;
				}
		}
		if (isExisting) {
			result.set(i, newR);
		}
		else {
			result.add(newR);
		}
	}
	
	/**
	 * Add student into database.
	 * @param newS Student to be added in
	 */
	public static void addStudent(Student newS) {
		// Check whether new
		int i;
		boolean isExisting = false;
		for (i = 0; i < student.size(); i ++) {
			Student tempS = student.get(i);
			if (Objects.equals(newS.getMatric(),tempS.getMatric())) {
				isExisting = true;
				break;
			}
		}
		if (isExisting) {
			student.set(i, newS);
		}
		else {
			student.add(newS);
		}
	}
	
	/**
	 * Add professor into database.
	 * @param newP Professor to be added in
	 */
	public static void addProfessor(Professor newP) {
		int i; 
		boolean isExisting = false;
		for (i = 0; i < prof.size(); i ++) {
			Professor tempP = prof.get(i);
			if (Objects.equals(tempP.getMatric(), newP.getMatric())) {
				isExisting = true;
				break;
			}
		}
		if (isExisting) {
			prof.set(i, newP);
		}
		else {
			prof.add(newP);
		}
	}
	
	/**
	 * Add course into database.
	 * @param newC Course to be added in 
	 */
	public static void addCourse(Course newC) {
		int i;
		for (i = 0; i < course.size(); i ++) {
			if (Objects.equals(newC.getCourseID(), course.get(i).getCourseID())) {
				break;
			}
		}
		if (i != course.size()) {
			course.set(i,newC);
		}
		else {
			course.add(newC);
		}
	}
	
	/**
	 * Get all data from text file.
	 */
	public static void getIO() {
		FileIO IO = new CourseIO();
		try {
			course = IO.readData();
		} catch (IOException e) {
			System.out.println("No courses has been created yet.");
		}
		IO = new StudentIO();
		try {
			student = IO.readData();
		} catch (IOException e) {
			System.out.println("No student has being added yet.");
		}
		IO = new ProfessorIO();
		try {
			prof = IO.readData();
		} catch (IOException e) {
			System.out.println("No professor has being added yet.");
		}
		IO =  new ResultIO();
		try {
			result = IO.readData();
		} catch (IOException e) {
			System.out.println("No result has been created yet.");
		}
	}
	
	/**
	 * Write all data into text file
	 */
	public static void returnIO() {
		FileIO IO = new CourseIO();
		try {
			IO.saveData(course);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IO = new StudentIO();
		try {
			IO.saveData(student);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IO = new ProfessorIO();
		try {
			IO.saveData(prof);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IO =  new ResultIO();
		try {
			IO.saveData(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No result has been created.");
			}
	}
}

