package entity;

public class Grade {
	private String type;
	private String name;
	private double mark = 0;
	
	//Constructor
	public Grade(String type,String name, double mark) {
		this.type =type;
		this.name = name;
		this.mark = mark;
	}
	
	
	//Mutator and Accessor
	public void setMark(double mark) {
		this.mark = mark;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public double getMark() {
		return mark;
	}
}
