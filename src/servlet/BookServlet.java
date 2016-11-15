package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import pojo.Book;
import pojo.Teacher;
import service.BookService;

public class BookServlet extends HttpServlet{
	BookService bookservice = new BookService();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Book book = bookservice.allBook(Integer.parseInt(req.getParameter("id")));
		ObjectMapper obj = new ObjectMapper();
		obj.writeValue(resp.getOutputStream(),book);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Book b = new Book();
		b.setId(Integer.parseInt(id));
		b.setName(name);
		bookservice.add(b);
	}
	public void doDelete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		bookservice.delete(Integer.parseInt(id));
	}
}
