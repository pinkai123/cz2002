package VeryReal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestApp {
	public static void main(String[] aArgs)  {
    	FileIO IO = new StudentIO();
    	Scanner sc = new Scanner(System.in);
		while (true) {
    	String filename = sc.next() ;
		try {
			// read file containing TutLab records.
			ArrayList al = IO.readData(filename);
			//System.out.println(al.size());
			for (int i = 0 ; i < al.size() ; i++) {
					Student data1 = (Student)al.get(i);
					System.out.println("Name " + data1.getName() );
					System.out.println("Vacany " + data1.getMatric() );
			}
			Student p1 = new Student("Jerv","U123124","khffff@e.ntu.edu.sg");
			// al is an array list containing TutLab objs
			al.add(p1);
			// write TutLab record/s to file.
			IO.saveData("student.txt", al);
			break;
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
			System.out.println("Please try again");
		}
		}
	}
}