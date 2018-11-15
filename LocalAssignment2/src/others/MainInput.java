package others;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.*;
import entity.Grade.gradeType;
import entity.Lesson.TypeOfLesson;

public class MainInput {
	public static void main(String[] args) {
		
		Database.getIO();
		int option = 0;
		int cFlag = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("STUDENT COURSE REGIRSTRATION AND MARK ENTRY Application");		
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
			try {
				option = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input type.");
				sc.next();
				continue;
			}
			
			switch(option) {
			// 1. ADD A STUDENT
			case 1:
				String matric;
				String name;
				String email;
				
				// Get Student matric
				matric = Retrieve.retrieveStudentMatric();
				// Get Name
				name = Retrieve.retrieveName();
				// Get Email
				email = Retrieve.retrieveEmail();
				
				// Add student
				boolean addB = MainController.addStudent(name, matric, email);
				
				// Print All Student if successfully added
				if (addB == true)
					MainController.printAllStudent();
				
				break;
			// 2. ADD A COURSE
			case 2:
				String courseID;
				String courseName;
				String profMatric;
				Professor prof;
				int vacancy;
				int lecVac;
				ArrayList<Lesson> lessonList = new ArrayList<Lesson>();
				
				// Get courseID, courseName, profMatric from admin
				courseID = Retrieve.retrieveCourseID();
				courseName = Retrieve.retrieveCourseName();
				profMatric = Retrieve.retrieveProfMatric();
				
				// Check whether prof and course exist in database
				boolean checkCourse = MainController.checkCourse(courseID,profMatric);
				if (!checkCourse) {
					break;
				}
				
				// Get vacancy for course
				lecVac = Retrieve.retrieveVacancy();
				
				// Create course object
				Course newCourse = MainController.newCourse(courseID, courseName, profMatric, lecVac);
				
				// Get lessonlist
				do {
					int lessonOption;
					cFlag = 1;
					int lCounter = 1;
					System.out.println("Type of lessons for Course:\n"
							+ "1. Lecture only\n"
							+ "2. Lecture and tutorial only\n"
							+ "3. Lecture, tutorial and lab");
					try {
						lessonOption = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Invalid input type.");
						sc.next();
						continue;
					}
					
					int[] info1 = {0,0};
					int[] info2 = {0,0};
					
					switch(lessonOption) {
					case 3:
						// int[2] = [numOfLabs, vacancy]
						info1 = Retrieve.retrieveLabInfo(lecVac);
						
						// Add lesson to course
						for (lCounter = 1; lCounter <= info1[0]; lCounter ++) {
							newCourse.addLesson(lCounter,info1[1],Lesson.TypeOfLesson.LAB);
						}
					case 2:
						info2 = Retrieve.retrieveTutInfo(lecVac);
						
						// Add lesson to course
						for (int j = lCounter; j < info2[0] + lCounter; j ++) {
							newCourse.addLesson(j,info2[1],Lesson.TypeOfLesson.TUT);
						}
					case 1:
						break;
					default:
						System.out.println("Invalid option.");
						cFlag = 0;
						break;
					}
				} while (cFlag == 0);
				
				// Add course to database
				MainController.addCourse(newCourse);
				
				// Print all courses
				MainController.printAllCourse();
				break;
			// 3. REGISTER STUDENT FOR A COURSE (includes tut/lab)
			case 3:
				
				matric = Retrieve.retrieveStudentMatric();
				courseID = Retrieve.retrieveCourseID();
				
				// Check whether valid register
				if (!(MainController.checkCanRegister(matric, courseID) )) {
					break;
				}
				
				// Choose tut/lab
				int[] count = Database.getCourse(courseID).countLessons();
				int labIndex = 0;
				int tutIndex = 0;
				// Get labIndex
				if (count[1] != 0) 
					do {
						cFlag = 1;
						System.out.println("Choose Lab index: [1-" + count[1] + "] ");
						try {labIndex = sc.nextInt();}
						catch (InputMismatchException e) {
							System.out.println("Invalid input type.");
							sc.next();
							cFlag = 0;
							continue;
						}
						if (labIndex <= 0 | labIndex > count[1]) {
							System.out.println("Invalid lab index range.");
							cFlag = 0;
						}
					} while (cFlag == 0);
				// Get tutIndex
				if (count[0] != 0)
					do {
						cFlag = 1;
						System.out.println("Choose Tutorial index: ["+(count[1]+1)+"-" + (count[0]+count[1]) + "] ");
						try {tutIndex = sc.nextInt();}
						catch (InputMismatchException e) {
							System.out.println("Invalid input type.");
							cFlag = 0;
							sc.next();
						}
						if (tutIndex > count[0]+count[1] | tutIndex < count[1]+1) {
							System.out.println("Invalid Tutorial index range.");
							cFlag = 0;
						}
					} while (cFlag == 0);
				
				// Student, Lesson, Course into database
				boolean changeVac = MainController.addStudentToCourse(matric, courseID, tutIndex, labIndex);
				// Check vacancy
				if (changeVac)
					MainController.updateVacancy(courseID, tutIndex, labIndex);
				break;
			// 4. CHECK AVAILABLE SLOT IN A CLASS 
			case 4:
				// Check available slot in a class (vacancy in a class)
				int classIndex = 0;
				
				courseID = Retrieve.retrieveCourseID();
				
				do {
					cFlag = 1;
					System.out.println("Enter Tut/Lab Index: ");
					try {classIndex = sc.nextInt();}
					catch (InputMismatchException e) {
						System.out.println("Invalid input type.");
						cFlag = 0;
						sc.next();
						continue;
					}
					if (classIndex <= 0) {
						cFlag = 0;
						System.out.println("Invalid classIndex format, must be positive number.");
					}
				} while (cFlag == 0);
				
				MainController.findVacancy(courseID,classIndex);
				break;
			// 5. Print student list by lecture, tutorial or laboratory session for a course
			case 5:
				Course tempCourse;
				ArrayList <Course> courses = Database.getAllCourse(); // get all course
				int choice = 0;
				
				//All courses available
				MainController.printAllCourse();
				
				courseID = Retrieve.retrieveCourseID();
				
				// Get the chosen course
				tempCourse = Database.getCourse(courseID);
				if (tempCourse == null) {
					System.out.println("Course does not exist.");
					break;
				}
				do {
					System.out.println("Display Option: ");
					System.out.println("1 - Lecture");
					System.out.println("2 - Lab");
					System.out.println("3 - Tutorial");
					try {choice = sc.nextInt();}
					catch (InputMismatchException e) {
						System.out.println("Invalid input type.");
						sc.next();
						continue;
				}
					switch(choice) {
					case 1:
						ArrayList<Student> students = tempCourse.getStudentList();
						// check whether any student is taking the course
						if(students.isEmpty()) {
							System.out.println("No students are currently taking this course.");
						}
						for(int i = 0; i < students.size(); i++) {
							System.out.println(students.get(i).getMatric() + " " + students.get(i).getName());
						}
						break;
					case 2:
						boolean haveTutLab = false;
						ArrayList<Lesson> lessons = tempCourse.getLessonList();
						
						System.out.println("Available lessonID");
						for(int k =0 ; k <lessons.size();k++) {
							if(lessons.get(k).getLType() == TypeOfLesson.LAB) {
								System.out.print(lessons.get(k).getLessonIndex() + " ");
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
						int lessonID = -1;
						do {
							cFlag = 1;
							try {lessonID = sc.nextInt();}
							catch (InputMismatchException e) {
								System.out.println("Invalid input type.");
								cFlag = 0;
								sc.next();
							}
						} while (cFlag == 0);
						
						// find the Lab slot
						Lesson lesson = Database.getTutLab(tempCourse, lessonID, TypeOfLesson.LAB);
						if(lesson == null) {
							System.out.println("This Lab slot does not exist");
							break;
						}
						students = lesson.getStudentList();
						// check whether any student is taking this tutorial slot
						if(students.isEmpty()) {
							System.out.println("No students are currently taking this course.");
						}
						for(int i = 0; i < students.size(); i++) {
							System.out.println(students.get(i).getMatric() + " " + students.get(i).getName());
						}
						break;
					case 3:
						haveTutLab = false;
						lessons = tempCourse.getLessonList();
						
						System.out.println("Available lessonID");
						for(int k =0 ; k <lessons.size();k++) {
							if(lessons.get(k).getLType() == TypeOfLesson.TUT)
								System.out.print(lessons.get(k).getLessonIndex() + " ");
								haveTutLab = true;
						}
						// check if this course has any tutorial slot
						if(!haveTutLab) {
							System.out.println("There is no Tutorial Slot for this course. ");
							break;
						}
						System.out.println();
						System.out.println("Enter LessonID: ");
						lessonID = -1;
						do {
							cFlag = 1;
							try{lessonID = sc.nextInt();}
							catch (InputMismatchException e) {
								System.out.println("Invalid input type.");
								sc.next();
								cFlag = 0;
							}
						} while(cFlag == 0);
						
						// find the Lab slot
						lesson = Database.getTutLab(tempCourse, lessonID, TypeOfLesson.TUT);
						if(lesson == null) {
							System.out.println("This Tutorial slot does not exist");
							break;
						}
						students = lesson.getStudentList();
						// check whether any student is taking this tutorial slot
						if(students.isEmpty()) {
							System.out.println("No students are currently taking this course.");
						}
						for(int i = 0; i < students.size(); i++) {
							System.out.println(students.get(i).getMatric() + " " + students.get(i).getName());
						}
						break;
					default:
						System.out.println("Invalid choice.");
					}	
				} while(choice != 1 & choice != 2 & choice != 3);
				break;
			// 6. ENTER COURSE ASSESSMENT COMPONENTS WEIGHTAGE
			case 6:
				int haveSub = -1;
				
				// Display available courses
				MainController.printAllCourse();
				
				courseID = Retrieve.retrieveCourseID();
				// Check is course exists
				tempCourse = Database.getCourse(courseID);
				if (tempCourse == null) {
					System.out.println("Course does not exist.");
					break;
				}
				
				// Check is weightage is present
				if (tempCourse.getCourseWeightage() != null) {
					System.out.println("Already has weightage.");
					break;
				}
				
				double mainPercentage = Retrieve.retrieveMainPercentage();
				double courseworkPercentage = (double)1 - mainPercentage;
				System.out.println("Please enter 0 for single Subcomponent or 1 for multiple");
				do {
					cFlag = 1;
					try { 
						haveSub = sc.nextInt();
						if (haveSub != 0 & haveSub != 1) {
							System.out.println("Enter either 1 or 0 only.");
							cFlag = 0;
						}
					}
					catch (InputMismatchException e) {
						System.out.println("Invalid input type.");
						sc.next();
						cFlag = 0;
					}
				} while (cFlag == 0);
				
				// With subcomponent (Create)
				if (haveSub == 1) {
					tempCourse.addWeightage(mainPercentage, courseworkPercentage, true);
				}
				else {
					tempCourse.addWeightage(mainPercentage, courseworkPercentage, false);
					Database.addCourse(tempCourse);
					System.out.println("Successfully added.");
				}
				// Enter subcomponent
				if (haveSub == 1) {
					ArrayList<Double> subPercentages = new ArrayList<Double>();
					ArrayList<String> names = new ArrayList<String>();
					double tempPercentage = -1;
					while (true) {
						System.out.println("Enter the Percentage of respective subcomponents(Totals to 1): ");
						do {
							cFlag = 1;
							try { 
								tempPercentage = sc.nextDouble();
								sc.nextLine();
								if ((tempPercentage <= 0 || tempPercentage >= 1 ) & tempPercentage != 2) {
									System.out.println("Please enter from 0 - 1 (excluding 0 & 1).");
									cFlag = 0;
								}
							}
							catch (InputMismatchException e) {
								System.out.println("Invalid input type.");
								sc.next();
								cFlag = 0;
							}
						} while(cFlag == 0);
						System.out.println("Enter the Name of the subcomponent: ");
						name = sc.nextLine().toUpperCase();
					// check for existing name
						if(Subcomponent.verificationSubcomponentName(names, name)) {
							names.add(name);
							subPercentages.add(tempPercentage);
							
						}
						else {
							System.out.println("The Name has been taken.");
							continue;
						}
						// Ensure the percentage adds to 1
							int isTotal = Subcomponent.verificationSubcomponentPercentage(subPercentages);
							if (isTotal == 1) {
								System.out.println("Please type in all the Subcomponents again and ensure it add up to 1");
								subPercentages = new ArrayList<Double>();
								names = new ArrayList<String>();
								continue;
							}
							else if(isTotal == -1) {
								System.out.println("Next subcomponent.");
								continue;
							}
							else {
								for (int i = 0; i < subPercentages.size(); i ++) {
									tempCourse.addSubcomponent(names.get(i), subPercentages.get(i));
								}
								Database.addCourse(tempCourse);
								System.out.println("Weightage added successfully.");
								break;
							}
					}
				}
				
				break;
			// Enter coursework mark - inclusive of its components
			case 7:
				// Show all courses
				MainController.printAllCourse();
				
				courseID = Retrieve.retrieveCourseID();
				tempCourse = Database.getCourse(courseID);
				//check if course exist
				if (tempCourse == null) {
					System.out.println("Course does not exist.");
					break;
				}
				matric = Retrieve.retrieveStudentMatric();
				Student tempS = Database.getStudent(matric);
				//check whether student is in the database
				if(tempS == null) {
					System.out.println("Student does not exist.");
					break;
				}
				//check whether the student take this course
				if(!tempCourse.studentInCourse(tempS)) {
					System.out.println("Student is not in course.");
					break;
				}
				
				//check whether weightage has been added
				Weightage Weightage = tempCourse.getCourseWeightage();
				if(Weightage == null) {
					System.out.println("Please enter the Weightage first!");
					break;
				}
				
				// Check for existing result for subcomponent
				Result result = Database.getResult(matric, courseID);
				if(result == null) {
					result = new Result(tempCourse,tempS);
				}
				else if (result.hasSubComponentMark()) {
					System.out.println("Result has already been inserted.");
					break;
				}
				
				// Coursework without subcomponent
				if(!Weightage.getHaveSub()) {
					double mark = 0;
					System.out.println("This course has CourseWork without Subcomponent.");
					do {
						cFlag = 1;
						System.out.println("Enter coursework mark (0 - 100): ");
						try {
							mark = sc.nextDouble();
							if (mark > 100 || mark < 0) {
								System.out.println("Invalid range.");
								cFlag = 0;
							}
						} catch (InputMismatchException e) {
							System.out.println("Invalid input type.");
							sc.next();
							cFlag = 0;
							continue;
						} 
					} while (cFlag == 0);
					
					MainController.addResult(result, gradeType.COURSEWORK, "FULL", mark);
					System.out.println("CourseWork mark added succesfully.");
				}
				
				// Coursework with subcomponents
				else {
					double mark = 0;
					System.out.println("This course has CourseWork with Subcomponents.");
					ArrayList<Subcomponent> subcomponents = Weightage.getSubcomponent();
					for(int i = 0;i < subcomponents.size(); i++) {
						do {
							cFlag = 1;
							String subName = subcomponents.get(i).getName();
							System.out.println("Enter coursework for '"+ subName +"' mark (0 - 100): ");
							try {
								mark = sc.nextDouble();
								if (mark > 100 || mark < 0) {
									System.out.println("Invalid range.");
									cFlag = 0;
								}
							} catch (InputMismatchException e) {
								System.out.println("Invalid input type.");
								sc.next();
								cFlag = 0;
								continue;
							} 
						} while (cFlag == 0);
						// ADD TO DATABASE
						MainController.addResult(result, gradeType.COURSEWORK, subcomponents.get(i).getName(), mark);
						System.out.println("Coursework mark added.");
					}
				}
				
				
				break;
			// 8. ENTER EXAM MARK
			case 8:
				double mark = 0;
				// Show all existing courseID
				MainController.printAllCourse();
				
				courseID = Retrieve.retrieveCourseID();
				// get the choosen course
				tempCourse = Database.getCourse(courseID);
				//check if course exist
				if (tempCourse == null) {
					System.out.println("Course does not exist.");
					break;
				}
				
				matric = Retrieve.retrieveStudentMatric();
				tempS = Database.getStudent(matric);
				//check whether student in the database
				if(tempS == null) {
					System.out.println("Student does not exist");
					break;
				}
				//check whether student take this course
				if(!tempCourse.studentInCourse(tempS)) {
					System.out.println("Student is not in course.");
					break;
				}
				
				// Check for existing result or else construct
				result = Database.getResult(matric, courseID);
				if(result == null) {
					result = new Result(tempCourse,tempS);
				}
				else if (result.hasExamMark()) {
					System.out.println("Result has already been inserted.");
					break;
				}
				
				// Mark verification
				do {
					cFlag = 1;
					System.out.println("Enter exam mark (0 - 100): ");
					try {
						mark = sc.nextDouble();
						if (mark > 100 || mark < 0) {
							System.out.println("Invalid range.");
							cFlag = 0;
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid input type.");
						sc.next();
						cFlag = 0;
						continue;
					} 
				} while (cFlag == 0);
				
				// ADD TO DATABASE
				MainController.addResult(result, gradeType.EXAM, null, mark);
				System.out.println("Exam mark added successfully.");
				
				
				break;
			// Print course statistics
			case 9:
				// Obtain course code
				int printStatType = -1;
				courseID = Retrieve.retrieveCourseID();
				
				// check whether course exists
				tempCourse = Database.getCourse(courseID);
				if (tempCourse == null) {
					System.out.println("Course does not exists.");
					break;
				}
				
				//check whether weightage has been added
				tempCourse = Database.getCourse(courseID);
				Weightage = tempCourse.getCourseWeightage();
				if(Weightage == null) {
					System.out.println("Please enter the Weightage first!");
					break;
				}
				
				do {
					cFlag = 1;
					System.out.println("Choose Type of course statistics: \n"
							+ "1. Print all Students\n"
							+ "2. Get min, max and average");
					try {printStatType = sc.nextInt();}
					catch (InputMismatchException e) {
						
						System.out.println("Invalid input type.");
						sc.next();
						cFlag = 0;
						continue;
					}
					switch (printStatType) {
						case 1:
							MainController.printCourseStat(courseID);
							break;
						case 2:
							MainController.printCourseAnalysis(courseID);
							break;
						default:
							cFlag = 0;
							System.out.println("Invalid option, please reenter.");
							break;
					}
				} while (cFlag == 0);
				break;
			case 10:
				// Get Student matric and check format 
				matric = Retrieve.retrieveStudentMatric();
				
				MainController.printStudentTranscript(matric);					
				break;
			case 11:
				// Quit application
				System.out.println("Stopping application...");
				Database.returnIO();
				break;
			default:
				System.out.println("Invalid input type.");
				break;
			}
		} while (option != 11);
	}
	
}

