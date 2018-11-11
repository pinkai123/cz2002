package VeryReal;

import java.util.ArrayList;

public class Lesson {
	public enum TypeOfLesson {LAB, TUT};
	
	private int lessonIndex;
	private int vacancy;
	// It is small case L
	private TypeOfLesson lType;
	ArrayList<Student> studentList = new ArrayList<Student>();
	
	public Lesson(int lessonIndex, int vacancy, TypeOfLesson lType) {
		this.lessonIndex = lessonIndex;
		this.vacancy = vacancy;
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
	public void addStudentToLesson(Student newStudent) {
		studentList.add(newStudent);
	}
}