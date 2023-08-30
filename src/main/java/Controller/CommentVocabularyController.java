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

import BEAN.cmtvocabulary;
import DAO.CommentDAO;


@WebServlet("/CommentVocabularyController")
public class CommentVocabularyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CommentVocabularyController() {
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
		String cmtvocabularycontent= request.getParameter("cmtvocabularycontent");
		String memberidstr= request.getParameter("memberid");
		int memberid = Integer.parseInt(memberidstr);
		String vocabularyguidelineidstr= request.getParameter("vocabularyguidelineid");
		int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidstr);
		CommentDAO.InsertCommentVocabulary(request, conn, cmtvocabularycontent, memberid, vocabularyguidelineid);
	
		List<cmtvocabulary> list = DAO.CommentDAO.DisplayCommentVocabulary(conn, vocabularyguidelineid);
		request.setAttribute("listComment", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/ResultCommentVocabulary.jsp");
		dispatcher.forward(request, response);
	}

}
