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

import BEAN.examinationquestion;
import BEAN.readingquestion;


@WebServlet("/ReadingQuestionController")
public class ReadingQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ReadingQuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		String readexeciseidstr = request.getParameter("readexeciseid");
		int readexeciseid = Integer.parseInt(readexeciseidstr);
		List<readingquestion> list = DAO.ReadexeciseDAO.DisplayReadingquestion(conn, readexeciseid);
		request.setAttribute("Listreadingquestion", list);
		request.setAttribute("readexeciseid", readexeciseid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/ReadingQuestion.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
