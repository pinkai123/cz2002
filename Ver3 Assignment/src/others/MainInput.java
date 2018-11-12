package others;

import java.util.Scanner;

import entity.Course;
import entity.Person;
import entity.Professor;
import entity.Student;

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
					+ "9. Print course statistics\n"
					+ "10. Print student transcript\n"
					+ "11. Quit");
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
						break;
					}
					
					// Once All entry are correct, try to add student to database
					mainC.addStudent(matric, name, email);
					
					break;
				// ADD A COURSE
				case 2:
					// Get courseID
					System.out.println("Enter CourseID: ");
					int courseID = sc.nextInt();
					
					// Get courseName
					System.out.println("Enter CourseName: ");
					String courseName = sc.next();
					if (! Course.checkValidCourseName(courseName)) {
						System.err.println("Invalid course name format.");
						break;
					}
					
					// Get courseCoordinator
					System.out.println("Enter Matric Number of Course Coordinator: ");
					String profMatric = sc.next();
					if (!Professor.checkProfMatric(profMatric)) {
						System.err.println("Invalid matric format.");
						break;
					}
					
					// Find prof from text file
					
					// Get lessonlist
					int cFlag = 0;
					do {
						System.out.println("Type of lessons for Course:\n"
								+ "1. Lecture only\n"
								+ "2. Lecture and tutorial only\n"
								+ "3. Lecture, tutorial and lab");
						int lessonOption = sc.nextInt();
					
						switch(option) {
							case 3: 
								
							case 2:
							
							case 1:
								// Assumption only 1 lecture option for course
								System.out.println("====Lecture=====\n"
										+ "Enter vacancy for whole course/lecture: ");
								int vacancy = sc.nextInt();
								break;
							default:
								System.out.println("Invalid option");
								break;
						}	
					} while (cFlag == 0);
					
					
					// Add course to text file 
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
				// Print course statistics
				case 9:
					break;
				// Print student transcript
				case 10:
					System.out.println("Enter Student Matric: ");
					String matric1 = sc.next();
					if (!Student.checkStudentMatric(matric1)) {
						System.err.println("Invalid matric format.");
						break;
					}
					
					
					break;
				case 11:
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
