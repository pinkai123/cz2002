package IO;

import java.io.IOException;
import java.util.ArrayList;

import entity.*;

public class Translation {
	public static  ArrayList StringtoStudents(ArrayList StudentName) throws IOException {
		ArrayList<Student> Cohort  = new ArrayList();
		ArrayList<Student> Final  = new ArrayList();
		FileIO IO = new StudentIO();
		Cohort = IO.readData();
		for( int i = 0; i<StudentName.size();i++) {
			for( int j = 0; j< Cohort.size();j++){
				if(Cohort.get(j).getName().equals(StudentName.get(i))) {
					Final.add(Cohort.get(j));
				}
			}
		}
		return Final;
	}
	public static Course StringtoCourse(String Course) throws IOException {
		ArrayList<Course> Courses  = new ArrayList();
		FileIO IO = new CourseIO();
		Courses = IO.readData();
		for( int i = 0; i<Courses.size();i++) {
				if(Courses.get(i).getCourseID().equals(Course))
					return Courses.get(i);
		}
		return null;
	}
	public static Student StringtoStudent(String StudentName) throws IOException {
		ArrayList<Student> Students  = new ArrayList();
		FileIO IO = new StudentIO();
		Students = IO.readData();
		for( int i = 0; i<Students.size();i++) {
				if(Students.get(i).getName().equals(StudentName))
					return Students.get(i);
		}
		return null;
	}
	public static Student MatrictoStudent(String Matric) throws IOException {
		ArrayList<Student> Students  = new ArrayList();
		FileIO IO = new StudentIO();
		Students = IO.readData();
		for( int i = 0; i<Students.size();i++) {
				if(Students.get(i).getMatric().equals(Matric))
					return Students.get(i);
		}
		return null;
	}
}