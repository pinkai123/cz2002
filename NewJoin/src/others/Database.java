package others;

import java.util.ArrayList;
import java.util.Objects;

import entity.*;

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
	public static void addResult(Result newR) {
		// Check whether new
		result.add(newR);
	}
	public static void addStudent(Student newS) {
		// Check whether new
		student.add(newS);
	}
	public static void addProfessor(Professor newP) {
		// Check whether new
		prof.add(newP);
	}
	
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
}
