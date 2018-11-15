package others;

import java.io.IOException;
import java.util.ArrayList;

import FileIO.CourseIO;
import FileIO.FileIO;
import entity.Course;

public class Test {
	public static void main(String[]args) {
		FileIO IO = new CourseIO();
		ArrayList<Course> Courses = new ArrayList();
		try {
			Courses = IO.readData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Courses.get(0).getStudentList().get(0).getMatric());
	}
}