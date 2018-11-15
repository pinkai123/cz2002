package entity;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a course available in the school.
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 */
public class Course {
	/**
	 * The primary key of the course.
	 */
	private String courseID;
	/**
	 * The name of the course.
	 */
	private String courseName;
	/**
	 * The number of vacancies in the course.
	 * Indicates how many student can enter the course.
	 */
	private int vacancy;
	
	// Weightage and Lesson objects as attributes (Composition)
	/**
	 * Consists of the information of the course's grading distribution.
	 */
	private Weightage courseWeightage;
	/**
	 * All of the course's laboratory session and tutorial session.
	 */
	private ArrayList<Lesson> lessonList = new ArrayList<Lesson>();
	
	// Student, Result and Professor objects as attributes (Association)
	/**
	 * All students who registered in the course.
	 */
	private ArrayList<Student> studentList = new ArrayList<Student>();
	/**
	 * All results pertaining to the course.
	 */
	private ArrayList<Result> resultList = new ArrayList<Result>();
	/**
	 * The professor in-charge of the course.
	 */
	private Professor courseCoordinator;
	
	// Constructor
	/**
	 * Create a new course.
	 * @param courseID This course's primary key
	 * @param courseName This course's name
	 * @param courseCoordinator This course's coordinator
	 * @param vacancy This course's overall vacancy
	 */
	public Course(String courseID, String courseName, Professor courseCoordinator, int vacancy) {
		this.courseCoordinator = courseCoordinator;
		this.courseID = courseID;
		this.courseName = courseName;
		this.vacancy = vacancy;
	}
	
	// Accessor
	/** 
	 * Get the course's primary key.
	 * @return This course's primary key
	 */
	public String getCourseID(){
		return courseID;
	}
	
	/**
	 * Get the course's name.
	 * @return This course's name
	 */
	public String getCourseName(){
		return courseName;
	}
	
	/**
	 * Get the professor coordinating the course.
	 * @return This course's coordinator
	 */
	public Professor getCourseCoordinator(){
		return courseCoordinator;
	}
	
	/**
	 * Get the course vacancy.
	 * @return This course's vacancy
	 */
	public int getVacancy() {
		return vacancy;
	}
	
	/**
	 * Get the course weightage.
	 * @return This course's weightage
	 */
	public Weightage getCourseWeightage(){
		return courseWeightage;
	}
	
	/**
	 * Get all student who successfully registered for the course.
	 * @return All registered students in this course
	 */
	public ArrayList<Student> getStudentList(){
		return studentList;
	}
	
	/**
	 * Get all lab and tutorial sessions of the course.
	 * @return All of this course's lesson
	 */
	public ArrayList<Lesson> getLessonList(){
		return lessonList;
	}
	
	/**
	 * Get all the results from this course.
	 * @return All results in this course
	 */
	public ArrayList<Result> getResultList(){
		return resultList;
	}
	
	// Mutators
	/**
	 * Changes the course's primary key.
	 * @param courseID This course's new primary key
	 */
	public void setCourseID(String courseID){
		this.courseID = courseID;
	}
	
	/**
	 * Changes the course's name.
	 * @param courseName This course's new name
	 */
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	
	/**
	 * Changes the professor in-charge of this course.
	 * @param courseCoordinator This course's new coordinator
	 */
	public void setCourseCoordinator(Professor courseCoordinator){
		this.courseCoordinator = courseCoordinator;
	}
	
	/**
	 * Changes the course's weightage.
	 * @param courseWeightage This course's new weightage
	 */
	public void setCourseWeightage(Weightage courseWeightage){
		this.courseWeightage = courseWeightage;
	}
	
	/** 
	 * Changes the group of students who registered for this course.
	 * @param studentList This course's new students
	 */
	public void setStudentList(ArrayList<Student> studentList){
		this.studentList = studentList;
	}
	
