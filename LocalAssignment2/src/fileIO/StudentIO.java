package fileIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entity.*;
import others.*;

public class StudentIO extends FileIO {
	public static final String SEPARATOR = "|";
	protected String fileName = "student.txt";

    // an example of reading
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

	// an example of saving
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