package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class StuTest {
	public static void main(String[] args) throws SQLException {
		Connection conn;
		try {
			conn = ConnUtil.getMySQLConnection();
			PreparedStatement sta = (PreparedStatement) conn.prepareStatement("select * from teacher");
			ResultSet rs = sta.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
