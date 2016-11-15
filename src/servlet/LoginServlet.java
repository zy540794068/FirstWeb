package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import pojo.Student;
import service.Loginservice;

public class LoginServlet extends HttpServlet{
	Loginservice service = new Loginservice();
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		Student stu = service.login(req.getParameter("name"));
		req.getSession().setAttribute("name",stu);
		if(stu != null){
			pw.write("frist login"+"   "+stu.getId());
		}else{
			resp.setStatus(401);
			pw.write("login defeat");
		}
	}
}
