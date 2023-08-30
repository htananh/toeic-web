package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.member;
public class RegisterDAO {
	public static void RegisterMember(HttpServletRequest request,HttpServletResponse response,Connection conn, member mem) {
		String sql = "INSERT INTO member (membername, memberpass, categorymemberid, fullname) VALUES (?,?,?,?)";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			String membername=mem.getMembername();
			String memberpass=mem.getMemberpass();
			int categorymemberid=1;
			String fullname=mem.getFullname();
			statement.setString(1, membername);
			statement.setString(2, memberpass);
			statement.setInt(3, categorymemberid);
			statement.setString(4, fullname);
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Đăng kí thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Đăng kí thất bại!!");
		}
		 
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Register.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
