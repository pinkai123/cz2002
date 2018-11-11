package IOTest;

import java.util.ArrayList;

public class Course {
	private String name;
	private ArrayList<TutLab> TutLab = null;
	
	public Course( String name, ArrayList a) {
		this.name = name;
		for(int i = 1; i< a.size()-1;i++) {
			TutLab Lesson = (TutLab)a.get(i);
			TutLab.add(Lesson);
		}
	}
}
