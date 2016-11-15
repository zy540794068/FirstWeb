package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException,SQLException{
			Connection con ;
			Statement sql; //声明Statement对象
			ResultSet rs;
			try{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/zhouyu","root","root");
			sql=(Statement) con.createStatement();
			rs=sql.executeQuery("Select * FROM student ");
			while(rs.next()){
			String name=rs.getString(2);
			String age = rs.getString(3);
			System.out.print(name+"  "+age);//輸出信息
			System.out.println();
			}
			con.close();
			con.close();
			}
			catch(SQLException el){}
			}
	
}
