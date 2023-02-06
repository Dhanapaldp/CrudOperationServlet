package daofactory;

import persistence.IStudentDao;
import persistence.StudentDaoImpl;

public class StudentDaoFactory {

	static IStudentDao studentDao=null;
	private StudentDaoFactory()
	{
		
	}
	public static IStudentDao getStudentDao()
	{
		studentDao=new StudentDaoImpl();
		return studentDao;
	}
}
