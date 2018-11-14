package entity;

public class Grade {
	public enum gradeType {EXAM,COURSEWORK};
	private gradeType type;
	private String name;
	private double mark = 0;
	
	// Constructor
	public Grade(gradeType type, String name, double mark) {
		this.type = type;
		this.name = name;
		this.mark = mark;
	}
	
	//Mutator and Accessor
	public void setMark(double mark) {
		this.mark = mark;
	}
	public gradeType getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public double getMark() {
		return mark;
	}

}
