package entity;

public class Subcomponent {
	double percentage;
	String name;

	public Subcomponent( String name,double percentage){
		this.percentage = percentage;
		this.name  = name;
	}
	
	// Accessors
	public double getPercentage() { return percentage;}
	public String getName() { return name;}
	
	
	
	// Mutators
	public void setPercentage(double percentage){
		this.percentage = percentage;
	}
	public void setName(String name){
		this.name = name;
	}
}