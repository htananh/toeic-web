package Controller;

import java.io.IOException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.answerUser;
import BEAN.examinationquestion;
import BEAN.result;


@WebServlet("/SubmitExamination")
public class SubmitExamination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SubmitExamination() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		HttpSession session=request.getSession(true);  
		int memberid = (int) session.getAttribute("SSmemberid");
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		List<examinationquestion> CorrectAnswers= DAO.ExaminaionQuestionDAO.DisplayExaminationQuestionPage(conn, examinationid);
		int lengtQuestion = DAO.ExaminaionQuestionDAO.lengtQuestion(conn,examinationid);
		List<answerUser> listAsUser= new ArrayList();
		int correctanswernum=0;
		for(int i=2;i <= lengtQuestion+1;i++) {
			
			String answers=request.getParameter("ans["+i+"]");
			if(answers!=null) {
			answerUser asUser= new answerUser();
			asUser.setNum(i);
			asUser.setAnswers(answers);
			
			listAsUser.add(asUser);
			String correctanswer= DAO.ExaminaionQuestionDAO.GetCorrectanswer(conn, i);
				if(correctanswer.equals(answers))
				{
					correctanswernum=correctanswernum+1;
				}
			}else {
				answerUser asUser= new answerUser();
				asUser.setNum(i);
				asUser.setAnswers("khongchon");
			}
		}
		int incorrectanswernum= lengtQuestion-correctanswernum;
		result rs = new result();
		rs.setCorrectanswernum(correctanswernum);
		rs.setIncorrectanswernum(incorrectanswernum);
		rs.setExaminationid(examinationid);
		rs.setMemberid(memberid);
		
		DAO.ExaminaionQuestionDAO.InsertResultQuestion(conn,rs);
		List<examinationquestion> list = DAO.ExaminaionQuestionDAO.DisplayExaminationQuestionPage(conn, examinationid);
		request.setAttribute("Listexaminationquestion", list);
		request.setAttribute("examinationid", examinationid);
		request.setAttribute("CorrectAnswers",CorrectAnswers);
		request.setAttribute("listAsUser",listAsUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/ResultExamination.jsp");
		dispatcher.forward(request, response);
				
	}

}
