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
import BEAN.vocabularyguideline;


@WebServlet("/DeleteExaminationController")
public class DeleteExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DeleteExaminationController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		DAO.ExaminationDAO.DeleteExamination(request, response, conn, examinationid);
		List<examination> list = DAO.ExaminationDAO.DisplayExamination(conn);
		request.setAttribute("ListExamination", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Examination.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
