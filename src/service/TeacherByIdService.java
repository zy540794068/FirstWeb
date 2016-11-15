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

public class TeacherByIdService {
	public Teacher teacherById(int id){
		List<Student> liststu = new ArrayList<Student>();
		Teacher teacher = null;
		Student student = null;
		String sql = "select * from teacher where id=?";
		Connection conn;
		try {
			conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1,id);
			ResultSet rs = sta.executeQuery();
			while(rs.next()){
				teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setName(rs.getString("name"));
				teacher.setAge(rs.getString("age"));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		String sql2 = "select * from student where teacher_id=?";
		try {
			conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta2 = conn.prepareStatement(sql2);
			sta2.setInt(1, teacher.getId());
			ResultSet rs = sta2.executeQuery();
			while(rs.next()){
				student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getString("age"));
				student.setTeacherId(rs.getInt("teacher_id"));
				liststu.add(student);
			}
			teacher.setStudentList(liststu);
			rs.close();
			sta2.close();
			conn.close();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
	public void add(Teacher teacher){
		try {
			Connection conn = ConnUtil.getMySQLConnection();
			PreparedStatement sta = conn.prepareStatement("insert into teacher(name,age) value(?,?)");
			sta.setString(1, teacher.getName());
			sta.setString(2, teacher.getAge());
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
	public void update(Integer id,String name,String age,String studentIds){
		String sql = "update teacher set name=?,age=? where id=?";
		Connection conn;
		try {
			conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setString(1,name);
			sta.setString(2, age);
			sta.setInt(3, id);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		try {
			 conn = ConnUtil.getJNDIConnection();
			for(String stu: studentIds.split(",")){
				PreparedStatement sta1 = conn.prepareStatement("update student set teacher_id=? where id=?");
				sta1.setInt(1, id);
				sta1.setInt(2, Integer.parseInt(stu));
				sta1.execute();
				conn.close();	
			}
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
			PreparedStatement sta = conn.prepareStatement("delete from teacher where id=?");
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
