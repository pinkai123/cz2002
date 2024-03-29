package IO;


import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entity.*;
import entity.Lesson.TypeOfLesson;
import others.*;

public class CourseIO extends FileIO {
	/**
	 * @param Seperator to separate each variable in the text file
	 */
	public static final String SEPARATOR = "|";
	/**
	 * @param name of the file name
	 */
	protected String fileName = "course.txt";
	/**
	 * construct courses from course text file
	 * @return list of courses
	 */
	public ArrayList readData() throws IOException {

		ArrayList stringArray = (ArrayList)read(fileName);
		ArrayList alr = new ArrayList() ;// to store TutLabs data
		
		ArrayList Students = new ArrayList(); // to store name of student taking the course
		Weightage weightage = null;
		ArrayList Lessons = new ArrayList();
		Course Course =  null;
		int Vacancy = 0, step = 0;
		String CourseName = null,CourseID = null;
		Professor CourseCoordinator = null;

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				String topic  = star.nextToken().trim();
				if(topic.equals("Course")) {
					CourseID = star.nextToken().trim();// CourseID
					CourseName = star.nextToken().trim();// COurseName
					String Prof = star.nextToken().trim();// COurseCoordinator
					String[] CourseCoordinatorInfo = Prof.split(",");
					CourseCoordinator = new Professor(CourseCoordinatorInfo[0].trim(),CourseCoordinatorInfo[1].trim(),CourseCoordinatorInfo[2].trim());
					Vacancy = Integer.parseInt(star.nextToken().trim()); // Vacancy
					step++;
				}
				else if(topic.equals("Student")) {
					while(star.hasMoreTokens()) {
						String Name = star.nextToken().trim();
						Students.add(Name);
					}
					Students = Translation.StringtoStudents(Students);
					step++;
				}
				else if(topic.equals("Weightage")) {
					if(star.hasMoreTokens()) {
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
					step++;
					
				}
				else if(topic.equals("Lesson")) {
				// get individual 'fields' of the string separated by SEPARATOR
					while(star.hasMoreTokens()) {
						String  Lesson = star.nextToken().trim();
						String[] Lesson1 = Lesson.split(",");// first token
						int Index = Integer.parseInt(Lesson1[0].trim());
						int VacancyLesson = Integer.parseInt(Lesson1[1].trim());
						String Type = Lesson1[2].trim();
						Lesson Tut = new Lesson(Index, VacancyLesson ,TypeOfLesson.valueOf(Type));
						ArrayList<String> StudentName = new ArrayList();
						ArrayList<Student> StudentInfo = new ArrayList();
						for(int i1 =3; i1<Lesson1.length;i1++) {
							StudentName.add(Lesson1[i1].trim());
						}
						StudentInfo = Translation.StringtoStudents(StudentName);
						Tut.addStudentList(StudentInfo);
						Lessons.add(Tut);
					}
					step++;
				}
				if( step == 4) {
					Course = new Course(CourseID,CourseName,CourseCoordinator,Vacancy);
					if(Lessons!= null) {
						Course.setLessonList(Lessons);
						Lessons = new ArrayList();
					}
					if(weightage!= null) {
						Course.setCourseWeightage(weightage);
						weightage = null;
					}
					if(Students != null) {
						Course.setStudentList(Students);
						Students = new ArrayList();
					}
					alr.add(Course);
					Course = null;
					step = 0;
				}
        }
        return alr ;
	}

		/**
		 * @param al list of Course
		 * void save the list of courses into a text file
		 */
public void saveData(ArrayList al) throws IOException {
		ArrayList alw = new ArrayList() ;// to store TutLabs data

        for (int i = 0 ; i < al.size() ; i++) {
        		Course course = (Course)al.get(i);
        		StringBuilder st =  new StringBuilder() ;
        		st.append("Course");
				st.append(SEPARATOR);
				st.append(course.getCourseID());
				st.append(SEPARATOR);
				st.append(course.getCourseName());
				st.append(SEPARATOR);
				Professor Professor = course.getCourseCoordinator();
				st.append(Professor.getName());
				st.append(",");
				st.append(Professor.getMatric());
				st.append(",");
				st.append(Professor.getEmail());
				st.append(SEPARATOR);
				st.append(course.getVacancy());
				alw.add(st.toString()) ;
				// For Add a Course (Student and Weightage not included)
				
				st.setLength(0);
				st.append("Student");
				ArrayList<Student> Student = new ArrayList();
				Student = course.getStudentList();
				if(Student != null) {
					for(int j = 0; j <Student.size();j++) {
						st.append(SEPARATOR);
						st.append(Student.get(j).getName());
					}
				}
				alw.add(st.toString()) ;
				
				st.setLength(0);
				Weightage Weightage = course.getCourseWeightage(); 
				st.append("Weightage");
				st.append(SEPARATOR);
				if(Weightage!= null) {
					st.append(Weightage.getMainPercentage());
					st.append(SEPARATOR);
					st.append(Weightage.getCourseworkPercentage());
					st.append(SEPARATOR);
					st.append(Weightage.getHaveSub());
					ArrayList<Subcomponent> Subcomponents = new ArrayList();
					Subcomponents = Weightage.getSubcomponent();
					for(int j = 0; j <Subcomponents.size();j++) {
						 Subcomponent TutLab = Subcomponents.get(j);
						 st.append(SEPARATOR);
						 st.append(TutLab.getName());
						 st.append(SEPARATOR);
						 st.append(TutLab.getPercentage());
					}
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
					st.append(SEPARATOR);
				}
				alw.add(st.toString()) ;
			}
			write(fileName,alw,false);
	}
}