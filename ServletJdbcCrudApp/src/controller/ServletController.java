package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataobject.Student;
import service.IStudentService;
import servicefactory.StudentServiceFactory;

@WebServlet("/Controller/*")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletController() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	public void  doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		IStudentService stdService = StudentServiceFactory.getStudentService();

		if (request.getRequestURI().endsWith("addform")) {

			String age = request.getParameter("age");
			String name = request.getParameter("name");
			String address = request.getParameter("address");

			Student student = new Student();
			student.setName(name);
			student.setAge(Integer.parseInt(age));
			student.setAddress(address);

			String status = stdService.addStudent(student);
			PrintWriter out = response.getWriter();

			if (status.equals("success")) {
				out.println("<h1 style='color:green; text-align:center;'>REGISTRATION SUCCESSFULL</h1>");
			} else {
				out.println("<h1 style='color:green; text-align:center;'>REGISTRATION FAILED</h1>");
			}
			out.close();
		}
		if (request.getRequestURI().endsWith("searchform")) {
			String id = request.getParameter("id");

			Student student = stdService.selectStudent(Integer.parseInt(id));
			PrintWriter out = response.getWriter();
			if (student != null) {
				out.println("<body>");
				out.println("<br/><br/><br/>");
				out.println("<center>");
				out.println("<h1 style='color:red;text-align:center;'>STUDENT RECORD </h1>");
				out.println("<table border='1'>");
				out.println("<tr><th>ID</th><td>" + student.getId() + "</td></tr>");
				out.println("<tr><th>NAME</th><td>" + student.getName() + "</td></tr>");
				out.println("<tr><th>AGE</th><td>" + student.getAge() + "</td></tr>");
				out.println("<tr><th>ADDRESS</th><td>" + student.getAddress() + "</td></tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
			} else {
				out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + id
						+ "</h1>");
			}
			out.close();
		}
		if(request.getRequestURI().endsWith("updateform"))
		{
			String id = request.getParameter("id");
			PrintWriter out = response.getWriter();
			Student student = stdService.selectStudent(Integer.parseInt(id));
			if (student != null) {
				out.println("<body>");
				out.println("<br/><br/><br/>");
				out.println("<center>");
				out.println("<form method='post' action='./Controller/updaterecord'>");
				out.println("<table border='1'>");
				out.println("<tr><th>ID</th><td>" + student.getId() + "</td></tr>");
				out.println("<input type='hidden' name='id' value='" + student.getId() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='name' value='" + student.getName() + "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='age' value='" + student.getAge() + "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='address' value='" + student.getAddress() + "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr></br");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			}else {
					// display not found message
					out.println("<body>");
					out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the given id :: " + id
							+ "</h1>");
					out.println("</body>");
				}
			out.close();
		}
		if(request.getRequestURI().endsWith("updaterecord"))
		{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String address = request.getParameter("address");
			
			Student student=new Student();
			student.setId(Integer.parseInt(id));
			student.setName(name);
			student.setAge(Integer.parseInt(age));
			student.setAddress(address);
			
			String status = stdService.updateStudent(student);
			PrintWriter out = response.getWriter();
			if (status.equals("success")) {
				out.println("<h1 style='color:green; text-align:center;'>UPDATED SUCCESSFULLY</h1>");
			} else {
				out.println("<h1 style='color:green; text-align:center;'>UPDATION FAILED</h1>");
			}
			out.close();
		}
		if(request.getRequestURI().endsWith("deleteform"))
		{
			String id = request.getParameter("id");
			String status = stdService.deleteStudent(Integer.parseInt(id));
			
			PrintWriter out = response.getWriter();
			if (status.equals("success")) {
				out.println("<h1 style='color:green; text-align:center;'>DELETED SUCCESSFULLY</h1>");
			} else {
				out.println("<h1 style='color:green; text-align:center;'>DELETION FAILED</h1>");
			}
			out.close();
			
		}
	}

}
