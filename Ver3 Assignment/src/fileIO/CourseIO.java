package fileIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import entity.Weightage;
import entity.Lesson;
import entity.Lesson.TypeOfLesson;
import entity.Student;
import entity.Course;
import entity.Person;
import entity.Professor;
import entity.Subcomponent;

public class CourseIO extends FileIO {
	public static final String SEPARATOR = "|";

    // an example of reading
	public ArrayList readData(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store TutLabs data
		ArrayList Students = new ArrayList(); // to store name of student taking the course
		Weightage weightage = null;
		ArrayList TutLab = new ArrayList();

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				String topic  = star.nextToken().trim();
				if(topic.equals("Course")) {
					alr.add(Integer.parseInt(star.nextToken().trim()));// CourseID
					alr.add(star.nextToken().trim());// COurseName
					alr.add(star.nextToken().trim());// COurseCoordinator
					alr.add(Integer.parseInt(star.nextToken().trim())); // Vacancy
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
					weightage = new Weightage(mainPercentage,courseworkPercentage,haveSub);
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
					int Index = Integer.parseInt(Lesson1[0].trim());
					int Vacancy = Integer.parseInt(Lesson1[1].trim());
					String Type = Lesson1[2].trim();
					Lesson Tut = new Lesson(Index, Vacancy ,TypeOfLesson.valueOf(Type));
					int i1 =2;
					while(Lesson1[i1] != null) {
						//get student class from name
						Tut.addStudentToLesson(Lesson1[i1].trim());
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
				st.append(course.getCourseID());
				st.append(SEPARATOR);
				st.append(course.getCourseName());
				st.append(SEPARATOR);
				st.append(course.getCourseCoordinator());
				st.append(SEPARATOR);
				st.append(course.getVacancy());
				alw.add(st.toString()) ;
				
				st.setLength(0);
				st.append("Student");
				ArrayList<Student> Student = new ArrayList();
				Student = course.getStudentList();
				for(int j = 0; j <Student.size();j++) {
					st.append(SEPARATOR);
					st.append(Student.get(j));
				}
				alw.add(st.toString()) ;
				
				st.setLength(0);
				Weightage Weightage = course.getCourseWeightage(); 
				st.append("Weightage");
				st.append(SEPARATOR);
				st.append(Weightage.getMainPercentage());
				st.append(SEPARATOR);
				st.append(Weightage.getCourseworkPercentage());
				st.append(SEPARATOR);
				st.append(Weightage.getHaveSub());
				Subcomponent[] Subcomponents = new Subcomponent[10];
				Subcomponents = Weightage.getSubcomponent();
				for(int j = 0; j <Subcomponents.length;j++) {
					 Subcomponent TutLab = Subcomponents[j];
					 st.append(SEPARATOR);
					 st.append(TutLab.getName());
					 st.append(SEPARATOR);
					 st.append(TutLab.getPercentage());
				}
				alw.add(st.toString()) ;
				
				st.setLength(0);
				ArrayList<Lesson> Lessons = new ArrayList();
				Lessons = course.getLessonList();
				Lesson Lesson = null;
				st.append("Lesson");
				st.append(SEPARATOR);
				for(int k = 0; k< Lessons.size();k++) {
				Lesson = Lessons.get(k);
				st.append(Lesson.getLessonIndex());
				st.append(",");
				st.append(Lesson.getVacancy());
				st.append(",");
				st.append(Lesson.getLType());
				Student = Lesson.getStudentList();
				for(int j = 0; j <Student.size();j++) {
					st.append(",");
					st.append(Student.get(j).getName());
				}
				}
				alw.add(st.toString()) ;
			}
			write(filename,alw,false);
	}
}