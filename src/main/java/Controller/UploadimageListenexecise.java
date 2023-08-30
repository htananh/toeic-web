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


@WebServlet("/UploadimageListenexecise")
public class UploadimageListenexecise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UploadimageListenexecise() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		String listenexeciseidstr = request.getParameter("listenexeciseid");
		int listenexeciseid = Integer.parseInt(listenexeciseidstr);
		
		DAO.ListenexeciseDAO.UploadSingleFile(request, response, conn, listenexeciseid);
		List<listenexecise> list = DAO.ListenexeciseDAO.Displaylistenexecise(conn);
		request.setAttribute("ListListenexecise", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Listenexecise.jsp");
		dispatcher.forward(request, response);
	}

}
