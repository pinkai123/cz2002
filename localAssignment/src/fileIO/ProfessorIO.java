package fileIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import entity.*;
import others.*;

public class ProfessorIO extends FileIO {
	public static final String SEPARATOR = "|";

    // an example of reading
	public ArrayList readData(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				Professor prof = null;
				while(star.hasMoreTokens()) {
					String  name = star.nextToken().trim();	// first token
					String  matric = star.nextToken().trim();	// second token
					String  email = star.nextToken().trim(); // third token
					// create Professor object from file data
					prof = new Professor(name, matric, email);
				}
				// add to Professors list
				alr.add(prof) ;
			}
			return alr ;
	}

  // an example of saving
public void saveData(String filename, ArrayList al) throws IOException {
		ArrayList alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
				Professor prof = (Professor)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(prof.getName().trim());
				st.append(SEPARATOR);
				st.append(prof.getMatric().trim());
				st.append(SEPARATOR);
				st.append(prof.getEmail());
				alw.add(st.toString()) ;
			}
			write(filename,alw,true);
	}

}