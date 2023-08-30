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


import BEAN.GrammarGuideLine;


@WebServlet("/GrammarGuideLineUser")
public class GrammarGuideLineUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public GrammarGuideLineUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		int count =6;
		int pageId=pageNumber;
		if (pageNumber==1)
		{
			
		}else {
			pageNumber=pageNumber-1;
			pageNumber=pageNumber*count+1;
		}
		
		int SumRow=DAO.GrammarGuideLineDAO.countRow(conn);
		int MaxDisplay=(SumRow/count)+1;
		
		List<GrammarGuideLine> list = DAO.GrammarGuideLineDAO.DisplayGrammarGuideLinePage(pageNumber, count, conn);
		request.setAttribute("ListGrammarGuideLine", list);
		request.setAttribute("pageNumber", pageId);
		request.setAttribute("MaxDisplay", MaxDisplay);
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/GrammarGuideLineUser.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
