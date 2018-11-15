package entity;

/**
 * Represents the grades of a specific component of the total marks
 * @author SS5 Group 4
 * @version 1.0
 * @since 2018-11-15
 */
public class Grade {
	/**
	 * Represents the type of grades. 
	 * The types consist of exam and coursework
	 * @author SS5 Group 4
	 * @version 1.0
	 * @since 2018-11-15
	 */
	public enum gradeType {EXAM,COURSEWORK};
	/**
	 * Specific component of the grade in the overall grading criteria.
	 */
	private gradeType type;
	/**
	 * The grade's unique name.
	 */
	private String name;
	/**
	 * The marks scored.
	 */
	private double mark = 0;
	
	// Constructor
	/**
	 * Creates a new grade.
	 * @param type This grade's type
	 * @param name This grade's name
	 * @param mark This grade's mark scored
	 */
	public Grade(gradeType type, String name, double mark) {
		this.type = type;
		this.name = name;
		this.mark = mark;
	}
	
	//Mutator and Accessor
	/**
	 * Changes the mark scored.
	 * @param mark This grade's new marks
	 */
	public void setMark(double mark) {
		this.mark = mark;
	}
	
	/**
	 * Get the type of the grade.
	 * @return This grade's type
	 */
	public gradeType getType() {
		return type;
	}
	
	/**
	 * Get the name of the grade.
	 * @return This grade's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the marks in a grade.
	 * @return This grade's marks
	 */
	public double getMark() {
		return mark;
	}

}
