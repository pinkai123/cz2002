package ver2;

public class MainController {
	
	// 1. ADD A STUDENT
	public void addStudent(String matric, String name, String email) {
		// Create student object
		Person newStudent = new Student(matric, name, email);
		
		// Check whether existing 
		boolean isExisting = newStudent.checkExisting();
		if (isExisting) {
			System.out.println("Student already exists.");
			return;
		}
		
		// Add student to database
		
	}
	
	/*
	public int addStudent(String matric, String name, String email) {
		// Check whether matric number 
		if (!Person.checkValidMatric(matric))
			return 1;
		if (!Person.checkExistMatric(matric))
			return 2;
		if (!Person.checkValidEmail(email))
			return 3;
		if (!Person.checkValidName(name))
			return 4;
		// If all valid, create student object
		Person newStudent = new Student(matric,name,email);
		// Save student data in text file
		return 0;
	}
	*/
}
