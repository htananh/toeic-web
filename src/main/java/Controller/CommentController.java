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
import BEAN.cmtgrammar;
import DAO.CommentDAO;

@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CommentController() {
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
		String cmtgrammarcontent= request.getParameter("cmtgrammarcontent");
		String memberidstr= request.getParameter("memberid");
		int memberid = Integer.parseInt(memberidstr);
		String grammarguidelineidstr= request.getParameter("grammarguidelineid");
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		CommentDAO.InsertComment(request, conn, cmtgrammarcontent, memberid, grammarguidelineid);
	
		List<cmtgrammar> list = DAO.CommentDAO.DisplayComment(conn, grammarguidelineid);
		request.setAttribute("listComment", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/ResultComment.jsp");
		dispatcher.forward(request, response);
	}

}
