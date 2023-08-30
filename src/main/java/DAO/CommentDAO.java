package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import BEAN.cmtgrammar;
import BEAN.cmtvocabulary;



public class CommentDAO {
	public static void InsertComment(HttpServletRequest request,Connection conn,String cmtgrammarcontent,int memberid,int grammarguidelineid) {
		String sql = "INSERT INTO cmtgrammar (cmtgrammarcontent,memberid,grammarguidelineid) VALUES (?,?,?)";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			
			
			statement.setString(1, cmtgrammarcontent);
			statement.setInt(2, memberid);
			statement.setInt(3, grammarguidelineid);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Thêm thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Thêm thất bại!!");
		}
	}
	public static List<cmtgrammar> DisplayComment (Connection conn,int grammarguidelineid) {
		List<cmtgrammar> list = new ArrayList<>();
		String sql = "Select * from cmtgrammar where grammarguidelineid="+grammarguidelineid;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				cmtgrammar cmtgr= new cmtgrammar();
				int memberid=resultSet.getInt("memberid");
				String cmtgrammarcontent = resultSet.getString("cmtgrammarcontent");
				String fullname=DAO.CommentDAO.GetFullName(conn, memberid);
			   
			    cmtgr.setCmtgrammarcontent(cmtgrammarcontent);
			    cmtgr.setFullname(fullname);
			    cmtgr.setGrammarguidelineid(grammarguidelineid);
			    cmtgr.setMemberid(memberid);
			    list.add(cmtgr);
			    
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	public static String GetFullName(Connection conn,int memberid) {
		String fullname="";
		String sql = "Select fullname from member where memberid="+memberid;
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
	public static void InsertCommentVocabulary(HttpServletRequest request,Connection conn,String cmtvocabularycontent,int memberid,int vocabularyguidelineid) {
		String sql = "INSERT INTO cmtvocabulary (cmtvocabularycontent,vocabularyguidelineid,memberid) VALUES (?,?,?)";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			
			
			statement.setString(1, cmtvocabularycontent);
			statement.setInt(2, vocabularyguidelineid);
			statement.setInt(3, memberid);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Thêm thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Thêm thất bại!!");
		}
	}
	public static List<cmtvocabulary> DisplayCommentVocabulary (Connection conn,int vocabularyguidelineid) {
		List<cmtvocabulary> list = new ArrayList<>();
		String sql = "Select * from cmtvocabulary where vocabularyguidelineid="+vocabularyguidelineid;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				cmtvocabulary cmtgr= new cmtvocabulary();
				int memberid=resultSet.getInt("memberid");
				String cmtvocabularycontent = resultSet.getString("cmtvocabularycontent");
				String fullname=DAO.CommentDAO.GetFullName(conn, memberid);
			   
			    cmtgr.setCmtvocabularycontent(cmtvocabularycontent);
			    cmtgr.setFullname(fullname);
			    cmtgr.setVocabularyguidelineid(vocabularyguidelineid);
			    cmtgr.setMemberid(memberid);
			    list.add(cmtgr);
			    
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	
}
