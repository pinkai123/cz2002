package entity;

import java.util.ArrayList;
import java.util.Objects;

import entity.Grade.gradeType;

public class Weightage {
	private double mainPercentage;
	private double courseworkPercentage;
	private boolean haveSub;
	// Subcomponent objects as attribute (Composition)
	private ArrayList<Subcomponent> subcomponent = new ArrayList<Subcomponent>();

	public Weightage(double mainPercentage, double courseworkPercentage,boolean haveSub){
		this.mainPercentage = mainPercentage;
		this.courseworkPercentage = courseworkPercentage;
		this.haveSub = haveSub;
	}
	
	public static boolean verificationSubcomponentPercentage(ArrayList<Double> subPercentage) {
		double percentage = 0;
		for (int i = 0; i < subPercentage.size(); i ++) {
			percentage += subPercentage.get(i);
		}
		if (percentage != 1)
			return false;
		return true;
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
	public double getMainPercentage(){return mainPercentage;}
	public double getCourseworkPercentage(){return courseworkPercentage;}
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
	}
	
	public void printMarks(Result r) {
		double percentage;
		
		for (int j = 0; j < r.getAllGrades().size(); j++) {
			Grade g = r.getAllGrades().get(j);
			System.out.println(g.getType() + " " + g.getName() + " " + g.getMark());
		}
	}
	
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
	
	public double getOverallMark(double examMark, double courseworkMark) {
		if (examMark == -1 | courseworkMark == -1) {
			return -1;
		}
		return (examMark*mainPercentage + courseworkMark*courseworkPercentage);
	}
	
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