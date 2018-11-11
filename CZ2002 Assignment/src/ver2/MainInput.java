package ver2;

import java.util.Scanner;

public class MainInput {

	public static void main(String[] args) {
		
		int option = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("STUDENT COURSE REGIRSTRATION AND MARK ENTRY Application");
		MainController mainC = new MainController();
		
		do {
			System.out.println("1. Add a student\n"
					+ "2. Add a course\n"
					+ "3. Register student for a course (this include registering for Tutorial/Lab classes)\n"
					+ "4. Check available slot in a class (vacancy in a class)\n"
					+ "5. Print student list by lecture, tutorial or laboratory session for a course\n"
					+ "6. Enter course assessment components weightage\n"
					+ "7. Enter coursework mark - inclusive of its components\n"
					+ "8. Enter exam mark\n"
					+ "9. Quit");
			System.out.print("==================================\n"
					+ "Choose option: ");
			option = sc.nextInt();
			// Exception to account for inputing non-input
					
			switch (option) {
				// ADD A STUDENT
				case 1:
					// Get Matric
					System.out.println("Enter Student Matric: ");
					String matric = sc.next();
					if (!Student.checkStudentMatric(matric)) {
						System.err.println("Invalid matric format.");
						break;
					}
					// Get Name
					System.out.println("Enter Student Name: ");
					// To allow whitespace in name
					sc.nextLine();
					String name = sc.nextLine();
					if (!Person.checkValidName(name)) {
						System.err.println("Invalid name entry.");
						break;
					}
					
					// Get Email
					System.out.println("Enter Student School Email: ");
					String email = sc.next();
					if (!Person.checkValidEmail(email)) {
						System.err.println("Invalid email format.");
					}
					
					// Once All entry are correct, try to add student to database
					mainC.addStudent(matric, name, email);
					
					break;
				// ADD A COURSE
				case 2:
					
					break;
				case 3:
					// Register student for a course (this include registering for Tutorial/Lab classes)
					break;
				case 4:
					// Check available slot in a class (vacancy in a class)
					break;
				case 5:
					// Print student list by lecture, tutorial or laboratory session for a course
					break;
				case 6:
					// Enter course assessment components weightage
					break;
				case 7:
					// Enter coursework mark - inclusive of its components
					break;
				case 8:
					// Enter exam mark
					break;
				case 9:
					// Quit application
					System.out.println("Stopping application...");
					break;
				default:
					System.out.println("Invalid input.");
					break;
			}
		} while (option != 9);

	}

}
