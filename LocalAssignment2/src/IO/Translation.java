package IO;

import java.io.IOException;
import java.util.ArrayList;

import entity.*;
/**
 * 
 * @author Kai Siang
 *
 */
public class Translation {
	/**
	 * 
	 * @param StudentName list of student name
	 * @return list of student object
	 * @throws IOException unable read the student file
	 */
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
	/**
	 * 
	 * @param Course CourseID
	 * @return Course object
	 * @throws IOException unable read the Course text file
	 */
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
	/**
	 * 
	 * @param StudentName Name of Student
	 * @return Student object
	 * @throws IOException unable to read the student text file
	 */
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
	/**
	 * 
	 * @param Matric Matriculation Number Primary Key
	 * @return Student object
	 * @throws IOException unable to read student file
	 */
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