package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import pojo.Student;
import service.StudentService;
public class StudentServlet extends HttpServlet{
	StudentService stuservice = new StudentService();
	public void doGet(HttpServletRequest request,HttpServletResponse response){	
		ObjectMapper objMapper = new ObjectMapper();
		try {
			objMapper.writeValue(response.getOutputStream(), stuservice.print());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		Student stu = new Student();
		stu.setName(name);
		stu.setAge(age);
		stuservice.add(stu);
	}
	public void doPut(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String age =request.getParameter("age");
		String id =request.getParameter("id");
		Student stu = new Student();
		stu.setName(name);
		stu.setAge(age);
		stuservice.update(stu,id);
	}
	public void doDelete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		stuservice.delete(Integer.parseInt(id));
	}
}
