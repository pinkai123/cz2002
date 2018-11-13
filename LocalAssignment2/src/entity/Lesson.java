package entity;

import java.util.ArrayList;

public class Lesson {
	public enum TypeOfLesson {LAB, TUT};
	
	private int lessonIndex;
	private int totalSize;
	private int vacancy;
	// It is small case L
	private TypeOfLesson lType;
	// Student object as attribute (Aggregation)
	private ArrayList<Student> studentList = new ArrayList<Student>();
	
	// Constructor
	public Lesson(int lessonIndex, int vacancy, TypeOfLesson lType) {
		this.lessonIndex = lessonIndex;
		this.vacancy = vacancy;
		this.totalSize = vacancy;
		this.lType = lType;
	}
	
	// Accessor
	public int getLessonIndex() {
		return lessonIndex;
	}
	public int getVacancy() {
		return vacancy;
	}
	public TypeOfLesson getLType() {
		return lType;
	}
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	// Mutator
	// Assume that index, lesson type cannot change
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	
	// Add or Change vacancy & studentList
	public void decrementVacancy() {
		vacancy --;
	}
	public void incrementVacancy(){
		vacancy++;
	}
	public void addStudentToLesson(Student newStudent) {
		studentList.add(newStudent);
	}
	public void addStudentList(ArrayList<Student> StudentList) {
		this.studentList = StudentList;
	}
}
