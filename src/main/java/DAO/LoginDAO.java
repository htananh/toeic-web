package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.member;



public class LoginDAO {
	public static int CheckLogin(Connection conn,member mem) {
		int count=0;
		String sql = "Select count(*) from member where membername='"+mem.getMembername()+"' and memberpass='"+mem.getMemberpass()+"'";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			resultSet.next();
			count=resultSet.getInt(1);
			    
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return count;
		
	}
	
	public static String GetFullName(Connection conn,member mem) {
		String fullname="";
		String sql = "Select fullname from member where membername='"+mem.getMembername()+"' and memberpass='"+mem.getMemberpass()+"'";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
			    
			     fullname = resultSet.getString("fullname");
			   
			    
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return fullname;
	}
	public static int GetMemberId(Connection conn,member mem) {
		int memberid=0;
		String sql = "Select memberid from member where membername='"+mem.getMembername()+"' and memberpass='"+mem.getMemberpass()+"'";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
			    
				memberid = resultSet.getInt("memberid");
			   
			    
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return memberid;
	}
	public static int Authorization(Connection conn,member mem)
	{
		int categorymemberid=0;
		String sql = "Select categorymemberid from member where membername='"+mem.getMembername()+"' and memberpass='"+mem.getMemberpass()+"'";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
			    
				categorymemberid = resultSet.getInt("categorymemberid");
			   
			    
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return categorymemberid;
	}
}
