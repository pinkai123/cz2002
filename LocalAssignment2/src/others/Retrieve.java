package others;

import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Course;
import entity.Lesson;
import entity.Person;
import entity.Professor;
import entity.Student;

public class Retrieve {
	
	public static String retrieveStudentMatric() {
		Scanner sc = new Scanner(System.in);
		String matric;
		int cFlag;
		do {
			cFlag = 1;
			System.out.println("Enter Student Matric: ");
			matric = sc.next().toUpperCase();
			if (!Student.isValidMatric(matric)) {
				System.out.println("Invalid matric format.");
				cFlag = 0;
			}
		} while(cFlag == 0);
		return matric;
	}
	
	public static String retrieveName() {
		Scanner sc = new Scanner(System.in);
		String name;
		int cFlag;
		do {
			cFlag = 1;
			System.out.println("Enter Student Name: ");
			// Allow whitespaces in name
			name = sc.nextLine().toUpperCase();
			if (!Person.isValidName(name)) {
				System.out.println("Invalid name format.");
				cFlag = 0;
			}
		} while (cFlag == 0);
		return name;
	}
	
	public static String retrieveEmail() {
		Scanner sc = new Scanner(System.in);
		String email;
		int cFlag;
		do {
			cFlag = 1;
			System.out.println("Enter Student School Email: ");
			email = sc.next().toLowerCase();
			if (!Person.isValidEmail(email)) {
				System.out.println("Invalid email format.");
				cFlag = 0;
			}
		} while (cFlag == 0);
		return email;
	}
	
	public static String retrieveCourseID() {
		Scanner sc = new Scanner(System.in);
		String courseID;
		int cFlag;
		do {
			cFlag = 1;
			System.out.println("Enter CourseID: ");
			courseID = sc.next().toUpperCase();
			if (!Course.isValidCourseID(courseID)) {
				cFlag = 0;
				System.out.println("Invalid courseID format. (Valid E.g. CZ2002)");
			}
		} while (cFlag == 0);
		return courseID;
	}
	
	public static String retrieveCourseName() {
		Scanner sc = new Scanner(System.in);
		String courseName;
		System.out.println("Enter Course Name: ");
		courseName = sc.nextLine().toUpperCase();
		return courseName;
	}
	
	public static String retrieveProfMatric() {
		Scanner sc = new Scanner(System.in);
		String profMatric;
		int cFlag;
		do {
			cFlag = 1;
			System.out.println("Enter Matric Number of Course Coordinator: ");
			profMatric = sc.next().toUpperCase();
			if (!Professor.isValidMatric(profMatric)) {
				System.out.println("Invalid matric format.");
				cFlag = 0;
			}
		} while (cFlag == 0);
		return profMatric;
	}
	
	public static int retrieveVacancy() {
		Scanner sc = new Scanner(System.in);
		int vac = 0;
		int cFlag;
		do {
			cFlag = 1;
			System.out.println("Enter total vacancy for course: ");
			try {
				vac = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				cFlag = 0;
				sc.next();
				continue;
			}
			if (vac <= 0) {
				System.out.println("Invalid vacancy, must be positive integer");
				cFlag = 0;
			}
		} while (cFlag == 0);
		return vac;
	}
	
	public static int[] retrieveLabInfo(int lecVac) {
		Scanner sc = new Scanner(System.in);
		// int[2] = [numOfLabs, vacancy]
		int[] info = {0,0};
		do {
			System.out.println("Enter number of labs: ");
			try { info[0] = sc.nextInt();}
			catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				sc.next();
				continue;
			}
			if (info[0] <= 0) {
				System.out.println("Cannot be less than 1, please reenter.");
				continue;
			}
			System.out.println("Enter vacancy for each lab: ");
			try {info[1] = sc.nextInt();}
			catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				sc.next();
				continue;
			} 
			if (info[1] <= 0) {
				System.out.println("Cannot be less than 1, please reenter.");
				continue;
			}
			else if (lecVac < info[1]) {
				System.out.println("Each lab's vacancy cannot be more than total course vacancy, please reenter.");
				continue;
			}
		} while (false);
		return info;
	}
	
	public static int[] retrieveTutInfo(int lecVac) {
		Scanner sc = new Scanner(System.in);
		// int[2] = [numOfTut, vacancy]
		int[] info = {0,0};
		do {
			System.out.println("Enter number of tutorial: ");
			try { info[0] = sc.nextInt();}
			catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				sc.next();
				continue;
			}
			if (info[0] <= 0) {
				System.out.println("Cannot be less than 1, please reenter.");
				continue;
			}
			System.out.println("Enter vacancy for each tutorial: ");
			try {info[1] = sc.nextInt();}
			catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				sc.next();
				continue;
			} 
			if (info[1] <= 0) {
				System.out.println("Cannot be less than 1, please reenter.");
				continue;
			}
			else if (lecVac < info[1]) {
				System.out.println("Each tutorial's vacancy cannot be more than total course vacancy, please reenter.");
				continue;
			}
		} while (false);
		return info;
	}
	
	public static int retrieveLessonIndex() {
		Scanner sc = new Scanner(System.in);
		int index = 0;
		int cFlag = 0;
		do {
			cFlag = 1;
			System.out.println("Enter Class Index: ");
			try {index = sc.nextInt();}
			catch (InputMismatchException e) {
				System.out.println("Invalid input type.");
				sc.next();
				cFlag = 0;
				continue;
			} 
			if (index <= 0) {
				cFlag = 0;
				System.out.println("Invalid classIndex format, must be positive number.");
			}
		} while (cFlag == 0);
		return index;
	}
	
	public static double retrieveMainPercentage() {
		Scanner sc = new Scanner(System.in);
		int cFlag = 0;
		double mainPercentage = 0;
		do {
			cFlag = 1;
			System.out.println("Enter Main Weightage(0 - 1  e.g. 60%  = 0.6)");
			try {mainPercentage = sc.nextDouble();}
			catch (InputMismatchException e) {
				System.out.println("Invalid input type.");
				sc.next();
				cFlag = 0;
			}
			if (mainPercentage > 1 | mainPercentage < 0) {
				System.out.println("Invalid range, must be 0-1.");
				cFlag = 0;
			}
			
		} while (cFlag == 0);
		return mainPercentage;
	}
}
