package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import fileIO.CourseIO;
import fileIO.FileIO;

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
	
	// Remove later /////////////////////////////////////////////
	public Course(String courseID, String courseName, Professor courseCoordinator, int vacancy, ArrayList<Lesson> TutLab) {
		this.courseCoordinator = courseCoordinator;
		this.courseID = courseID;
		this.courseName = courseName;
		this.vacancy = vacancy;
		this.lessonList = TutLab;
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
	public void addWeightage(double mainPercentage, double courseworkPercentage,boolean haveSub) {
		courseWeightage = new Weightage(mainPercentage, courseworkPercentage, haveSub);
	}
	public void addSubcomponent(String name, double percentage) {
		courseWeightage.setSubcomponent(name, percentage);
	}
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
	
	// Add courseWeight //////////////////////////////////
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
	
	// Check whether course exists in database
	public static boolean isExisting(String courseID){
		// Retrieve all Course Objects in text file
		ArrayList courseList = new ArrayList();
		FileIO retrieve = new CourseIO();
		try {
			courseList = retrieve.readData();
		} catch(IOException e) {
			return false;
		}
		
		// Check whether matric number is existing
		for (int i = 0; i < courseList.size(); i ++) {
			Course temp = (Course) courseList.get(i);
			if (Objects.equals(courseID, temp.courseID)) {
				return true;
			}
		}
		return false;
		
	}
	
	// Check whether student in course
	public boolean studentInCourse(Student S) {
		if (studentList.contains(S))
			return true;
		else
			return false;
	}
	
	// Count the num of tut and lab
	public int[] countLessons() {
		int[] count = {0,0};
		if (lessonList.isEmpty())
			return count;
		for (int i = 0; i < lessonList.size(); i ++) {
			if (lessonList.get(i).getLType() == Lesson.TypeOfLesson.LAB)
				count[1] ++;
			else
				count[0] ++;
		}
		return count;
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
