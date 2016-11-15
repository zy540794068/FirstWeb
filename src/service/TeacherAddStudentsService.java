package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

import db.ConnUtil;

public class TeacherAddStudentsService {
	public void teacherAddStudentsService(Integer teacherId,String studentId) 
			throws NamingException, SQLException{
		String sql = "update student set teacher_Id=? where id=?";
		Connection conn = ConnUtil.getJNDIConnection();
		for(String stu: studentId.split(",")){
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1, teacherId);
			sta.setString(2, stu);
			sta.execute();
			sta.close();
			conn.close();
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
