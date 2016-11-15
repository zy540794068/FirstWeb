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

public class Computerservice {
	public List<Computer> print() throws NamingException, SQLException{
		List<Computer> listcom = new ArrayList<>();
		String sql = "select * from computer";
		Connection conn = ConnUtil.getJNDIConnection();
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		while(rs.next()){
			Computer com = new Computer();
			com.setId(rs.getInt("id"));
			com.setName(rs.getString("name"));
			com.setLocation(rs.getString("location"));
			listcom.add(com);
		}
		rs.close();
		sta.close();
		conn.close();
		return listcom;
	}
}
