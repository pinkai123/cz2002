package ver2;

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
		// Check first and last char are alphabet
		if (!Character.isLetter(matric.charAt(0)) | !Character.isLetter(matric.charAt(lengthOfMatric-1)))
			return false;
		// Check the rest are digits
		for (int i = 1; i < 8; i ++) {
			if (!Character.isDigit(matric.charAt(i)))
				return false;
		}
		return true;
	}
	
	public boolean checkExisting() {
		return true;
	}
}
