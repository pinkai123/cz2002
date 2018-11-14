package others;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.*;
import entity.Grade.gradeType;
import entity.Lesson.TypeOfLesson;

public class MainInput {
	public static void main(String[] args) {
		// Data for testing
		Student s1 = new Student("JESS CHEW", "U1722206C", "jchew032@e.ntu.edu.sg");
		Student s2 = new Student("JACOB GOH", "U1234567A", "jgoh@e.ntu.edu.sg");
		Professor p1 = new Professor("Mr Ding", "P1111111A", "123@e.ntu.edu.sg");
		Course c1 = new Course("CZ2001", "ALGO", p1, 2);
		c1.addLesson(1, 1, TypeOfLesson.LAB);
		c1.addLesson(2, 2, TypeOfLesson.TUT);
		
		Database.addCourse(c1);
		Database.addStudent(s2);
		Database.addStudent(s1);
		Database.addProfessor(p1);
		
		int option = 0;
		int cFlag = 0;
		Scanner sc = new Scanner(System.in);
		// MainController mainC = new MainController();
		
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
				System.out.println("Invalid input.");
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
				if (addB != true)
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
						System.out.println("Invalid input.");
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
						System.out.println("Invalid option");
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
						if (tutIndex > count[0]+count[1] | tutIndex < count[1]+1) {
							System.out.println("Invalid lab index range");
							cFlag = 0;
						}
					} while (cFlag == 0);
				
				// Student, Lesson, Course into database
				boolean changeVac = MainController.addStudentToCourse(matric, courseID, tutIndex, labIndex);
				// Check vacancy
				if (changeVac)
					MainController.updateVacancy(matric, courseID, tutIndex, labIndex);
				break;
			// 4. CHECK AVAILABLE SLOT IN A CLASS 
			case 4:
				// Check available slot in a class (vacancy in a class)
				int classIndex;
				
				courseID = Retrieve.retrieveCourseID();
				
				do {
					cFlag = 1;
					System.out.println("Enter Class Index: ");
					classIndex = sc.nextInt();
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
						System.out.println("Invalid input.");
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
							System.out.println(students.get(i).getName());
						}
						break;
					case 2:
						boolean haveTutLab = false;
						ArrayList<Lesson> lessons = tempCourse.getLessonList();
						
						for(int k =0 ; k <lessons.size();k++) {
							if(lessons.get(k).getLType() == TypeOfLesson.LAB) {
								System.out.print(lessons.get(k).getLessonIndex() + " ");
								haveTutLab = true;
							}
						}
						// check this course has any lab slot
						if(!haveTutLab) {
							System.out.println("There is no Lab Slot for this course. ");
							return;
						}
						System.out.println();
						System.out.println("Enter LessonID: ");
						int lessonID = sc.nextInt();
						// find the Lab slot
						Lesson lesson = Database.getTutLab(tempCourse, lessonID, TypeOfLesson.LAB);
						if(lesson == null) {
							System.out.println("This Lab slot does not exist");
							return;
						}
						students = lesson.getStudentList();
						// check whether any student is taking this tutorial slot
						if(students.isEmpty()) {
							System.out.println("No students are currently taking this course.");
						}
						for(int i = 0; i < students.size(); i++) {
							System.out.println(students.get(i).getName());
						}
						break;
					case 3:
						haveTutLab = false;
						lessons = tempCourse.getLessonList();
						
