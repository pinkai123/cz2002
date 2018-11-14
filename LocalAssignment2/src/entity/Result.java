package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Result {
	private Course course;
	private Student student;
	// Grade objects as attributes (Composition)
	private ArrayList<Grade> allGrades = new ArrayList<Grade>();
	
	// Constructor
	public Result(Course course, Student student) {
		this.course = course;
		this.student = student;
	}
	
	public void addGrade(Grade.gradeType type, String name, double mark) {
		Grade grade = new Grade(type,name,mark);
		allGrades.add(grade);
	}
	
	// Accessor
	public Course getCourse() {
		return course;
	}
	public Student getStudent() {
		return student;
	}
	public ArrayList<Grade> getAllGrades() {
		return allGrades;
	}
	
	public boolean isStudent(String matric) {
		if (Objects.equals(matric, student.getMatric()))
			return true;
		else
			return false;
	}
}
