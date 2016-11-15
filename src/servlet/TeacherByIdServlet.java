package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import pojo.Student;
import pojo.Teacher;
import service.TeacherByIdService;

public class TeacherByIdServlet extends HttpServlet{
	TeacherByIdService teaById = new TeacherByIdService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("id")!=null){
		Teacher teacher = teaById.teacherById(Integer.parseInt(req.getParameter("id")));
		ObjectMapper obj = new ObjectMapper();
		obj.writeValue(resp.getOutputStream(),teacher);
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		Teacher tea = new Teacher();
		tea.setName(name);
		tea.setAge(age);
		teaById.add(tea);
	}
	public void doPut(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String studentIds = request.getParameter("studentIds");
		if(id != null){
			teaById.update(Integer.parseInt(id),name,age,studentIds);
		}
	}
	public void doDelete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		teaById.delete(Integer.parseInt(id));
	}
}
