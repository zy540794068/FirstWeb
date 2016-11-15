package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import db.ConnUtil;
import pojo.Student;

public class StudentService {
	public List<Student> print() throws ClassNotFoundException{
		List<Student> stu = new ArrayList<>();
		Student student = null;
		Connection conn;
		PreparedStatement sta;
		ResultSet rs;
		try {
			conn = ConnUtil.getJNDIConnection();
			 sta = conn.prepareStatement("select * from student");
			 rs = sta.executeQuery();
			while(rs.next()){
				student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getString("age"));
				stu.add(student);
			}
			rs.close();
			sta.close();
			conn.close();
		}
		catch (NamingException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu;
	}
	public void add(Student student){
		try {
			Connection conn = ConnUtil.getMySQLConnection();
			PreparedStatement sta = conn.prepareStatement("insert into student(name,age) value(?,?)");
			sta.setString(1, student.getName());
			sta.setString(2, student.getAge());
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
	public void update(Student student,String id){
		try {
			Connection conn = ConnUtil.getMySQLConnection();
			PreparedStatement sta = conn.prepareStatement("update student set name=?,age=? where id=?");
			sta.setString(1, student.getName());
			sta.setString(2, student.getAge());
			sta.setString(3, id);
			sta.execute();
			conn.close();
		} catch (ClassNotFoundException e) {
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
			PreparedStatement sta = conn.prepareStatement("delete from student where id=?");
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
