package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.GrammarGuideLine;

public class SearchDAO {
	public static void DisplaySearch(HttpServletRequest request,Connection conn,String nameSearch) {
		List<GrammarGuideLine> list = new ArrayList<>();
		String sql = "Select * from grammarguideline where grammarname LIKE '%"+nameSearch+"%'";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			if (!resultSet.isBeforeFirst()) {
				request.setAttribute("message", "Không tìm thấy kết quả");
			}else {
				while (resultSet.next()) {
					GrammarGuideLine gr=new GrammarGuideLine();
					int id = resultSet.getInt("grammarguidelineid");
				    String grammarname = resultSet.getString("grammarname");
				    String grammarimage = resultSet.getString("grammarimage");
				    String content = resultSet.getString("content");
				    
				    gr.setGrammarguidelineid(id);
				    gr.setGrammarname(grammarname);
				    gr.setGrammarimage(grammarimage);
				    gr.setContent(content);
				    list.add(gr);
				}
				
			}
			
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 request.setAttribute("ListGrammarGuideLine", list);
		
	}
	
}
