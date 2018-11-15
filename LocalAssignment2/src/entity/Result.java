package entity;

import java.util.ArrayList;
import java.util.Objects;

import entity.Grade.gradeType;

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
	
	public boolean hasExamMark() {
		if (allGrades.size() == 0) {
			return false;
		}
		for (int i = 0 ; i < allGrades.size(); i ++) {
			Grade tempG = allGrades.get(i);
			if (tempG.getType() == gradeType.EXAM)
				return true;
		}
		return false;
	}
	
	public boolean hasSubComponentMark() {
		if (allGrades.size() == 0)
			return false;
		for (int i = 0; i < allGrades.size(); i ++) {
			Grade tempG = allGrades.get(i);
			if (tempG.getType() == gradeType.COURSEWORK)
				return true;
		}
		return false;
	}
}
