package others;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import entity.*;
import fileIO.CourseIO;
import fileIO.FileIO;
import fileIO.ProfessorIO;
import fileIO.ResultIO;
import fileIO.StudentIO;

public class Database {
	// Contains all data
	private static ArrayList<Course> course = new ArrayList<Course>();
	private static ArrayList<Professor> prof = new ArrayList<Professor>();
	private static ArrayList<Result> result = new ArrayList<Result>();
	private static ArrayList<Student> student = new ArrayList<Student>();
	
	// Accessor
	public static ArrayList<Course> getAllCourse() {
		return course;
	}
	public static ArrayList<Professor> getAllProfessor() {
		return prof;
	}
	public static ArrayList<Result> getAllResult() {
		return result;
	}
	public static  ArrayList<Student> getAllStudent() {
		return student;
	}
	
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
	
	// Mutator
	public static  void addCourse(Course newC) {
		// Check whether new
		course.add(newC);
	}
	public static void addResult(Result newR) {
		// Check whether new
		result.add(newR);
	}
	public static void addStudent(Student newS) {
		// Check whether new
		student.add(newS);
	}
	
	public static void updateCourse(Course newC) {
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
	// get Data from txt file
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
	//write data to text file
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
