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

import BEAN.listenexecise;
import BEAN.readexecise;
import BEAN.vocabularyguideline;


@WebServlet("/UploadImageAndSoundListenexecise")
public class UploadImageAndSoundListenexecise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UploadImageAndSoundListenexecise() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn =DB.DbConnection.CreateConnnection();
		DAO.ListenexeciseDAO.UploadMultipleFile(request, response, conn);
		List<listenexecise> list = DAO.ListenexeciseDAO.Displaylistenexecise(conn);
		request.setAttribute("ListListenexecise", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Listenexecise.jsp");
		dispatcher.forward(request, response);
	}

}
