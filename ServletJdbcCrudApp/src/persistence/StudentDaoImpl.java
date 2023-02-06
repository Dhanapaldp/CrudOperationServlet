package persistence;

import dataobject.Student;
import utility.Util;

import java.io.IOException;
import java.sql.*;

public class StudentDaoImpl implements IStudentDao
{

	Connection connection=null;
	PreparedStatement pstmt=null;
	ResultSet resultSet=null;
	@Override
	public String addStudent(Student student) {

			String sqlInsertQuery = "insert into studentdata(`sname`,`sage`,`saddress`)values(?,?,?)";
			try {
				connection =Util.getConnect();

				if (connection != null)
					pstmt = connection.prepareStatement(sqlInsertQuery);

				if (pstmt != null) {

					pstmt.setString(1, student.getName());
					pstmt.setInt(2, student.getAge());
					pstmt.setString(3, student.getAddress());

					int rowAffected = pstmt.executeUpdate();
					if (rowAffected == 1) {
						return "success";
					}
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}

			return "failure";
		}

	@Override
	public Student selectStudent(Integer id) {
		String sqlSelectQuery = "select * from studentdata where sid = ?";
		Student student = null;

		try {
			connection = Util.getConnect();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);

			if (pstmt != null)
				pstmt.setInt(1, id);

			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
			}

			if (resultSet != null) {

				if (resultSet.next()) {
					student = new Student();

					// copy resultSet data to student object
					student.setId(resultSet.getInt(1));
					student.setName(resultSet.getString(2));
					student.setAge(resultSet.getInt(3));
					student.setAddress(resultSet.getString(4));

					return student;
				}

			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return student;
	}

	@Override
	public String updateStudent(Student student) {
		try
		{
			connection=Util.getConnect();
			pstmt=connection.prepareStatement("update studentdata set sname=?,sage=?,saddress=? where sid=?");
			
			//Set the values to parameters
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			pstmt.setString(3, student.getAddress());
			pstmt.setInt(4, student.getId());
			
			//Executing Query
			int rows=pstmt.executeUpdate();
			
			if(rows==1)
				return "success";
			else
				return "not found";
		}catch(IOException | SQLException e){
			e.getMessage();
		}
		
		return "failure";
	}

	@Override
	public String deleteStudent(Integer id) {
		try
		{
			connection=Util.getConnect();
			pstmt=connection.prepareStatement("delete from studentdata where sid=?");
			
			//Set the values to parameters
			if(pstmt!=null)
			{
				pstmt.setInt(1, id);
				
				//Executing Query
				int rows=pstmt.executeUpdate();
				
				if(rows==1)
					return "success";
				else
					return "not found";
			}
		}catch(IOException | SQLException e){
			e.getMessage();
		}
		
		return "failure";
	}
}
