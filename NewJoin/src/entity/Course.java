package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Course {
	private String courseID;
	private String courseName;
	private int vacancy;
	
	// Weightage and Lesson objects as attributes (Composition)
	private Weightage courseWeightage;
	private ArrayList<Lesson> lessonList = new ArrayList<Lesson>();
	
	// Student, Result and Professor objects as attributes (Association)
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private ArrayList<Result> resultList = new ArrayList<Result>();
	private Professor courseCoordinator;
	
	// Constructor
	public Course(String courseID, String courseName, Professor courseCoordinator, int vacancy) {
		this.courseCoordinator = courseCoordinator;
		this.courseID = courseID;
		this.courseName = courseName;
		this.vacancy = vacancy;
	}
	
	// Accessor
	public String getCourseID(){
		return courseID;
	}
	public String getCourseName(){
		return courseName;
	}
	public Professor getCourseCoordinator(){
		return courseCoordinator;
	}
	public int getVacancy() {
		return vacancy;
	}
	public Weightage getCourseWeightage(){
		return courseWeightage;
	}
	public ArrayList<Student> getStudentList(){
		return studentList;
	}
	public ArrayList<Lesson> getLessonList(){
		return lessonList;
	}
	public ArrayList<Result> getResultList(){
		return resultList;
	}
	
	// Mutators
	public void setCourseID(String courseID){
		this.courseID = courseID;
	}
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	public void setCourseCoordinator(Professor courseCoordinator){
		this.courseCoordinator = courseCoordinator;
	}
	public void setCourseWeightage(Weightage courseWeightage){
		this.courseWeightage = courseWeightage;
	}
	public void setStudentList(ArrayList<Student> studentList){
		this.studentList = studentList;
	}
	public void setResultList(ArrayList<Result> resultList){
		this.resultList = resultList;
	}
	public void decrementVacancy() {
		this.vacancy--;
	}
	
	public void addWeightage(double mainPercentage, double courseworkPercentage,boolean haveSub) {
		courseWeightage = new Weightage(mainPercentage, courseworkPercentage, haveSub);
	}
	public void addSubcomponent(String name, double percentage) {
		courseWeightage.setSubcomponent(name, percentage);
	}
	public void addLesson(int lessonIndex, int vac, Lesson.TypeOfLesson lType) {
		Lesson newLesson = new Lesson(lessonIndex,vac,lType);
		lessonList.add(newLesson);
	}
	public void addStudent(Student newStudent) {
		studentList.add(newStudent);
	}
	public void addResult(Result newResult) {
		resultList.add(newResult);
	}

	// Other Methods
	// Check whether the format of courseID is valid
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
	
	// Check whether student in course
	public boolean studentInCourse(Student S) {
		if (studentList.contains(S))
			return true;
		else
			return false;
	}
	
	// Count the num of tut and lab
	// NumofTut, NumOfLab
	public int[] countLessons() {
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
	
	// Get Lesson object with lesson index
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
