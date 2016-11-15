package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import db.ConnUtil;
import pojo.Student;

public class Loginservice {
	public Student login(String name){
		Student student = null;
		String sql = "select * from student where name = ?";
		try {
			Connection conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setString(1, name);
			ResultSet rs = sta.executeQuery();
			if(rs.next()){
				student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getString("age"));
			}
			rs.close();
			sta.close();
			conn.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return student;
	}
}
