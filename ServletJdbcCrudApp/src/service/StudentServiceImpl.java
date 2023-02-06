package service;

import daofactory.StudentDaoFactory;
import dataobject.Student;
import persistence.IStudentDao;

public class StudentServiceImpl implements IStudentService {

	IStudentDao studentDao=null;
	@Override
	public String addStudent(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.addStudent(student);
	}

	@Override
	public Student selectStudent(Integer id) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.selectStudent(id);
	}

	@Override
	public String updateStudent(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer id) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.deleteStudent(id);
	}

}
