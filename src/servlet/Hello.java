package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.JNDITest;

public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response){
		int count = 0 ;
		Object obj = request.getServletContext().getAttribute("count");
		if(obj != null){
			count  = Integer.parseInt(obj.toString());
		}
		count++;
		request.getServletContext().setAttribute("count", count);
		try {
			response.getWriter().write(Integer.toString(count));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JNDITest test = new JNDITest();
		try {
			test.testJNDI();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
