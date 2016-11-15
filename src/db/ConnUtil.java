package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnUtil {
	public static Connection getMySQLConnection() throws ClassNotFoundException,SQLException{
		String hostName = "localhost";
		String dbName = "zhouyu";
		String userName = "root";
		String password = "root";
		return getMySQLConnection(hostName,dbName,userName,password);
	}
	public static Connection getMySQLConnection(String hostName,String dbName,String userName,String password) 
			throws SQLException,ClassNotFoundException{
		String connectionURL = "jdbc:mysql://"+hostName+":3306/"+dbName;
		Connection coon = DriverManager.getConnection(connectionURL, userName, password);
		return coon;
	}
	public static Connection getJNDIConnection() throws NamingException, SQLException{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jndi/mybatis");
		Connection conn = ds.getConnection();
		return conn;
	}
}
