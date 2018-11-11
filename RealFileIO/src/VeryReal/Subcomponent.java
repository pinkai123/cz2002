package VeryReal;

public class Subcomponent {
	double percentage;
	String name;

	public Subcomponent( String name,double percentage){
		this.percentage = percentage;
		this.name  = name;
	}
	
	public double getPercentage() { return percentage;}
	public String getName() { return name;}
}
