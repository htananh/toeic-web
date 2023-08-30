package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import BEAN.examination;

import java.util.*;

@WebServlet("/ExaminationUserController")
public class ExaminationUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExaminationUserController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		int count =2;
		int pageId=pageNumber;
		if (pageNumber==1)
		{
			
		}else {
			pageNumber=pageNumber-1;
			pageNumber=pageNumber*count+1;
		}
		
		int SumRow=DAO.ExaminationDAO.countRow(conn);
		int MaxDisplay=(SumRow/count)+1;
		
		List<examination> list = DAO.ExaminationDAO.DisplayExaminationPage(pageNumber, count, conn);

		request.setAttribute("pageNumber", pageId);
		request.setAttribute("MaxDisplay", MaxDisplay);
		
		request.setAttribute("ListExamination", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Examination.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
