package others;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import entity.*;
import fileIO.*;

public class FindByID {
	public static Student findStudent(String id) {
		FileIO IO = new StudentIO();
		ArrayList<Student> studentList = null;
		Student result = null;
		try {
			studentList = IO.readData();
		} catch (IOException e) {
			System.err.println("StudentIO has problems.");
		}
		for (int i = 0; i < studentList.size(); i ++) {
			result = (Student)studentList.get(i);
			if (Objects.equals(id, result.getMatric()))
				break;
		}
		return result;
	}
	
	public static Course findCourse(String id) {
		FileIO IO = new CourseIO();
		ArrayList<Course> courseList = null;
		Course result = null;
		try {
			courseList = IO.readData();
		} catch (IOException e) {
			System.err.println("CourseIO has problems.");
		}
		for (int i = 0; i < courseList.size(); i ++) {
			result = (Course)courseList.get(i);
			if (Objects.equals(id, result.getCourseID()))
				break;
		}
		return result;
	}
	
	public static Professor findProfessor(String id) {
		FileIO IO = new ProfessorIO();
		ArrayList<Professor> professorList = null;
		Professor result = null;
		try {
			professorList = IO.readData();
		} catch (IOException e) {
			System.err.println("professorIO has problems.");
		}
		for (int i = 0; i < professorList.size(); i ++) {
			result = (Professor)professorList.get(i);
			if (Objects.equals(id, result.getMatric()));
				break;
		}
		return result;
	}
	
	
}
