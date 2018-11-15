package entity;

import java.util.ArrayList;
import java.util.Objects;

import entity.Grade.gradeType;

/**
 * Represents the storage of marks and scores of a particular course and a particular student.
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 */
public class Result {
	/**
	 * The course which the result is specified on.
	 */
	private Course course;
	
	/**
	 * The student which the result is specified on.
	 */
	private Student student;
	
	// Grade objects as attributes (Composition)
	/**
	 * All the grades of all components for this result.
	 */
	private ArrayList<Grade> allGrades = new ArrayList<Grade>();
	
	/**
	 * Create new result.
	 * @param course This result's course
	 * @param student This result's student
	 */
	public Result(Course course, Student student) {
		this.course = course;
		this.student = student;
	}
	
	/**
	 * Add a grade to the list of grades in this result.
	 * @param type Type of this new result's grade
	 * @param name Name of this new result's grade
	 * @param mark Mark of this new result's grade
	 */
	public void addGrade(Grade.gradeType type, String name, double mark) {
		Grade grade = new Grade(type,name,mark);
		allGrades.add(grade);
	}
	
	// Accessor
	/**
	 * Get the course corresponding to this result.
	 * @return This result's course
	 */
	public Course getCourse() {
		return course;
	}
	
	/**
	 * Get the student corresponding to this result.
	 * @return This result's student
	 */
	public Student getStudent() {
		return student;
	}
	
	/**
	 * Get all grades in this result.
	 * @return All grade in this result
	 */
	public ArrayList<Grade> getAllGrades() {
		return allGrades;
	}
	
	/**
	 * Check whether the student this result belongs to is the same as the given student.
	 * @param matric Given student's matriculation number
	 * @return true if the result belongs to the student
	 */
	public boolean isStudent(String matric) {
		if (Objects.equals(matric, student.getMatric()))
			return true;
		else
			return false;
	}
	
	/**
	 * Check whether this result contains exam marks
	 * @return true if this result has exam marks
	 */
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
	
	/**
	 * Check whether this result contains subcomponent marks
	 * @return true if this result has subcomponent marks
	 */
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
