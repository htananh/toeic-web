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
import BEAN.cmtgrammar;


@WebServlet("/GrammarGuideLineContentUser")
public class GrammarGuideLineContentUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GrammarGuideLineContentUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		String grammarguidelineidstr= request.getParameter("grammarguidelineid");
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		
		List<GrammarGuideLine> list = DAO.GrammarGuideLineDAO.DisplayGrammarGuideLineContent(conn, grammarguidelineid);
		request.setAttribute("ListContent", list);
		request.setAttribute("characterDB1", "\n");
		request.setAttribute("characterHTML1", "<br/>");
		request.setAttribute("grammarguidelineid", grammarguidelineid);
		List<cmtgrammar> listcmt = DAO.CommentDAO.DisplayComment(conn, grammarguidelineid);
		request.setAttribute("listComment", listcmt);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/GramarGuideLineContent.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
