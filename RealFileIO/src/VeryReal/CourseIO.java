package VeryReal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CourseIO extends FileIO {
	public static final String SEPARATOR = "|";

    // an example of reading
	public ArrayList readData(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store TutLabs data
		ArrayList Students = new ArrayList(); // to store name of student taking the course
		weightage weightage = null;
		ArrayList TutLab = new ArrayList();

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				String topic  = star.nextToken().trim();
				if(topic.equals("Course")) {
					alr.add(star.nextToken().trim());
				}
				else if(topic.equals("Vacany")) {
					alr.add(Integer.parseInt(star.nextToken().trim()));
				}
				else if(topic.equals("Student")) {
					while(star.hasMoreTokens()) {
						String Name = star.nextToken().trim();
						Students.add(Name);
					}
				}
				else if(topic.equals("Weightage")) {
					double mainPercentage = Double.parseDouble(star.nextToken().trim());
					double courseworkPercentage = Double.parseDouble(star.nextToken().trim());
					boolean haveSub = Boolean.parseBoolean(star.nextToken().trim());
					weightage = new weightage(mainPercentage,courseworkPercentage,haveSub);
					while(star.hasMoreTokens()) {
						String Name = star.nextToken().trim();
						double Percentage = Double.parseDouble(star.nextToken().trim());
						weightage.setSubcomponent(Name,Percentage);
					}

				}
				else if(topic.equals("Lesson")) {
				// get individual 'fields' of the string separated by SEPARATOR
				while(star.hasMoreTokens()) {
					String  Lesson = star.nextToken().trim();
					String[] Lesson1 = Lesson.split(",");// first token
					String name = Lesson1[0].trim();
					int Vacany = Integer.parseInt(Lesson1[1].trim());
					Lesson Tut = new Lesson(Vacany,name);
					int i1 =2;
					while(Lesson1[i1] != null) {
						Tut.addStudent(Lesson1[i].trim());
						i1++;
					}
					TutLab.add(Tut);
				}
				// add to TutLabs list

				}
			}
			alr.add(Students);
			alr.add(weightage);
			alr.add(TutLab) ;
			return alr ;
	}

  // an example of saving
public void saveData(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store TutLabs data

        for (int i = 0 ; i < al.size() ; i++) {
        		Course course = (Course)al.get(i);
        		StringBuilder st =  new StringBuilder() ;
        		st.append("Course");
				st.append(SEPARATOR);
				st.append(course.getName());
				alw.add(st.toString()) ;
				
				st.setLength(0);
				st.append("Vacany");
				st.append(SEPARATOR);
				st.append(course.getVacany());
				alw.add(st.toString()) ;
				
				st.setLength(0);
				st.append("Student");
				ArrayList<String> Student = new ArrayList();
				Student = course.getStudent();
				for(int j = 0; j <Student.size();j++) {
					st.append(SEPARATOR);
					st.append(Student.get(j));
				}
				alw.add(st.toString()) ;
				
				st.setLength(0);
				st.append("Weightage");
				st.append(SEPARATOR);
				st.append(course.getMainPercentage());
				st.append(SEPARATOR);
				st.append(course.getCourseWorkPercentage());
				st.append(SEPARATOR);
				st.append(course.getHaveSub());
				ArrayList<String> TutLab = new ArrayList();
				TutLab = course.getSubcomponent();
				for(int j = 0; j <TutLab.size();j++) {
					 Subcomponent TutLab1 = TutLab[j];
					 st.append(SEPARATOR);
					 st.append(TutLab1.getName());
					 st.append(SEPARATOR);
					 st.append(TutLab1.getPercentage());
				}
				alw.add(st.toString()) ;
				
				st.setLength(0);
				ArrayList<Lesson> Lessons = new ArrayList();
				Lessons = course.getLessons();
				Lesson Lesson = null;
				st.append("Lesson");
				st.append(SEPARATOR);
				for(int k = 0; k< Lessons.size();k++) {
				Lesson = Lessons[k];
				st.append(Lesson.getLessonIndex());
				st.append(",");
				st.append(Lesson.getVacancy());
				st.append(",");
				st.append(Lesson.getLType());
				ArrayList<Student> Student = new ArrayList();
				Student = Lesson.getStudentList();
				for(int j = 0; j <Student.size();j++) {
					st.append(",");
					st.append(Student.get(j).getName());
				}
				}
				alw.add(st.toString()) ;
			}
			overwrite(filename,alw);
	}
}
