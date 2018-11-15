package IO;


import java.io.*;
import java.util.*;

import entity.*;
import others.*;

public abstract class FileIO {
	public static final String SEPARATOR = "|";
	protected String fileName;
	/**
	 * 
	 * @return list of object(course,professor,student,result)
	 * @throws IOException when the file to be written and read cannot be found
	 */
	public abstract ArrayList readData() throws IOException ;
	/**
	 * 
	 * save list of objects
	 * @param al list of list of object(course,professor,student,result)
	 * @throws IOException when the file to be written and read cannot be found
	 */
	public abstract void saveData(ArrayList al) throws IOException;
  
	/** Overwrite content to the given file. */
	public static void write(String fileName, ArrayList data, boolean write) throws IOException  {
		PrintWriter out = new PrintWriter(new FileWriter(fileName,write));
	
		try {
			for (int i =0; i < data.size() ; i++) {
				out.println((String)data.get(i));
			}
		}
		finally {
			out.close();
		}
	}

	/**
	 * @filename the name of the file
	 *  Read the contents of the given file. */
	public static List read(String fileName) throws IOException {
		ArrayList data = new ArrayList() ;
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()){
				data.add(scanner.nextLine());
			}
		}
		finally{
			scanner.close();
		}
		return data;
	}


}