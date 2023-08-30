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


@WebServlet("/UploadImageAndSoundExamination")
public class UploadImageAndSoundExamination extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public UploadImageAndSoundExamination() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn =DB.DbConnection.CreateConnnection();
		DAO.ExaminationDAO.UploadMultipleFile(request, response, conn);
		List<examination> list = DAO.ExaminationDAO.DisplayExamination(conn);
		request.setAttribute("ListExamination", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Examination.jsp");
		dispatcher.forward(request, response);
	}

}
