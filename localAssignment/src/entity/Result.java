package entity;

import java.util.ArrayList;
import fileIO.*;
import others.*;

public class Result {
	private Course Course;
	private Student Student;
	private ArrayList<Grade> AllGrades = new ArrayList();
	
	//Constructor
	public Result(Course Course, Student Student) {
		this.Course =Course;
		this.Student = Student;
	}
	
	public void addGrade(String type,String name, double mark) {
		Grade Grade = new Grade(type,name,mark);
		AllGrades.add(Grade);
	}
	
	//Accessor
	public Course getCourse() {
		return Course;
	}
	public Student getStudent() {
		return Student;
	}
	public ArrayList getAllGrades() {
		return AllGrades;
	}
}
