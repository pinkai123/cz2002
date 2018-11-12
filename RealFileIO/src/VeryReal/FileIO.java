package VeryReal;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class FileIO {
	public static final String SEPARATOR = "|";

    // an example of reading
	public abstract ArrayList readData(String filename) throws IOException ;
  // an example of saving
	public abstract void saveData(String filename, List al) throws IOException;
	
  
	/** Write fixed content to the given file. */
  public static void write(String fileName, List data) throws IOException  {
    PrintWriter out = new PrintWriter(new FileWriter(fileName,true));

    try {
		for (int i =0; i < data.size() ; i++) {
      		out.println((String)data.get(i));
		}
    }
    finally {
      out.close();
    }
  }
  public static void overwrite(String fileName, List data) throws IOException  {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileOutputStream(fileName,false));
			for (int i =0; i < data.size() ; i++) {
	      		out.println((String)data.get(i));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally {
	      out.close();
	    }
	  }


  /** Read the contents of the given file. */
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
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