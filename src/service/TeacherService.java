package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import db.ConnUtil;
import pojo.Teacher;

public class TeacherService {
	
	public List<Teacher> print(){
		List<Teacher> listteacher = new ArrayList<>();
		Connection conn;
		PreparedStatement sta;
		ResultSet rs;
		try {
			 conn = ConnUtil.getMySQLConnection();
			 sta = conn.prepareStatement("select * from teacher");
			 rs = sta.executeQuery();
			while(rs.next()){
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setName(rs.getString("name"));
				teacher.setAge(rs.getString("age"));
				listteacher.add(teacher);
			}
			rs.close();
			sta.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listteacher;
	}
	public void add(Teacher teacher) throws NamingException{
		try {
			Connection conn = ConnUtil.getJNDIConnection();
			PreparedStatement sta = conn.prepareStatement("insert into teacher(name,age) value(?,?)");
			sta.setString(1, teacher.getName());
			sta.setString(2, teacher.getAge());
			sta.execute();
			sta.close();
			conn.close();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
