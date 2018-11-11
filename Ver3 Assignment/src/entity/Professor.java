package entity;

public class Professor extends Person{
	
	// Constructor
	public Professor(String name, String matric, String email) {
		super(name, matric, email);
	}
	
	// Validation
	public static boolean checkProfMatric(String matric) {
		int lengthOfMatric = 9;
		// Check correct length
		if (matric.length() != lengthOfMatric)
			return false;
		// Check last char is an alphabet
			if (!Character.isLetter(matric.charAt(lengthOfMatric-1)))
				return false;
		// Check first char is 'P'
			if (!(matric.charAt(0) == 'P'))
				return false;
		// Check the rest are digits
		for (int i = 1; i < 8; i ++) {
			if (!Character.isDigit(matric.charAt(i)))
				return false;
		}
		return true;
	}

	// Others
	public boolean checkExisting() {
		return false;
	}
	
	public static boolean checkExisting(String matric) {
		return true;
	}
	
	public static Professor getFromText(String matric) {
		Person newProf;
		
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
	

}
