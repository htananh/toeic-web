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
import BEAN.vocabularyguideline;


@WebServlet("/UploadimageVocabularyGuideLine")
public class UploadimageVocabularyGuideLine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UploadimageVocabularyGuideLine() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		String vocabularyguidelineidstr = request.getParameter("vocabularyguidelineid");
		int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidstr);
		DAO.VocabularyGuideLine.UploadSingleFile(request, response,conn,vocabularyguidelineid);
		List<vocabularyguideline> list=DAO.VocabularyGuideLine.DisplayVocabularyGuideLine(conn);
		request.setAttribute("Listvocabularyguideline", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/VocabularyGuideLine.jsp");
		dispatcher.forward(request, response);
	}

}
