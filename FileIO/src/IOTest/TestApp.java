package IOTest;

import java.io.IOException;
import java.util.ArrayList;

public class TestApp {
	public static void main(String[] aArgs)  {
    	FileIO IO = new CourseIO();
		String filename = "TutLab.txt" ;
		try {
			// read file containing TutLab records.
			ArrayList al = IO.readData(filename) ;
			System.out.println(al.get(0));
			//System.out.println(al.size());
			for (int i = 1 ; i < al.size() ; i++) {
					TutLab data1 = (TutLab)al.get(i);
					System.out.println("Name " + data1.getName() );
					System.out.println("Vacany " + data1.getVacany() );
			}
			TutLab p1 = new TutLab("FSP6",11);
			// al is an array list containing TutLab objs
			al.add("CZ2002");
			al.add(p1);
			// write TutLab record/s to file.
			IO.saveData("CZ2002.txt", al);
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
  }

}
