package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GrammarGuideLineContentForward")
public class GrammarGuideLineContentForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GrammarGuideLineContentForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grammarguidelineidstr= request.getParameter("grammarguidelineid");
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		request.setAttribute("grammarguidelineid", grammarguidelineid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/GrammarGuideLineContent.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
