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




import BEAN.examination;


@WebServlet("/examinationAdminController")
public class examinationAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public examinationAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		List<examination> list = DAO.ExaminationDAO.DisplayExamination(conn);
		request.setAttribute("ListExamination", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Examination.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Connection conn = DB.DbConnection.CreateConnnection();
		String examinationame= request.getParameter("examinationame");
		
		examination exam = new examination();
		exam.setExaminationame(examinationame);
		DAO.ExaminationDAO.InsertGrammarGuideLineName(request, response, conn, exam);
		
		List<examination> list = DAO.ExaminationDAO.DisplayExamination(conn);
		request.setAttribute("ListExamination", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Examination.jsp");
		dispatcher.forward(request, response);
		
		
		
	}
	
	
}
