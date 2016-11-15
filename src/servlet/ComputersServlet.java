package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import pojo.Computer;
import pojo.Teacher;
import service.ComputersService;

public class ComputersServlet extends HttpServlet{
	ComputersService comservice = new ComputersService();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Computer computer;
		ObjectMapper obj = new ObjectMapper();
		try {
			computer = comservice.allComputer(Integer.parseInt(req.getParameter("id")));
			obj.writeValue(resp.getOutputStream(), computer);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		Computer com = new Computer();
		com.setName(name);
		com.setLocation(location);
		comservice.add(com);
	}
	public void doPut(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		String studentId = request.getParameter("studentId");
		if(id != null){
			comservice.update(Integer.parseInt(id), name, location, Integer.parseInt(studentId));
		}
	}
	public void doDelete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		comservice.delete(Integer.parseInt(id));
	}
}
