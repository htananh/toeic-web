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

import BEAN.vocabularycontent;
import BEAN.cmtgrammar;


@WebServlet("/VocabularyGuideLineContentUser")
public class VocabularyGuideLineContentUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public VocabularyGuideLineContentUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		String vocabularyguidelineidstr= request.getParameter("vocabularyguidelineid");
		int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidstr);
		
		List<vocabularycontent> list = DAO.VocabularyGuideLine.DisplayVocabularyContent(conn, vocabularyguidelineid);
		request.setAttribute("ListContent", list);
		request.setAttribute("characterDB1", "\n");
		request.setAttribute("characterHTML1", "<br/>");
		request.setAttribute("vocabularyguidelineid", vocabularyguidelineid);
		List<cmtgrammar> listcmt = DAO.CommentDAO.DisplayComment(conn, vocabularyguidelineid);
		request.setAttribute("listComment", listcmt);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/VocabularyGuideLineContent.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
