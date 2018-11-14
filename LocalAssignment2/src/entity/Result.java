package entity;

import java.util.ArrayList;

import entity.Grade.TypeOfResult;

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
	
	public void addGrade(TypeOfResult type, String name, double mark) {
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
}
