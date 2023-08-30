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


@WebServlet("/UploadimageGrammarGuideLine")
public class UploadimageGrammarGuideLine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UploadimageGrammarGuideLine() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		DAO.GrammarGuideLineDAO.UploadSingleFile(request, response,conn,grammarguidelineid);
		List<GrammarGuideLine> list = DAO.GrammarGuideLineDAO.DisplayGrammarGuideLine(conn);
		request.setAttribute("ListGrammarGuideLine", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/GrammarGuideLine.jsp");
		dispatcher.forward(request, response);
		
	}

}
