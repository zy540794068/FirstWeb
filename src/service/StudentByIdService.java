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
import pojo.Teacher;

public class StudentByIdService {
	public Student studentById(int id){
		Student stu = null;
			String sql = "select * from student where id=?";
			Connection conn;
			try {
				conn = ConnUtil.getJNDIConnection();
				PreparedStatement sta = conn.prepareStatement(sql);
				sta.setInt(1,id);
				ResultSet rs = sta.executeQuery();
				if(rs.next()){
					stu = new Student();
					stu.setId(rs.getString("id"));
					stu.setName(rs.getString("name"));
					stu.setAge(rs.getString("age"));
					stu.setTeacherId(rs.getInt("teacher_id"));
				}
				rs.close();
				sta.close();
				conn.close();
			}catch (NamingException | SQLException e) {
					e.printStackTrace();
				}
				String sql2 = "select * from teacher where id=?";
				Teacher teacher = null;
				Connection conn2 ;
				try {
					 conn2 = ConnUtil.getJNDIConnection();
					PreparedStatement sta2 = conn2.prepareStatement(sql2);
					sta2.setInt(1,stu.getTeacherId());
					ResultSet rs2 = sta2.executeQuery();
					if(rs2.next()){
						teacher = new Teacher();
						teacher.setId(rs2.getInt("id"));
						teacher.setName(rs2.getString("name"));
						teacher.setAge(rs2.getString("age"));
						stu.setTeacher(teacher);
					}
				rs2.close();
				sta2.close();
				conn2.close();
			} catch (NamingException | SQLException e) {
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
//			sta.setInt(3, student.getTeacherId());
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
			Connection conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta = conn.prepareStatement("update student set name=?,age=?,teacher_id=? where id=?");
			sta.setString(1, student.getName());
			sta.setString(2, student.getAge());
			sta.setInt(3, student.getTeacherId());
			sta.setString(4, id);
			sta.execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
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
