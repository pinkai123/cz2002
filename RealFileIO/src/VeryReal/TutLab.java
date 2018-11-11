package VeryReal;

import java.util.ArrayList;

public class TutLab {
	private String name;
	private int vacany;
	ArrayList<String> Student = new ArrayList();
	
	public TutLab(String name, int vacany) {
		this.name = name;
		this.vacany = vacany;
	}
	
	public void setStudent(String Student) {
		this.Student.add(Student);
	}
	public String getName() { return name;}
	public int getVacany() { return vacany;}
}

