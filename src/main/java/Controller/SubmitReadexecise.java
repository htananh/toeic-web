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
import BEAN.readingquestion;
import BEAN.result;


@WebServlet("/SubmitReadexecise")
public class SubmitReadexecise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SubmitReadexecise() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		
		String readexeciseidstr = request.getParameter("readexeciseid");
		int readexeciseid = Integer.parseInt(readexeciseidstr);
		List<readingquestion> CorrectAnswers= DAO.ReadexeciseDAO.DisplayReadingquestion(conn, readexeciseid);
		int lengtQuestion = DAO.ReadexeciseDAO.lengtQuestion(conn,readexeciseid);
		List<answerUser> listAsUser= new ArrayList();
		int correctanswernum=0;
		for(int i=2;i <= lengtQuestion+1;i++) {
			
			String answers=request.getParameter("ans["+i+"]");
			if(answers!=null) {
			answerUser asUser= new answerUser();
			asUser.setNum(i);
			asUser.setAnswers(answers);
			
			listAsUser.add(asUser);
			String correctanswer= DAO.ReadexeciseDAO.GetCorrectanswer(conn, i);
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
		
		
		
		List<readingquestion> list = DAO.ReadexeciseDAO.DisplayReadingquestion(conn, readexeciseid);
		request.setAttribute("Listreadingquestion", list);
		request.setAttribute("readexeciseid", readexeciseid);
		request.setAttribute("CorrectAnswers",CorrectAnswers);
		request.setAttribute("listAsUser",listAsUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/ResultReadexecise.jsp");
		dispatcher.forward(request, response);
	}

}