						for(int k =0 ; k <lessons.size();k++) {
							if(lessons.get(k).getLType() == TypeOfLesson.TUT)
								System.out.print(lessons.get(k).getLessonIndex() + " ");
								haveTutLab = true;
						}
						// check if this course has any tutorial slot
						if(!haveTutLab) {
							System.out.println("There is no Tutorial Slot for this course. ");
							return;
						}
						System.out.println();
						System.out.println("Enter LessonID: ");
						lessonID = sc.nextInt();
						// find the Lab slot
						lesson = Database.getTutLab(tempCourse, lessonID, TypeOfLesson.TUT);
						if(lesson == null) {
							System.out.println("This Tutorial slot does not exist");
							return;
						}
						students = lesson.getStudentList();
						// check whether any student is taking this tutorial slot
						if(students.isEmpty()) {
							System.out.println("No students are currently taking this course.");
						}
						for(int i = 0; i < students.size(); i++) {
							System.out.println(students.get(i).getName());
						}
						break;
					default:
						System.out.println("Invalid choice.");
					}	
				} while(choice != 1 & choice != 2 & choice != 3);
				break;
			case 6:
				// Enter course assessment components weightage
				MainController.printAllCourse();
				
				String courseID1 = Retrieve.retrieveCourseID();
				// get the choosen course
				tempCourse = Database.getCourse(courseID1);
				//check if course exist
				if (tempCourse == null) {
					System.out.println("Course does not exist.");
					break;
				}
					
					double mainPercentage = Retrieve.retrieveMainPercentage();
					double courseworkPercentage = (double)1 - mainPercentage;
				do {	
					cFlag = 1;
					try {
						// choose to have subcomponent or not
						System.out.println("Please enter 1 if you have Subcomponent or 0 if you have none");
						int HaveSub = sc.nextInt();
						if(HaveSub == 1) {
							tempCourse.addWeightage(mainPercentage, courseworkPercentage, true);
						}
						else if(HaveSub == 0) {
							tempCourse.addWeightage(mainPercentage, courseworkPercentage, false);
							Database.addCourse(tempCourse);
							System.out.println("Successfully added.");
						}
						else
							System.out.println("Please enter either 1 or 0 only");
						// enter subcomponent
						if(HaveSub == 1) {
							ArrayList<Double> subPercentages = new ArrayList();
							ArrayList<String> names = new ArrayList();
							double subPercentage;
							while(true) {
								System.out.println("Enter the Percentage of subcomponent(2 to end): ");
								subPercentage = sc.nextDouble();
								if(subPercentage == 2) {
									double totalPercentage = 0;
									for(int i = 0; i< subPercentages.size();i++) {
										totalPercentage += subPercentages.get(i);
									}
									//ensure percentage add to 1
									if(totalPercentage != 1) {
										System.out.println("Please type in all the Subcomponent again and ensure it add up to 1");
										subPercentages = new ArrayList();
										names = new ArrayList();
										continue;
									}
									for(int i = 0; i< subPercentages.size();i++) {
										tempCourse.addSubcomponent(names.get(i),subPercentages.get(i));
									}
									Database.addCourse(tempCourse);
									System.out.println("Weightage add successfully.");
									break;
								}
								if(subPercentage <= 0 || subPercentage >= 1) {
									System.out.println("Please enter from 0 - 1");
									continue;
								}
								System.out.println("Enter the Name of the subcomponent: ");
								name = sc.next();
								// check for existing name
								if(Weightage.verificationSubcomponentName(names, name)) {
									names.add(name);
									subPercentages.add(subPercentage);
								}
								else {
									System.out.println("The Name has been taken");
									continue;
								}
								
							}
						}
					} catch (InputMismatchException e) {
						cFlag = 0;
						System.out.println("Invalid input type.");
					}
				} while (cFlag == 0);
				
				break;
			case 7:
				// Enter coursework mark - inclusive of its components
				ArrayList <Course> courses11 = Database.getAllCourse(); 
				for(int k =0 ; k <courses11.size();k++) {
					System.out.println(courses11.get(k).getCourseID());
				}
			
				courseID = Retrieve.retrieveCourseID();
				// get the choosen course
				tempCourse = Database.getCourse(courseID);
				//check if course exist
				if (tempCourse == null) {
					System.out.println("Course does not exist.");
					break;
				}
				System.out.println("Enter the Student Matric Number: ");
				matric = Retrieve.retrieveStudentMatric();
				Student student = Database.getStudent(matric);
				//check whether student is in the database
				if(student == null) {
					System.out.println("Student does not exist");
					break;
				}
				//check whether the student take this course
				if(!tempCourse.studentInCourse(student)) {
					System.out.println("Student is not in course");
					break;
				}
				//check whether weightage has been added
				Weightage Weightage = tempCourse.getCourseWeightage();
				if(Weightage == null) {
					System.out.println("Please enter the Weightage first!");
					break;
				}
				//check for existing result or not construct
				Result result = Database.getResult(matric, courseID);
				if(result == null) {
					result = new Result(tempCourse,student);
				}
				//coursework withouht subcomponent
				if(!Weightage.getHaveSub()) {
					System.out.println("This course has CourseWork without Subcomponent.");
					System.out.println("Enter mark for coursework(0 -1): ");
					double mark = sc.nextDouble();
					if(mark > 1|| mark < 0) {
						System.out.println("Enter Mark from 0 - 1");
						break;
					}
					result.addGrade(gradeType.COURSEWORK, null, mark);
					Database.addResult(result);
					System.out.println("Result added successfully");
				}
				//coursework with subcomponent
				else {
					System.out.println("This course has CourseWork with Subcomponents.");
					ArrayList<Subcomponent> subcomponents = Weightage.getSubcomponent();
					for(int i = 0;i < subcomponents.size(); i++) {
						System.out.println("Enter the mark for " + subcomponents.get(i).getName() + "(0 -1):");
						double mark = sc.nextDouble();
						if(mark > 1|| mark < 0) {
							System.out.println("Enter Mark from 0 - 1");
							break;
						}
						result.addGrade(gradeType.COURSEWORK, subcomponents.get(i).getName(), mark);
						Database.addResult(result);
						System.out.println("Result of" + subcomponents.get(i).getName() +"added successfully");
					}
				}
				break;
			case 8:
				// Enter exam mark
				ArrayList <Course> courses2 = Database.getAllCourse(); 
				for(int k =0 ; k <courses2.size();k++) {
					System.out.println(courses2.get(k).getCourseID());
				}
				
				courseID = Retrieve.retrieveCourseID();
				
				// get the choosen course
				tempCourse = Database.getCourse(courseID);
				//check if course exist
				if (tempCourse == null) {
					System.out.println("Course does not exist.");
					break;
				}
				matric = Retrieve.retrieveStudentMatric();
				Student student1 = Database.getStudent(matric);
				//check whether student in the database
				if(student1 == null) {
					System.out.println("Student does not exist");
					break;
				}
				//check whether student take this course
				if(!tempCourse.studentInCourse(student1)) {
					System.out.println("Student is not in course");
					break;
				}
				//check for existing result or else construct
				result = Database.getResult(matric, courseID);
				if(result == null) {
					result = new Result(tempCourse,student1);
				}
				else {
					System.out.println("Result has already been inserted");
					break;
				}
				//mark verification
				System.out.println("Enter exam mark(0 -1): ");
				double mark = sc.nextDouble();
				if(mark > 1|| mark < 0) {
					System.out.println("Enter Mark from 0 - 1");
					break;
				}
				// ADD TO DATABASE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				result.addGrade(gradeType.EXAM, null, mark);
				System.out.println("Exam mark added succesfully");
				break;
			// Print course statistics
			case 9:
				// Obtain course code
				int printStatType;
				do {
					cFlag = 1;
					System.out.println("Enter Course ID:");
					courseID = sc.next();
					if (!Course.isValidCourseID(courseID)) {
						System.out.println("Invalid course ID format.");
						cFlag = 0;
					}
				} while (cFlag == 0);
				
				do {
					cFlag = 1;
					System.out.println("Choose Type of course statistics: \n"
							+ "1. Print all Students\n"
							+ "2. Get min, max and average\n");
					printStatType = sc.nextInt();
					switch (printStatType) {
						case 1:
							MainController.printCourseAnalysis(courseID);
							break;
						case 2:
							MainController.printCourseStat(courseID);
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
				break;
			default:
				System.out.println("Invalid input.");
				break;
			}
		} while (option != 11);
	}
	
}

