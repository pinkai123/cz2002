package entity;

public class Person {
	protected String name;
	protected String matric;
	protected String email;
	
	// Constructor 
	public Person(String name, String matric, String email) {
		this.name = name;
		this.matric = matric;
		this.email = email;
	}
	
	// Accessor
	public String getName() {
		return name;
	}
	public String getMatric() {
		return matric;
	}
	public String getEmail() {
		return email;
	}
	
	// Mutator
	public void setName(String name) {
		this.name = name;
	}
	public void setMatric(String matric) {
		this.matric = matric;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	// Check naming convention of name is valid
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