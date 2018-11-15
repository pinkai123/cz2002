package entity;

import java.util.ArrayList;
import java.util.Objects;

import entity.Grade.gradeType;

/**
 * Represents the assessment weightage of a particular course.
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 */
public class Weightage {
	/**
	 * The assessment weightage of the exam (main component).
	 */
	private double mainPercentage;
	
	/**
	 * The assessment weightage of the coursework.
	 */
	private double courseworkPercentage;
	
	/**
	 * Indicates whether there are subcomponents.
	 */
	private boolean haveSub;
	// Subcomponent objects as attribute (Composition)
	/**
	 * All subcomponents in the assessment weightage.
	 */
	private ArrayList<Subcomponent> subcomponent = new ArrayList<Subcomponent>();

	/**
	 * Create a new weightage for a course.
	 * Specify the assessment percentage distribution of different components.
	 * @param mainPercentage This weightage's main component assessment percentage
	 * @param courseworkPercentage This weightage's coursework component assessment percentage
	 * @param haveSub Indication of existence of subcomponent in weightage
	 */
	public Weightage(double mainPercentage, double courseworkPercentage,boolean haveSub){
		this.mainPercentage = mainPercentage;
		this.courseworkPercentage = courseworkPercentage;
		this.haveSub = haveSub;
	}
	
	// Accessors
	/**
	 * Get the main component percentage in this weightage.
	 * @return This weightage's main component percentage
	 */
	public double getMainPercentage(){return mainPercentage;}
	
	/**
	 * Get the coursework percentage in this weightage.
	 * @return This weightage;s coursework percentage
	 */
	public double getCourseworkPercentage(){return courseworkPercentage;}
	
	/**
	 * Check whether this weightage has subcomponents.
	 * @return true if this weightage has subcomponents
	 */
	public boolean getHaveSub() { return haveSub;}
	public ArrayList<Subcomponent> getSubcomponent(){
		return subcomponent;
	}
	
	// Mutators
	/**
	 * Changes the main component percentage in this weightage.
	 * @param mainPercentage This weightage's new main component percentage
	 */
	public void setMainPercentage(double mainPercentage){
		this.mainPercentage = mainPercentage;
	}
	
	/**
	 * Changes the coursework percentage in this weightage.
	 * @param courseworkPercentage This weightage's coursework percentage
	 */
	public void setCourseworkPercentage(double courseworkPercentage){
		this.courseworkPercentage = courseworkPercentage;
	}
	
	/** 
	 * Update that subcomponent has subcomponents.
	 */
	public void setHaveSub(){
		this.haveSub = true;
	}
	
	/**
	 * Add a new subcomponent into this weightage.
	 * @param name The new subcomponent's name in this weightage
	 * @param percentage The new subcomponent's percentage in this weightage
	 */
	public void setSubcomponent(String name, double percentage) {
		Subcomponent Subcomponent = new Subcomponent(name,percentage);
		subcomponent.add(Subcomponent);
	}
	
	/**
	 * Prints the results given with its assessment weightage counterpart.
	 * @param r The results that corresponds to this weightage
	 */
	public void printMarks(Result r) { 
		double mark;
		double percentage = 0;
		Subcomponent sub;
		
		// if there are no subcomponents
		if (!haveSub) {
			for (int j = 0; j < r.getAllGrades().size(); j++) {
				Grade tempG = r.getAllGrades().get(j);
				if (Objects.equals(tempG.getType(), gradeType.EXAM)) {
					System.out.println("EXAM, marks: " + tempG.getMark() + ", weightage: " + (mainPercentage*100) + "%");
				}
				else {
					System.out.println("COURSEWORK-TOTAL, marks: "  + tempG.getMark() + ", weightage: " + (courseworkPercentage*100) + "%");
				}
			}
		}
		else {
			for (int i = 0; i < r.getAllGrades().size(); i ++) {
				Grade tempG = r.getAllGrades().get(i);
				// If grade is exam
				if (Objects.equals(tempG.getType(), gradeType.EXAM)) {
					System.out.println("EXAM, marks: " + tempG.getMark() + ", weightage: " + (mainPercentage*100) + "%");
				}
				else {
					// find the weightage according to the temG.name
					for (int j = 0; j < subcomponent.size(); j ++) {
						sub = subcomponent.get(j);
						String subName = sub.getName();
						if (Objects.equals(subName, tempG.getName())) {
							percentage = sub.getPercentage();
							break;
						}
					}
					System.out.println(tempG.getType() + "-" + tempG.getName() + ", marks: " 
							+ tempG.getMark() + ", weightage: " + (percentage*100) + "%");
				}
			}
			
			// Print total coursework
			System.out.println("COURSEWORK-TOTAL, marks: "  +  getCourseworkMark(r) + ", weightage: " + (courseworkPercentage*100) + "%");
		}
	
	}
	
	/**
	 * Get the letter grade according to this marks.
	 * @param marks
	 * @return
	 */
	public String calculateGrade(double marks) {
		if (marks >= 90)
			return "A+";
		else if (marks >= 85)
			return "A";
		else if (marks >= 80)
			return "A-";
		else if (marks >= 75)
			return "B+";
		else if (marks >= 70)
			return "B";
		else if (marks >= 65)
			return "B-";
		else if (marks >= 60)
			return "C+";
		else if (marks >= 55)
			return "C";
		else if (marks >= 50)
			return "C-";
		else if (marks >= 45)
			return "D+";
		else if (marks >= 40)
			return "D";
		else
			return "F";
	}
	
	/**
	 * Calculate the overall assessment score according to the given exam marks and coursework marks based on this weightage.
	 * @param examMark Exam marks based on this weightage
	 * @param courseworkMark Coursework mark based on this weightage
	 * @return final mark 
	 */
	public double getOverallMark(double examMark, double courseworkMark) {
		if (examMark == -1 | courseworkMark == -1) {
			return -1;
		}
		return (examMark*mainPercentage + courseworkMark*courseworkPercentage);
	}
	
	/**
	 * Get the exam marks based on this weightage.
	 * @param r all results that correspond to this weightage
	 * @return exam marks or -1 if no exam marks
	 */
	public double getExamMark(Result r) {
		for (int i = 0; i < r.getAllGrades().size(); i ++) {
			Grade tempG = r.getAllGrades().get(i);
			// Exam
			if (Objects.equals(gradeType.EXAM, tempG.getType())) {
				return tempG.getMark();
			}
		}
		return -1;
	}
	
	/**
	 * Get the coursework marks based on this weightage.
	 * @param r all results that correspond to this weightage
	 * @return exam marks or -1 if no exam marks
	 */
	public double getCourseworkMark(Result r) {
		double courseworkMark = 0;
		boolean haveCoursework = false;
		for (int i = 0; i < r.getAllGrades().size(); i ++) {
			Grade tempG = r.getAllGrades().get(i);
			if (Objects.equals(tempG.getType(), gradeType.COURSEWORK)) {
				// for no subcomponents
				if (!haveSub) {
					return tempG.getMark();
				}
				// Get weightage according to the name
				for (int j = 0; j < subcomponent.size(); j ++) {
					String name = subcomponent.get(j).getName();
					if (Objects.equals(tempG.getName(), name)) {
						haveCoursework = true;
						double percentage = subcomponent.get(j).getPercentage();
						courseworkMark += percentage * tempG.getMark();
					}
				}
			}
		}
		if (haveCoursework == true)
			return (courseworkMark);
		else
			return -1;
	}
}
