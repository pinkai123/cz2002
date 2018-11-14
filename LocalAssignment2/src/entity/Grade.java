package entity;

public class Grade {
	public enum TypeOfResult{EXAM, COURSEWORK};
	private TypeOfResult type;
	private String name;
	private double mark = 0;
	
	// Constructor
	public Grade(TypeOfResult type, String name, double mark) {
		this.type = type;
		this.name = name;
		this.mark = mark;
	}
	
	//Mutator and Accessor
	public void setMark(double mark) {
		this.mark = mark;
	}
	public TypeOfResult getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public double getMark() {
		return mark;
	}
}
