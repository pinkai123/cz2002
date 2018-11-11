package entity;

import java.util.ArrayList;

public class Course {
	private String courseID;
	private String courseName;
	private Professor courseCoordinator;
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private Weightage courseWeightage;
	private ArrayList<Lesson> lessonList = new ArrayList<Lesson>();
	
	// Constructor 
	public Course(String courseID, String courseName, Professor courseCoordinator, ArrayList<Lesson> lessonList) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseCoordinator = courseCoordinator;
		this.lessonList = lessonList; 
	}
	
	//
	public Lesson[] getLessons() {
		return Lessons;
	}
	
	// Validation
	public static boolean checkValidCourseName(String courseName) {
		if (courseName.length() == 6)
			return true;
		else
			return false;
	}
	
	
	
	//ACCESSORS MY NIGGA
	public String getCourseID(){
		return courseID;
	}
	public String getCourseName(){
		return courseName;
	}
	public Professor getCourseCoordinator(){
		return courseCoordinator;
	}
	public Weightage getCourseWeightage{
		return courseWeightage;
	}
	public ArrayList<Student> getStudentList{
		return studentList;
	}
	public ArrayList<Lesson> getLessonList{
		return lessonList;
	}
	
	//MUTATORS MY NIGGA
	
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
	public void setLessonList(ArrayList<Lesson> lessonList){
		this.lessonList = lessonList;
	}
	
	
	
	
}
