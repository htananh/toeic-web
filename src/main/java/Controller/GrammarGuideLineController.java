package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BEAN.GrammarGuideLine;
import java.util.*;

@WebServlet("/GrammarGuideLineController")
public class GrammarGuideLineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public GrammarGuideLineController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Connection conn = DB.DbConnection.CreateConnnection();
		String grammarname= request.getParameter("grammarname");
		
		GrammarGuideLine gr = new GrammarGuideLine();
		gr.setGrammarname(grammarname);
		
	
		DAO.GrammarGuideLineDAO.InsertGrammarGuideLineName(request, response, conn, gr);
		List<GrammarGuideLine> list = DAO.GrammarGuideLineDAO.DisplayGrammarGuideLine(conn);
		request.setAttribute("ListGrammarGuideLine", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/GrammarGuideLine.jsp");
		dispatcher.forward(request, response);
	}

}
