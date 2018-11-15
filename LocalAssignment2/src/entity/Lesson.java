package entity;

import java.util.ArrayList;

/**
 * Represents a tutorial or laboratory session group.
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 *
 */
public class Lesson {
	/**
	 * Represents the different of session group or lesson.
	 * @author SS5 Group 4
	 * @version 1.0
	 * @since 2018-11-15
	 */
	public enum TypeOfLesson {LAB, TUT};
	
	/**
	 * This lesson's primary key.
	 */
	private int lessonIndex;
	
	/**
	 * Total number of students that this lesson has cater to.
	 */
	private int totalSize;
	
	/**
	 * The vacancy for this lesson.
	 */
	private int vacancy;
	
	/**
	 * The type of lesson for this lesson.
	 */
	// It is small case L
	private TypeOfLesson lType;
	
	/**
	 * All students who registered for this lesson.
	 */
	// Student object as attribute (Aggregation)
	private ArrayList<Student> studentList = new ArrayList<Student>();
	
	/**
	 * Create a new lesson or session group.
	 * @param lessonIndex This lesson's primary key
	 * @param vacancy This lesson's vacancy
	 * @param lType This lesson's type
	 */
	// Constructor
	public Lesson(int lessonIndex, int vacancy, TypeOfLesson lType) {
		this.lessonIndex = lessonIndex;
		this.vacancy = vacancy;
		this.totalSize = vacancy;
		this.lType = lType;
	}
	
	// Accessor
	/**
	 * Get this lesson's primary key.
	 * @return This lesson's primary key
	 */
	public int getLessonIndex() {
		return lessonIndex;
	}
	
	/**
	 * Get the vacancy available for this lesson.
	 * @return This lesson's vacancy
	 */
	public int getVacancy() {
		return vacancy;
	}
	
	/**
	 * Get the type of this lesson.
	 * @return This lesson's session group type
	 */
	public TypeOfLesson getLType() {
		return lType;
	}
	
	/**
	 * Get all students who registered for this lesson.
	 * @return All students in this lesson
	 */
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	/**
	 * Get the total number of students can this lesson cater to.
	 * @return This lesson's total size
	 */
	public int getTotalSize() {
		return totalSize;
	}
	
	// Mutator
	// Assume that index, lesson type cannot change
	/**
	 * Changes the vacancy for this lesson.
	 * @param vacancy This lesson's new vacancy
	 */
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	
	/**
	 * Decrease the number of vacancies by 1.
	 */
	public void decrementVacancy() {
		vacancy --;
	}
	
	/**
	 * Increase the number of vacancies by 1.
	 */
	public void incrementVacancy(){
		vacancy++;
	}
	
	/**
	 * Add a new student into this lesson.
	 * @param newStudent New student who successfully registered for this lesson
	 */
	public void addStudentToLesson(Student newStudent) {
		studentList.add(newStudent);
	}
	
	/**
	 * Change the students in this lesson.
	 * @param StudentList New students in this lesson
	 */
	public void addStudentList(ArrayList<Student> StudentList) {
		this.studentList = StudentList;
	}
}
