package others;

import java.util.Scanner;

import entity.*;
import fileIO.*;

public class MainInput {

	public static void main(String[] args) {
		int cFlag = 0;
		int option = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("STUDENT COURSE REGIRSTRATION AND MARK ENTRY Application");
		MainController mainC = new MainController();
		
		do {
			System.out.println("==================================");
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
					String matric;
					String name;
					String email;
					
					do {
						cFlag = 1;
						System.out.println("Enter Student Matric: ");
						matric = sc.next().toUpperCase();
						if (!Student.checkStudentMatric(matric)) {
							System.out.println("Invalid matric format.");
							cFlag = 0;
						}
					} while(cFlag == 0);
					
					// Get Name
					do {
						cFlag = 1;
						System.out.println("Enter Student Name: ");
						// Allow whitespaces in name
						sc.nextLine();
						name = sc.nextLine().toUpperCase();
						if (!Person.checkValidName(name)) {
							System.out.println("Invalid name entry.");
							cFlag = 0;
						}
					} while (cFlag == 0);
					
					// Get Email
					do {
						cFlag = 1;
						System.out.println("Enter Student School Email: ");
						email = sc.next().toLowerCase();
						if (!Person.checkValidEmail(email)) {
							System.out.println("Invalid email format.");
							cFlag = 0;
						}
					} while (cFlag == 0);
					
					// Once All entry are correct, try to add student to database
					mainC.addStudent(name, matric, email);
					
					break;
				// ADD A COURSE
				case 2:
					// Get courseID
					do {
						cFlag = 1;
						System.out.println("Enter CourseID: ");
						String courseID = sc.next();
						if (!Course.validCourseID(courseID)) {
							cFlag = 0;
							System.out.println("Invalid courseID format. (Valid E.g. CZ2002)");
						}
					} while (cFlag == 0);
					
					// Get courseName
					cFlag = 1;
					System.out.println("Enter Course Name: ");
					String courseName = sc.next();
					
					// Get courseCoordinator
					System.out.println("Enter Matric Number of Course Coordinator: ");
					String profMatric = sc.next();
					if (!Professor.checkProfMatric(profMatric)) {
						System.out.println("Invalid matric format.");
						break;
					}
					
					// Find prof from text file
					
					// Get lessonlist
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
					do {
						cFlag = 1;
						System.out.println("Enter Student Matric: ");
						matric = sc.next().toUpperCase();
						if (!Student.checkStudentMatric(matric)) {
							System.out.println("Invalid matric format.");
							cFlag = 0;
						}
					} while(cFlag == 0);
					
					
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
