package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import fileIO.*;
import others.*;

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
		// Check last char is an alphabet
			if (!Character.isLetter(matric.charAt(lengthOfMatric-1)))
				return false;
		// Check first char is 'P'
			if (!(matric.charAt(0) == 'P'))
				return false;
		// Check the rest are digits
		for (int i = 1; i < 8; i ++) {
			if (!Character.isDigit(matric.charAt(i)))
				return false;
		}
		return true;
	}

	// Others
	public boolean checkExisting() {
		return false;
	}
	
	public static boolean checkExisting(String matricNum){
		// Retrieve all Student Objects in text file
		ArrayList studentList = new ArrayList();
		FileIO retrieve = new StudentIO();
		try {
			studentList = retrieve.readData("professor.txt");
		} catch(IOException e) {
			return false;
		}
		
		// Check whether matric number is existing
		for (int i = 0; i < studentList.size(); i ++) {
			Person temp = (Student) studentList.get(i);
			if (Objects.equals(matricNum, temp.getMatric())) {
				return true;
			}
		}
		return false;
		
	}	

}
