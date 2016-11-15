package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import pojo.Teacher;
import service.TeacherService;

public class TeacherServlet extends HttpServlet{

	TeacherService teaservice = new TeacherService();
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
			
		ObjectMapper obj = new ObjectMapper();
		try {
			obj.writeValue(response.getOutputStream(),teaservice.print());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		Teacher tea = new Teacher();
		tea.setName(name);
		tea.setAge(age);
		try {
			teaservice.add(tea);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void doPut(HttpServletRequest request,HttpServletResponse response){
//		String name = request.getParameter("name");
//		String age =request.getParameter("age");
//		String id =request.getParameter("id");
//		Student stu = new Student();
//		stu.setName(name);
//		stu.setAge(age);
//		stuservice.update(stu,id);
//	}
//	public void doDelete(HttpServletRequest request,HttpServletResponse response){
//		String name = request.getParameter("name");
//		stuservice.delete(name);
//	}
	
}