	/** 
	 * Changes the results in this course.
	 * @param resultList This course's results
	 */
	public void setResultList(ArrayList<Result> resultList){
		this.resultList = resultList;
	}
	
	/**
	 * Changes the tutorial and laboratory sessions in this courses.
	 * @param LessonList This course's new tutorial and laboratory sessions
	 */
	public void setLessonList(ArrayList<Lesson> LessonList){
		this.lessonList = LessonList;
	}
	
	/**
	 * Decrease the vacancy for this course by 1.
	 */
	public void decrementVacancy() {
		this.vacancy--;
	}
	
	/**
	 * Create a new weightage for the course.
	 * @param mainPercentage This course's weightage's main exam percentage in overall assessment
	 * @param courseworkPercentage This course's weightage's coursework percentage in overall assessment
	 * @param haveSub
	 */
	public void addWeightage(double mainPercentage, double courseworkPercentage,boolean haveSub) {
		courseWeightage = new Weightage(mainPercentage, courseworkPercentage, haveSub);
	}
	
	/** 
	 * Create the subcomponent in course's weightage.
	 * @param name The subcomponent name in course's weightage
	 * @param percentage The subcomponent percentage in course's weight
	 */
	public void addSubcomponent(String name, double percentage) {
		courseWeightage.setSubcomponent(name, percentage);
	}
	
	/** 
	 * Add tutorial or laboratory sessions in this course.
	 * @param lessonIndex The primary key of lesson in this course
	 * @param vac The vacancy of lesson in this course
	 * @param lType The type of lesson in this course
	 */
	public void addLesson(int lessonIndex, int vac, Lesson.TypeOfLesson lType) {
		Lesson newLesson = new Lesson(lessonIndex,vac,lType);
		lessonList.add(newLesson);
	}
	
	/** 
	 * Add new student who has registered for the course.
	 * @param newStudent This course's new registered student
	 */
	public void addStudent(Student newStudent) {
		studentList.add(newStudent);
	}
	
	/**
	 * Add new result obtained regarding this course.
	 * @param newResult This course's new result
	 */
	public void addResult(Result newResult) {
		resultList.add(newResult);
	}

	/**
	 * Check whether the course's primary key is of a valid format.
	 * @param courseName This course's primary key
	 * @return true if the format is correct
	 */
	public static boolean isValidCourseID(String courseName) {
		if (courseName.length() != 6)
			return false;
		for (int i = 0; i < courseName.length(); i ++) {
			char c = courseName.charAt(i);
			if (c >= 'A' & c <= 'Z' | c >='0' & c<='9')
				continue;
			else
				return false;
		}
		return true;
	}
	
	/**
	 * Check whether student has registered in this course.
	 * @param S Student
	 * @return true if student registered in this course
	 */
	public boolean studentInCourse(Student S) {
		for( int i =0; i< studentList.size();i++) {
			if(studentList.get(i).getName().equals(S.getName()))
				return true;
		}
			return false;
	}
	
	/**
	 * Count the total number of tutorial and laboratory sessions in this course.
	 * @return This course's total number of tutorial and laboratory sessions
	 */
	public int[] countLessons() {
		// NumofTut, NumOfLab
		int[] countL = {0,0};
		if (lessonList.isEmpty())
			return countL;
		for (int i = 0; i < lessonList.size(); i ++) {
			if (lessonList.get(i).getLType() == Lesson.TypeOfLesson.LAB)
				countL[1] ++;
			else
				countL[0] ++;
		}
		return countL;
	}
	
	/**
	 * Get the lesson with a given lesson primary key in this course.
	 * @param index lesson's primary key
	 * @return lesson if valid primary key or null if otherwise
	 */
	public Lesson getLesson(int index) {
		Lesson temp = null;
		for (int i = 0; i < lessonList.size(); i ++) {
			if (lessonList.get(i).getLessonIndex() == index) {
				temp = lessonList.get(i);
			}
		}
		return temp;
	}
}
