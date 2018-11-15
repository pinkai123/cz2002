package entity;

import java.util.ArrayList;

/**
 * Represent the subcomponents of the coursework mark,
 * consisting of a weightage and a name to identity itself
 * from other subcomponents.
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 */
public class Subcomponent {
	/**
	 * The percentage of the subcomponent which determines
	 * the marks and weightage of coursework
	 */
	private double percentage;
	/**
	 * The name of the subcomponent
	 */
	private String name;

	/**
	 * Creates a new subcomponent with given name and percentage.
	 * @param name The subcomponent's name
	 * @param percentage The subcomponent's weightage under coursework
	 */
	public Subcomponent( String name,double percentage){
		this.percentage = percentage;
		this.name  = name;
	}
	

	/**
	 * Check whether all subcomponent in weightage adds up to 1.
	 * @param subPercentage All the subcomponents' percentage 
	 * @return true if subcomponents' percentage in this weightage adds up to 1
	 */
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
	
	/**
	 * Check whether there a subcomponent name is the same as any of the other subcomponent.
	 * @param subcomponent other subcomponents' name
	 * @param name a subcomponent name
	 * @return false if there is a match in subcomponent names
	 */
	public static boolean verificationSubcomponentName(ArrayList<String> subcomponent, String name) {
		for(int i= 0;i<subcomponent.size();i++) {
			if(subcomponent.get(i).equals(name)) {
				return false;
			}
		}
		return true;
	}
	
	// Accessors
	/**
	 * Get the percentage of the subcomponent.
	 * @return This subcomponent's percentage
	 */
	public double getPercentage() { return percentage;}
	/**
	 * Get the name of the subcomponent.
	 * @return This subcomponent's name
	 */
	public String getName() { return name;}
	
	
	
	// Mutators
	/**
	 * Changes the percentage of the subcomponent.
	 * @param percentage New subcomponent's percentage
	 */
	public void setPercentage(double percentage){
		this.percentage = percentage;
	}
	/**
	 * Changes the name of the subcomponent.
	 * @param name New subcomponent's name
	 */
	public void setName(String name){
		this.name = name;
	}
}
