package IO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entity.*;
import others.*;

public class StudentIO extends FileIO {
	/**
	 * @param Seperator to separate each variable in the text file
	 */
	public static final String SEPARATOR = "|";
	/**
	 * @param name of the file
	 */
	protected String fileName = "student.txt";

	/**
	 * construct list of student from text file
	 * @return list of student
	 */
	public ArrayList readData() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(fileName);
		ArrayList alr = new ArrayList() ;// to store Students data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				Student Student = null;
				while(star.hasMoreTokens()) {
					String  name = star.nextToken().trim();	// first token
					String  matric = star.nextToken().trim();	// second token
					String  email = star.nextToken().trim(); // third token
					// create Student object from file data
					Student = new Student(name,matric, email);
				}
				// add to Students list
				alr.add(Student) ;
			}
			return alr ;
	}

	/**
	 * @param al list of Student
	 * void save the list of student into a text file
	 */
	public void saveData(ArrayList al) throws IOException {
		ArrayList alw = new ArrayList() ;// to store Students data

        for (int i = 0 ; i < al.size() ; i++) {
				Student Student = (Student)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(Student.getName().trim());
				st.append(SEPARATOR);
				st.append(Student.getMatric().trim());
				st.append(SEPARATOR);
				st.append(Student.getEmail());
				alw.add(st.toString()) ;
			}
			write(fileName,alw,false);
	}
}