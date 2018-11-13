package fileIO;

import java.io.IOException;
import java.util.ArrayList;

import entity.Student;

public class Translation {
	public static  ArrayList StringtoObject(ArrayList StudentName) throws IOException {
		ArrayList<Student> Cohort  = new ArrayList();
		ArrayList<Student> Final  = new ArrayList();
		FileIO IO = new StudentIO();
		Cohort = IO.readData();
		for( int i = 0; i<StudentName.size();i++) {
			for( int j = 0; j< Cohort.size();j++){
				if(Cohort.get(j).getName().equals(StudentName.get(i))) {
					Final.add(Cohort.get(j));
					//System.out.println("ADDED");
				}
			}
		}
		return Final;
	}
}
