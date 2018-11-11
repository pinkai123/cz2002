package VeryReal;

import java.util.ArrayList;

public class Course {
	private String name;
	private ArrayList<Lesson> Lessons = null;
	
	public Course( String name, ArrayList a) {
		this.name = name;
		for(int i = 1; i< a.size()-1;i++) {
			Lesson Lesson = (Lesson)a.get(i);
			Lessons.add(Lesson);
		}
	}
	public Lesson[] getLessons() {
		return Lessons;
	}
}
