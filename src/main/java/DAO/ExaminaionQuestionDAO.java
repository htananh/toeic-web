package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.examinationquestion;
import BEAN.result;

public class ExaminaionQuestionDAO {
	public static List<examinationquestion> DisplayExaminationQuestionPage(Connection conn,int examinationid){
		List<examinationquestion> list = new ArrayList<>();
		String sql = "Select * from examinationquestion where examinationid="+examinationid;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				examinationquestion examqt=new examinationquestion();
				int num = resultSet.getInt("num");
			    String imagequestion = resultSet.getString("imagequestion");
			    String audiogg = resultSet.getString("audiogg");
			    String audiomp3 = resultSet.getString("audiomp3");
			    String paragraph = resultSet.getString("paragraph");
			    String question = resultSet.getString("question");
			    String option1 = resultSet.getString("option1");
			    String option2 = resultSet.getString("option2");
			    String option3 = resultSet.getString("option3");
			    String option4 = resultSet.getString("option4");
			    String correctanswer = resultSet.getString("correctanswer");
			    
			    
			     examqt.setNum(num);
	        	 examqt.setImagequestion(imagequestion);
	        	 examqt.setAudiogg(audiogg);
	        	 examqt.setAudiomp3(audiomp3);
	        	 examqt.setParagraph(paragraph);
	        	 examqt.setQuestion(question);
	        	 examqt.setOption1(option1);
	        	 examqt.setOption2(option2);
	        	 examqt.setOption3(option3);
	        	 examqt.setOption4(option4);
	        	 examqt.setCorrectanswer(correctanswer);
	        	 examqt.setExaminationid(examinationid);
			    list.add(examqt);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
		
	}
	public static void InsertResultQuestion(Connection conn,result rs) {
		String sql = "INSERT INTO result (correctanswernum,incorrectanswernum,examinationid,memberid) VALUES (?,?,?,?)";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			
			int correctanswernum = rs.getCorrectanswernum();
			int incorrectanswernum = rs.getIncorrectanswernum();
			int examinationid=rs.getExaminationid();
			int memberid = rs.getMemberid();
			
			statement.setInt(1, correctanswernum);
			statement.setInt(2, incorrectanswernum);
			statement.setInt(3, examinationid);
			statement.setInt(4, memberid);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				
			}
			
		} catch (SQLException e) {
			
		}
		
	}
	public static int lengtQuestion(Connection conn,int examinationid) {
		int count =0;
		
		String sql = "Select count(*) from examinationquestion where examinationid="+examinationid;
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
	public static String GetCorrectanswer(Connection conn,int num) {
		String correctanswer ="";
		
		String sql = "Select correctanswer from examinationquestion where num="+num;
		PreparedStatement statement=null;
		
		try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			correctanswer=resultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return correctanswer;
		
	}
}
