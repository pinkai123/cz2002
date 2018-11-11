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

public class IO1 {
	public static void main(String[]args ){
	String[] data = {"Hello","world"};
	PrintWriter out = null;
	try {
		out = new PrintWriter(new FileOutputStream("hello.txt",false));
		for (int i =0; i < data.length ; i++) {
      		out.println((String)data[i]);
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    finally {
      out.close();
    }
	}
}