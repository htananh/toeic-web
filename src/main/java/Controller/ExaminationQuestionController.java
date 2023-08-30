package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.examinationquestion;

@WebServlet("/ExaminationQuestionController")
public class ExaminationQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ExaminationQuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(true);  
		
		if(session.getAttribute("SSfullname")!=null)
		{
			
			Connection conn = DB.DbConnection.CreateConnnection();
			String examinationidstr = request.getParameter("examinationid");
			int examinationid = Integer.parseInt(examinationidstr);
			List<examinationquestion> list = DAO.ExaminaionQuestionDAO.DisplayExaminationQuestionPage(conn, examinationid);
			request.setAttribute("Listexaminationquestion", list);
			request.setAttribute("examinationid", examinationid);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/ExaminationQuestion.jsp");
			dispatcher.forward(request, response);
		}else
		{
			request.setAttribute("messageLogin", "Đăng nhập trước khi làm bài thi");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Login.jsp");
			dispatcher.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
				doGet(request, response);
	}
	

}
