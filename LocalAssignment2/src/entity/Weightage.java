package entity;

import java.util.ArrayList;

public class Weightage {
	private double mainPercentage;
	private double courseworkPercentage;
	private boolean haveSub;
	private int noSub = 0;
	// Subcomponent objects as attribute (Composition)
	private ArrayList<Subcomponent> subcomponent = new ArrayList<Subcomponent>();

	public Weightage(double mainPercentage, double courseworkPercentage,boolean haveSub){
		this.mainPercentage = mainPercentage;
		this.courseworkPercentage = courseworkPercentage;
		this.haveSub = haveSub;
	}
	
	public static boolean verificationOverall(int mainPercentage, int courseworkPercentage) {
		if(mainPercentage + courseworkPercentage == 100) {
			return true;
		}
		else 
			return false;
	}
	public static boolean verificationSubcomponentPercentage(ArrayList<Subcomponent> subcomponent, int noSub) {
		int percentage = 0;
		for(int i = 0; i < noSub;i++) {
			percentage += ((Subcomponent) subcomponent.get(i)).getPercentage();
			}
		if(percentage!= 100)
			return false;
		return true;
	}
	
	public static boolean verificationSubcomponentName(ArrayList<Subcomponent> subcomponent, String name, int noSub) {
		for(int i= 0;i<noSub;i++) {
			if((((Subcomponent) subcomponent.get(i)).getName()).equals(name))
				return false;
		}
		return true;
	}
	// Accessors
	public double getMainPercentage(){return mainPercentage;}
	public double getCourseworkPercentage(){return courseworkPercentage;}
	public int getNoSub(){return noSub;}
	public boolean getHaveSub() { return haveSub;}
	public ArrayList<Subcomponent> getSubcomponent(){
		return subcomponent;
	}
	
	// Mutators
	public void setMainPercentage(double mainPercentage){
		this.mainPercentage = mainPercentage;
	}
	public void setCourseworkPercentage(double courseworkPercentage){
		this.courseworkPercentage = courseworkPercentage;
	}
	public void setHaveSub(){
		this.haveSub = true;
	}
	public void setSubcomponent(String name, double percentage) {
		Subcomponent Subcomponent = new Subcomponent(name,percentage);
		subcomponent.add(Subcomponent);
		noSub++;
	}
}
