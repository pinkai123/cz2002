package others;

import java.util.ArrayList;
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
						if (!Student.isValidMatric(matric)) {
							System.out.println("Invalid matric format.");
							cFlag = 0;
						}
					} while(cFlag == 0);
					
					// Get Name
					sc.nextLine();
					do {
						cFlag = 1;
						System.out.println("Enter Student Name: ");
						// Allow whitespaces in name
						name = sc.nextLine().toUpperCase();
						if (!Person.isValidName(name)) {
							System.out.println("Invalid name entry.");
							cFlag = 0;
						}
					} while (cFlag == 0);
					
					// Get Email
					do {
						cFlag = 1;
						System.out.println("Enter Student School Email: ");
						email = sc.next().toLowerCase();
						if (!Person.isValidEmail(email)) {
							System.out.println("Invalid email format.");
							cFlag = 0;
						}
					} while (cFlag == 0);
					
					// Once All entry are correct, try to add student to database
					boolean addB = mainC.addStudent(name, matric, email);
					
					// Print Student Text file
					if (addB == true)
						mainC.printStudents();
					
					break;
				// ADD A COURSE
				case 2:
					String courseID;
					String courseName;
					String profMatric;
					Professor coursecoordinator;
					int vacancy;
					int lecVac;
					ArrayList<Lesson> lessonList = new ArrayList<Lesson>();
					
					// Get courseID
					do {
						cFlag = 1;
						System.out.println("Enter CourseID: ");
						courseID = sc.next().toUpperCase();
						if (!Course.isValidCourseID(courseID)) {
							cFlag = 0;
							System.out.println("Invalid courseID format. (Valid E.g. CZ2002)");
						}
					} while (cFlag == 0);
					
					// Get courseName
					cFlag = 1;
					sc.nextLine();
					System.out.println("Enter Course Name: ");
					courseName = sc.nextLine().toUpperCase();
					
					// Get courseCoordinator
					do {
						cFlag = 1;
						System.out.println("Enter Matric Number of Course Coordinator: ");
						profMatric = sc.next().toUpperCase();
						if (!Professor.isValidMatric(profMatric)) {
							System.out.println("Invalid matric format.");
							cFlag = 0;
						}
					} while (cFlag == 0);
					
					// Check whether prof and course exist in database
					boolean checkCourse = mainC.checkCourse(courseID,profMatric);
					if (!checkCourse) {
						break;
					}
					
					// Get vacancy
					do {
						cFlag = 1;
						System.out.println("Enter total vacancy for course: ");
						lecVac = sc.nextInt();
						if (lecVac <= 0) {
							System.out.println("Invalid vacancy, must be positive integer");
							cFlag = 0;
						}
					} while (cFlag == 0);
					
					// Create course object
					Course newCourse = mainC.newCourse(courseID, courseName, profMatric, lecVac);
					
					// Get lessonlist
					do {
						cFlag = 1;
						int cFlag2 = 1;
						int lCounter = 1;
						System.out.println("Type of lessons for Course:\n"
								+ "1. Lecture only\n"
								+ "2. Lecture and tutorial only\n"
								+ "3. Lecture, tutorial and lab");
						int lessonOption = sc.nextInt();
					
						switch(lessonOption) {
							case 3: 
								do {
									cFlag2 = 1;
									System.out.println("Enter number of labs: ");
									int numL = sc.nextInt();
									if (numL <= 0) {
										System.out.println("Cannot be less than 1, please reenter.");
										cFlag2 = 0;
										continue;
									}
									System.out.println("Enter vacancy for each lab: ");
									vacancy = sc.nextInt();
									if (vacancy <= 0) {
										System.out.println("Cannot be less than 1, please reenter.");
										cFlag2 = 0;
										continue;
									}
									else if (lecVac < numL) {
										System.out.println("Each lab's vacancy cannot be more than total course vacancy, please reenter.");
										cFlag2 = 0;
										continue;
									}
									for (int i = 1; i <= numL; i ++) {
										newCourse.addLesson(i,vacancy,Lesson.TypeOfLesson.LAB);
									}
								} while (cFlag2 == 0);
							case 2:
								do {
									cFlag2 = 1;
									System.out.println("Enter number of tutorials: ");
									int numL = sc.nextInt();
									if (numL <= 0) {
										System.out.println("Cannot be less than 1, please reenter.");
										cFlag2 = 0;
										continue;
									}
									System.out.println("Enter vacancy for each tutorial: ");
									vacancy = sc.nextInt();
									if (vacancy <= 0) {
										System.out.println("Cannot be less than 1, please reenter.");
										cFlag2 = 0;
										continue;
									}
									else if (lecVac < numL) {
										System.out.println("Each tutorial's vacancy cannot be more than total course vacancy, please reenter.");
										cFlag2 = 0;
										continue;
									}
									for (int i = 0; i < numL; i ++) {
										newCourse.addLesson(i,vacancy,Lesson.TypeOfLesson.TUT);
									}
								} while (cFlag2 == 0);
							case 1:
								break;
							default:
								System.out.println("Invalid option");
								cFlag = 0;
								break;
						}	
					} while (cFlag == 0);
										
					// Add course to text file 
					mainC.addCourse(newCourse);
					
					// Print course
					mainC.printCourses();
					
					break;
				// Register student for a course (this include registering for Tutorial/Lab classes)
				case 3:
					// Get Student
					Person tempStudent;
					Course tempCourse;
					
					// Get student's matric
					do {
						cFlag = 1;
						System.out.println("Enter Student Matric: ");
						matric = sc.next().toUpperCase();
						if (!Student.isValidMatric(matric)) {
							System.out.println("Invalid matric format.");
							cFlag = 0;
						}
					} while(cFlag == 0);
					
					// Get student info
					// do a loop?
					tempStudent = FindByID.findStudent(matric);
					if (tempStudent == null) {
						System.out.println("Student does not exist.");
						break;
					}
					
					do {
						cFlag = 1;
						System.out.println("Enter Course ID: ");
						courseID = sc.next().toUpperCase();
						if (!Course.isValidCourseID(courseID)) {
							System.out.println("Invalid course ID format.");
							cFlag = 0;
						}
					} while(cFlag == 0);
					
					// Get course info
					// do a loop?
					tempCourse = FindByID.findCourse(courseID);
					if (tempCourse == null) {
						System.out.println("Course does not exist.");
						break;
					}
					
					// Check whether student is in course
					if (tempCourse.studentInCourse((Student) tempStudent)) {
						System.out.println("Student has already registered for this course.");
					}
					
					// Check whether vacancy in course
					else if (tempCourse.getVacancy() == 0) {
						System.out.println("No more vacancy in course.");
						break;
					}
					
					// Register for course
					int[] count = tempCourse.countLessons();
					int labIndex;
					int tutIndex;
					// Get labIndex
					if (count[1] != 0) 
						do {
							cFlag = 1;
							System.out.println("Choose Lab index: [1-" + count[1] + "] ");
							labIndex = sc.nextInt();
							if (labIndex <= 0 | labIndex > count[1]) {
								System.out.println("Invalid lab index range");
								cFlag = 0;
							}
						} while (cFlag == 0);
					// Get tutIndex
					if (count[0] != 0)
						do {
							cFlag = 1;
							System.out.println("Choose Tutorial index: [1-" + count[1] + "] ");
							tutIndex = sc.nextInt();
							if (tutIndex <= 0 | tutIndex > count[0]) {
								System.out.println("Invalid lab index range");
								cFlag = 0;
							}
						} while (cFlag == 1);
						
				
					// Add student to course and change vacancy
					
					
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
					// Get Student matric and check format 
					do {
						cFlag = 1;
						System.out.println("Enter Student Matric: ");
						matric = sc.next().toUpperCase();
						if (!Student.isValidMatric(matric)) {
							System.out.println("Invalid matric format.");
							cFlag = 0;
						}
					} while(cFlag == 0);
					
					mainC.printStudentTranscript(matric);					
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
