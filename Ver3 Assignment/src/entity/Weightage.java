package entity;

public class Weightage {
	private double mainPercentage;
	private double courseworkPercentage;
	private boolean haveSub;
	private int noSub = 0;
	private Subcomponent[] subcomponent = new Subcomponent[10];

	public Weightage(double mainPercentage, double courseworkPercentage,boolean haveSub){
		this.mainPercentage = mainPercentage;
		this.courseworkPercentage = courseworkPercentage;
		this.haveSub = haveSub;
	}
	
	public void setSubcomponent(String name, double percentage) {
		subcomponent [noSub] = new Subcomponent(name,percentage);
		noSub++;
	}
	public static boolean verificationOverall(int mainPercentage, int courseworkPercentage) {
		if(mainPercentage + courseworkPercentage == 100) {
			return true;
		}
		else 
			return false;
	}
	public static boolean verificationSubcomponentPercentage(Subcomponent[] subcomponent, int noSub) {
		int percentage = 0;
		for(int i = 0; i < noSub;i++) {
			percentage += subcomponent[i].getPercentage();
			}
		if(percentage!= 100)
			return false;
		return true;
	}
	
	public static boolean verificationSubcomponentName(Subcomponent[] subcomponent, String name, int noSub) {
		for(int i= 0;i<noSub;i++) {
			if((subcomponent[i].getName()).equals(name))
				return false;
		}
		return true;
	}
	
	public double getMainPercentage(){return mainPercentage;}
	public double getCourseworkPercentage(){return courseworkPercentage;}
	public int getNoSub(){return noSub;}
	public boolean getHaveSub() { return haveSub;}
}