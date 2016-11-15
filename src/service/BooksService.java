package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import db.ConnUtil;
import pojo.Book;
import pojo.Computer;

public class BooksService {
	public List<Book> allBook() throws NamingException, SQLException{
		List<Book> listb = new ArrayList<Book>();
		String sql = "select * from book";
		Connection conn = ConnUtil.getJNDIConnection();
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		while(rs.next()){
			Book b = new Book();
			b.setId(rs.getInt("id"));
			b.setName(rs.getString("name"));
			listb.add(b);
		}
		rs.close();
		sta.close();
		conn.close();
		return listb;
	}
}
