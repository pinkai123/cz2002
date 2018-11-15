package entity;

import java.util.ArrayList;

public class Subcomponent {
	private double percentage;
	private String name;

	public Subcomponent( String name,double percentage){
		this.percentage = percentage;
		this.name  = name;
	}
	//verification
	public static int verificationSubcomponentPercentage(ArrayList<Double> subPercentage) {
		double percentage = 0;
		for (int i = 0; i < subPercentage.size(); i ++) {
			percentage += subPercentage.get(i);
		}
		if (percentage < 1)
			return -1;
		else if (percentage == 1)
			return 0;
		else
			return 1;
	}
	
	public static boolean verificationSubcomponentName(ArrayList<String> subcomponent, String name) {
		for(int i= 0;i<subcomponent.size();i++) {
			if(subcomponent.get(i).equals(name)) {
				return false;
			}
		}
		return true;
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