package others;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.*;
import entity.Lesson.TypeOfLesson;
import fileIO.*;

public class MainInput {

	public static void main(String[] args) {
		int cFlag = 0;
		int option = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("STUDENT COURSE REGIRSTRATION AND MARK ENTRY Application");
		MainController mainC = new MainController();
		Database.getIO();
		
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
									else if (lecVac < vacancy) {
										System.out.println("Each lab's vacancy cannot be more than total course vacancy, please reenter.");
										cFlag2 = 0;
										continue;
									}
									for (lCounter = 1; lCounter <= numL; lCounter ++) {
										newCourse.addLesson(lCounter,vacancy,Lesson.TypeOfLesson.LAB);
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
									else if (lecVac < vacancy) {
										System.out.println("Each tutorial's vacancy cannot be more than total course vacancy, please reenter.");
										cFlag2 = 0;
										continue;
									}
									for (int j = lCounter; j < numL + lCounter; j ++) {
										newCourse.addLesson(j,vacancy,Lesson.TypeOfLesson.TUT);
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
					tempStudent = IOController.findStudent(matric);
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
					tempCourse = IOController.findCourse(courseID);
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
					int labIndex = 0;
					int tutIndex = 0;
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
							System.out.println("Choose Tutorial index: ["+(count[1]+1)+"-" + (count[0]+count[1]) + "] ");
							tutIndex = sc.nextInt();
							if (tutIndex <= 0 | tutIndex > count[0]) {
								System.out.println("Invalid lab index range");
								cFlag = 0;
							}
						} while (cFlag == 0);
						
				
					// Add student to course and change vacancy
					mainC.addStudenttoCourse((Student) tempStudent, tempCourse, tutIndex, labIndex);
					
					break;
				case 4:
					// Check available slot in a class (vacancy in a class)
					break;
				case 5:
					// Print student list by lecture, tutorial or laboratory session for a course
					Course TempCourse;
					int LessonID;
					Lesson Lesson = null;
					ArrayList <Student> Students = new ArrayList();
					ArrayList <Lesson> Lessons = new ArrayList();
					ArrayList <Course> Courses = Database.getAllCourse(); // get all course
					boolean haveTutLab = false;
					//All courses available
					for(int k =0 ; k <Courses.size();k++) {
						System.out.println(Courses.get(k).getCourseID());
					}
				
					System.out.println("Enter the CourseID: ");
					String CourseID = sc.next();
					// get the choosen course
					TempCourse = Database.getCourse(CourseID);
					if (TempCourse == null) {
						System.out.println("Course does not exist.");
						break;
					}
					Lessons = TempCourse.getLessonList();
					System.out.println("Display Option: ");
					System.out.println("1 - Lecture");
					System.out.println("2 - Lab");
					System.out.println("3 - Tutorial");
					int choice = sc.nextInt();
					switch(choice) {
					case 1:
						Students = TempCourse.getStudentList();
						// check whether any student is taking the course
						if(Students.isEmpty()) {
							System.out.println("No students are currently taking this course.");
						}
						for(int i = 0; i < Students.size(); i++) {
							System.out.println(Students.get(i).getName());
						}
						break;
					case 2:
						
						for(int k =0 ; k <Lessons.size();k++) {
							if(Lessons.get(k).getLType() == TypeOfLesson.LAB) {
								System.out.print(Lessons.get(k).getLessonIndex() + " ");
								haveTutLab = true;
							}
						}
						// check this course has any lab slot
						if(!haveTutLab) {
							System.out.println("There is no Lab Slot for this course. ");
							break;
						}
						System.out.println();
						System.out.println("Enter LessonID: ");
						LessonID = sc.nextInt();
						// find the Lab slot
						Lesson = FindByID.findTutLab(TempCourse, LessonID, TypeOfLesson.LAB);
						if(Lesson == null) {
							System.out.println("This Lab slot does not exist");
							break;
						}
						Students = Lesson.getStudentList();
						// check whether any student is taking this tutorial slot
						if(Students.isEmpty()) {
							System.out.println("No students are currently taking this course.");
						}
						for(int i = 0; i < Students.size(); i++) {
							System.out.println(Students.get(i).getName());
						}
						break;
					case 3:
						for(int k =0 ; k <Lessons.size();k++) {
							if(Lessons.get(k).getLType() == TypeOfLesson.TUT)
								System.out.print(Lessons.get(k).getLessonIndex() + " ");
								haveTutLab = true;
						}
						// check if this course has any tutorial slot
						if(!haveTutLab) {
							System.out.println("There is no Tutorial Slot for this course. ");
							break;
						}
						System.out.println();
						System.out.println("Enter LessonID: ");
						LessonID = sc.nextInt();
						// find the Lab slot
						Lesson = FindByID.findTutLab(TempCourse, LessonID, TypeOfLesson.TUT);
						if(Lesson == null) {
							System.out.println("This Tutorial slot does not exist");
							break;
						}
						Students = Lesson.getStudentList();
						// check whether any student is taking this tutorial slot
						if(Students.isEmpty()) {
							System.out.println("No students are currently taking this course.");
						}
						for(int i = 0; i < Students.size(); i++) {
							System.out.println(Students.get(i).getName());
						}
						break;
					}
					break;
				case 6:
					// Enter course assessment components weightage
					ArrayList <Course> Courses1 = Database.getAllCourse(); 
					for(int k =0 ; k <Courses1.size();k++) {
						System.out.println(Courses1.get(k).getCourseID());
					}
				
					System.out.println("Enter the CourseID: ");
					String courseID1 = sc.next();
					// get the choosen course
					TempCourse = Database.getCourse(courseID1);
					//check if course exist
					if (TempCourse == null) {
						System.out.println("Course does not exist.");
						break;
					}
					System.out.println("Enter Main Weightage(0 - 1  e.g. 60%  = 0.6)");
					double mainPercentage = sc.nextDouble();
					System.out.println("Enter Coursework Weightage(0 - 1  e.g. 60%  = 0.6)");
					double courseworkPercentage = sc.nextDouble();
					// ensure percentage add up to 1
					if(!Weightage.verificationOverall(mainPercentage, courseworkPercentage)) {
						System.out.println("Please make sure both percentage add up to 1");
						break;
					}
					// choose to have subcomponent or not
					System.out.println("Please enter 1 if you have Subcomponent or 0 if you hvae none");
					int HaveSub = sc.nextInt();
					if(HaveSub == 1) {
						TempCourse.addWeightage(mainPercentage, courseworkPercentage, true);
					}
					else if(HaveSub == 0)
						TempCourse.addWeightage(mainPercentage, courseworkPercentage, false);
					else
						System.out.println("Please enter either 1 or 0 only");
					// enter subcomponent
					if(HaveSub == 1) {
						ArrayList<Double> SubPercentages = new ArrayList();
						ArrayList<String> Names = new ArrayList();
						double subPercentage;
						while(true) {
							System.out.println("Enter the Perceentage of subcomponent(2 to end): ");
							subPercentage = sc.nextDouble();
							if(subPercentage == 2) {
								double totalPercentage = 0;
								for(int i = 0; i< SubPercentages.size();i++) {
									totalPercentage += SubPercentages.get(i);
								}
								//emsure percentage add to 1
								if(totalPercentage != 1) {
									System.out.println("Please type in all the Subcomponent again and ensure it add up to 1");
									SubPercentages = new ArrayList();
									Names = new ArrayList();
									continue;
								}
								for(int i = 0; i< SubPercentages.size();i++) {
									TempCourse.addSubcomponent(Names.get(i),SubPercentages.get(i));
								}
								System.out.println("Weightage add succesfully.");
								break;
							}
							if(subPercentage <= 0 || subPercentage >= 1) {
								System.out.println("Please enter from 0 - 1");
								continue;
							}
							System.out.println("Enter the Name of the subcomponent: ");
							String Name = sc.next();
							// check for existing name
							if(Weightage.verificationSubcomponentName(Names, Name)) {
								Names.add(Name);
								SubPercentages.add(subPercentage);
							}
							else {
								System.out.println("The Name has been taken");
								continue;
							}
							
						}
					}
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
					Database.returnIO();
					break;
				default:
					System.out.println("Invalid input.");
					break;
			}
		} while (option != 9);

	}

}
