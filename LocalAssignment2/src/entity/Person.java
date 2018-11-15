package entity;

/**
 * Represents a person in the school.
 * Either refer to students or professors.
 * @author SS5 Group 4
 * @version  1.0
 * @since 2018-11-15
 */
public class Person {
	/**
	 * The person's full name.
	 */
	protected String name;
	/**
	 * The person's matriculation name.
	 */
	protected String matric;
	/**
	 * The person's official school email.
	 */
	protected String email;
	
	/**
	 * Creates a new person in the school.
	 * @param name This person's name
	 * @param matric This person's matriculation number
	 * @param email This person's school email
	 */
	public Person(String name, String matric, String email) {
		this.name = name;
		this.matric = matric;
		this.email = email;
	}
	
	// Accessor
	/**
	 * Get the full name of the person.
	 * @return This person's full name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the matriculation number of the person.
	 * @return This person's matriculation number
	 */
	public String getMatric() {
		return matric;
	}
	
	/**
	 * Get the official school email of the person.
	 * @return This person's school email
	 */
	public String getEmail() {
		return email;
	}
	
	// Mutator
	/**
	 * Changes the name of the person.
	 * @param name This person's new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Changes the matriculation number of the person.
	 * @param matric This person's new matriculation number
	 */
	public void setMatric(String matric) {
		this.matric = matric;
	}
	
	/**
	 * Changes the school email of the person.
	 * @param email This person's new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Check whether the person's name is in a valid format.
	 * Invalid names are those that contains numbers and some unique characters
	 * @param name This person's name
	 * @return true if person's name is in a valid format
	 */
	public static boolean isValidName(String name) {
		// Check string only contain alphabet, "/", " "
		for (int i = 0; i < name.length(); i ++) {
			if (name.charAt(i) == ' ' | name.charAt(i) == '/' | Character.isLetter(name.charAt(i))) {}
			else 
				return false;
		}
		return true;
	}
	
	// Check naming convention of email is valid
	public static boolean isValidEmail(String email) {
		// Check that it has the email path at the end
		if (!(email.endsWith("@ntu.edu.sg")) & !(email.endsWith("@e.ntu.edu.sg")))
			return false;
		else if (email.endsWith("@ntu.edu.sg") & email.length() > "@ntu.edu.sg".length())
			return true;
		else if (email.endsWith("@e.ntu.edu.sg") & email.length() > "@e.ntu.edu.sg".length())
			return true;
		else
			return false;
	}
}
