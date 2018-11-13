package fileIO;

import java.io.*;
import java.util.*;

import entity.*;
import others.*;

public abstract class FileIO {
	public static final String SEPARATOR = "|";
	protected String fileName;
	
	// an example of reading
	public abstract ArrayList readData() throws IOException ;
	// an example of saving
	public abstract void saveData(ArrayList al) throws IOException;
  
	/** Write fixed content to the given file. */
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

	/** Read the contents of the given file. */
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