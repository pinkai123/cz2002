package entity;

import java.util.ArrayList;
import fileIO.*;
import others.*;

public class Result {
	private String Course;
	private String Student;
	private ArrayList<Grade> AllGrades = new ArrayList();
	
	//Constructor
	public Result(String Course, String Student) {
		this.Course =Course;
		this.Student = Student;
	}
	
	public void addGrade(String type,String name, double mark) {
		Grade Grade = new Grade(type,name,mark);
		AllGrades.add(Grade);
	}
	
	//Accessor
	public String getCourse() {
		return Course;
	}
	public String Student() {
		return Student;
	}
	public ArrayList getAllGrades() {
		return AllGrades;
	}
}
