package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import db.ConnUtil;
import pojo.Computer;
import pojo.Student;
import pojo.Teacher;

public class ComputersService {
	public Computer allComputer(int id) throws ClassNotFoundException{
		Computer com = null;
		String sql = "select * from computer where id=?";
		Connection conn;
		try {
			conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1,id);
			ResultSet rs = sta.executeQuery();
			if(rs.next()){
				com = new Computer();
				com.setId(Integer.parseInt(rs.getString("id")));
				com.setName(rs.getString("name"));
			}
			rs.close();
			sta.close();
			conn.close();
		}catch (NamingException | SQLException e) {
				e.printStackTrace();
			}
			String sql2 = "select * from student where id=?";
			Student student = null;
			Connection conn2 ;
			try {
				 conn2 = ConnUtil.getJNDIConnection();
				PreparedStatement sta2 = conn2.prepareStatement(sql2);
				sta2.setInt(1,com.getId());
				ResultSet rs2 = sta2.executeQuery();
				if(rs2.next()){
					student = new Student();
					student.setId(rs2.getString("id"));
					student.setName(rs2.getString("name"));
					student.setAge(rs2.getString("age"));
					com.setStudent(student);
				}
			rs2.close();
			sta2.close();
			conn2.close();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
			return com;
	}
	public void add(Computer computer){
		try {
			Connection conn = ConnUtil.getMySQLConnection();
			PreparedStatement sta = conn.prepareStatement("insert into computer(name,location) value(?,?)");
			sta.setString(1, computer.getName());
			sta.setString(2, computer.getLocation());
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
	public void update(Integer id,String name,String location,Integer studentId){
		Computer com = null;
		Connection conn;
		String sql = "update computer set name=?,location=?,student_id=? where id=?";
		try {
			conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setString(1, name);
			sta.setString(2,location);
			sta.setInt(3, studentId);
			sta.setInt(4, id);
			sta.execute();
			sta.close();
			conn.close();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(int id){
		try {
			Connection conn = ConnUtil.getMySQLConnection();
			PreparedStatement sta = conn.prepareStatement("delete from computer where id=?");
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
