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
	
	// Validation
	public static boolean checkValidCourseName(String courseName) {
		if (courseName.length() == 6)
			return true;
		else
			return false;
	}
}
