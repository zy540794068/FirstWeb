package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import pojo.Student;
import service.StudentByIdService;

public class StudentByIdServlet extends HttpServlet{
	StudentByIdService stu = new StudentByIdService();
	public void doGet(HttpServletRequest requset,HttpServletResponse response){
		Student student = stu.studentById(Integer.parseInt(requset.getParameter("id")));
		ObjectMapper obj = new ObjectMapper();
		try {
			obj.writeValue(response.getOutputStream(),student);
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
//		String teacherId = request.getParameter("id");
		Student stu1 = new Student();
		stu1.setName(name);
		stu1.setAge(age);
//		stu1.setTeacherId(Integer.parseInt(teacherId));
		stu.add(stu1);
	}
	public void doPut(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String age =request.getParameter("age");
		String teacherId = request.getParameter("teacherId");
		String id =request.getParameter("id");
		Student stu2 = new Student();
		stu2.setName(name);
		stu2.setAge(age);
		stu2.setTeacherId(Integer.parseInt(teacherId));
		stu.update(stu2,id);
	}
	public void doDelete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		stu.delete(Integer.parseInt(id));
	}
}
