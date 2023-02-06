package servicefactory;

import service.IStudentService;
import service.StudentServiceImpl;

public class StudentServiceFactory {
	
	static IStudentService studentService=null;
	//Make constructor private o avoid object creation(Singleton)
	private StudentServiceFactory()
	{
		
	}
	public static IStudentService getStudentService()
	{
		studentService=new StudentServiceImpl();
		return studentService;
		
	}
	

}

