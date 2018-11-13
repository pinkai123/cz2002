package fileIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entity.*;

public class ResultIO extends FileIO {
	public static final String SEPARATOR = "|";
	protected String fileName = "result.txt";

    // an example of reading
	public ArrayList readData() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(fileName);	
		ArrayList AllResults = new ArrayList(); // to store name of student taking the course

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
					String CourseIDInfo = star.nextToken().trim();
					Course CourseID = Translation.StringtoCourse(CourseIDInfo);
					String StudentNameInfo = star.nextToken().trim();
					Student StudentName =Translation.StringtoStudent(StudentNameInfo);
					Result Result = new Result(CourseID,StudentName);
					while(star.hasMoreTokens()) {
						String[] GradeInfo = star.nextToken().trim().split(",");
						String type = GradeInfo[0].trim();
						String name = GradeInfo[1].trim();
						double mark = Double.parseDouble(GradeInfo[2].trim());
						Result.addGrade(type, name, mark);
				}
					AllResults.add(Result);
        }
			return AllResults ;
	}

  // an example of saving
public void saveData(ArrayList al) throws IOException {
		ArrayList alw = new ArrayList() ;// to store TutLabs data

        for (int i = 0 ; i < al.size() ; i++) {
        		Result Result = (Result)al.get(i);
        		StringBuilder st =  new StringBuilder() ;
				st.append(Result.getCourse().getCourseID());
				st.append(SEPARATOR);
				st.append(Result.getStudent().getName());
				st.append(SEPARATOR);
				ArrayList<Grade> Grades = new ArrayList();
				Grades = Result.getAllGrades();
				for(int j = 0; j <Grades.size();j++) {
					 Grade Grade = Grades.get(j);
					 st.append(Grade.getType());
					 st.append(",");
					 st.append(Grade.getName());
					 st.append(",");
					 st.append(Grade.getMark());
				}
				alw.add(st.toString()) ;
			}
			write(fileName,alw,false);
	}
}