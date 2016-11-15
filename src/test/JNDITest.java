package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JNDITest {
	public void testJNDI() throws NamingException, SQLException{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jndi/mybatis");
		Connection conn = ds.getConnection();
		PreparedStatement sta = conn.prepareStatement("select * from student");
		ResultSet rs = sta.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString("name"));
		}
		//访问网站 做到登录界面  访问一次servlet 技术加1；
	}
}
