package persistence;

import dataobject.Student;

public interface IStudentDao {
	public String addStudent(Student student);
	public Student selectStudent(Integer id);
	public String updateStudent(Student student);
	public String deleteStudent(Integer id);

}
