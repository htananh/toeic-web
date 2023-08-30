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

import BEAN.answerUser;
import BEAN.listenquestion;



@WebServlet("/SubmitListenexecise")
public class SubmitListenexecise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SubmitListenexecise() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		
		String listenexeciseidstr = request.getParameter("listenexeciseid");
		int listenexeciseid = Integer.parseInt(listenexeciseidstr);
		List<listenquestion> CorrectAnswers= DAO.ListenexeciseDAO.DisplayListenquestion(conn, listenexeciseid);
		int lengtQuestion = DAO.ListenexeciseDAO.lengtQuestion(conn,listenexeciseid);
		List<answerUser> listAsUser= new ArrayList();
		int correctanswernum=0;
		for(int i=2;i <= lengtQuestion+1;i++) {
			
			String answers=request.getParameter("ans["+i+"]");
			if(answers!=null) {
			answerUser asUser= new answerUser();
			asUser.setNum(i);
			asUser.setAnswers(answers);
			
			listAsUser.add(asUser);
			String correctanswer= DAO.ListenexeciseDAO.GetCorrectanswer(conn, i);
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
		
		
		
		List<listenquestion> list = DAO.ListenexeciseDAO.DisplayListenquestion(conn, listenexeciseid);
		request.setAttribute("ListListenquestion", list);
		request.setAttribute("listenexeciseid", listenexeciseid);
		request.setAttribute("CorrectAnswers",CorrectAnswers);
		request.setAttribute("listAsUser",listAsUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/ResultListenexecise.jsp");
		dispatcher.forward(request, response);
	}

}
