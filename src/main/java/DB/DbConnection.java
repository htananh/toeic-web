package DB;

import java.sql.*;
public class DbConnection {

	public static Connection CreateConnnection() {
		
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/webtoeic?useUnicode=true&characterEncoding=UTF-8";
		String username = "root";
		String password = "1234";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// create connection
           
			conn = DriverManager.getConnection(url,username,password);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return conn;
	}
}
