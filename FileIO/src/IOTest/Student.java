package IOTest;

public class Student {
	private String name ;
	private String email ;
	private String MatriNum ;

	public Student(String n, String e, String c)  {
		name = n ;
		MatriNum = e ;
		email = c ;
	}
	public String getName() { return name ; }
	public String getMatriNum() { return MatriNum ; }
	public String getEmail() { return email ; }

}

