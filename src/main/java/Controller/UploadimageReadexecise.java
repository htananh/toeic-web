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

import BEAN.readexecise;


@WebServlet("/UploadimageReadexecise")
public class UploadimageReadexecise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UploadimageReadexecise() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		String readexeciseidstr = request.getParameter("readexeciseid");
		int readexeciseid = Integer.parseInt(readexeciseidstr);
		
		DAO.ReadexeciseDAO.UploadSingleFile(request, response, conn, readexeciseid);
		List<readexecise> list = DAO.ReadexeciseDAO.DisplayReadexecise(conn);
		request.setAttribute("ListReadexecise", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Readexecise.jsp");
		dispatcher.forward(request, response);
	}

}
