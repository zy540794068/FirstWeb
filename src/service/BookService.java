package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import db.ConnUtil;
import pojo.Book;
import pojo.Student;
import pojo.Teacher;

public class BookService {
	public Book allBook(int id){
		List<Student> liststu = new ArrayList<Student>();
		Book book = null;
		Student student = null;
		String sql = "select * from book where id=?";
		Connection conn;
		try {
			conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1,id);
			ResultSet rs = sta.executeQuery();
			while(rs.next()){
				book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		String sql2 = "select student.* from student inner join stu_book on student.id=stu_book.stu_id where stu_book.book_id=?";
		try {
			conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta2 = conn.prepareStatement(sql2);
			sta2.setInt(1, book.getId());
			ResultSet rs = sta2.executeQuery();
			while(rs.next()){
				student = new Student();
				student.setName(rs.getString("name"));
				liststu.add(student);
			}
			book.setStudentList(liststu);
			rs.close();
			sta2.close();
			conn.close();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	public void add(Book book){
		try {
			Connection conn = ConnUtil.getMySQLConnection();
			PreparedStatement sta = conn.prepareStatement("insert into book(id,name) value(?,?)");
			sta.setInt(1, book.getId());
			sta.setString(2, book.getName());
			sta.execute();
			sta.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(Integer id,String name,String studentIds){
		Book book = null;
		String sql = "update book set name=? where id=?";
		try {
			Connection conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta = conn.prepareStatement(sql);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(int id){
		try {
			Connection conn = ConnUtil.getMySQLConnection();
			PreparedStatement sta = conn.prepareStatement("delete from book where id=?");
			sta.setInt(1,id);
			sta.execute();
			sta.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
