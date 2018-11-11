package VeryReal;

public abstract class Person {
	protected String name;
	protected String matric;
	protected String email;
	
	// Constructor
	public Person(String name, String matric, String email) {
		this.name = name;
		this.matric = matric;
		this.email = email;
	}
	
	// Assessor
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
	
	// Other methods
	public abstract boolean checkExisting();
	
	// Validation	
	public static boolean checkValidName(String name) {
		// Check string only contain alphabet, "/", " "
		for (int i = 0; i < name.length(); i ++) {
			if (name.charAt(i) == ' ' | name.charAt(i) == '/' | Character.isLetter(name.charAt(i))) {}
			else 
				return false;
		}
		return true;
	}
	
	public static boolean checkValidEmail(String email) {
		// Check that it is at the end
		if (email.contains("@ntu.edu.sg") | email.contains("@e.ntu.edu.sg"))
			return true;
		return false;
	}
}