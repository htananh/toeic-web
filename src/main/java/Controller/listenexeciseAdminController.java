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


@WebServlet("/listenexeciseAdminController")
public class listenexeciseAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public listenexeciseAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Connection conn = DB.DbConnection.CreateConnnection();


		
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		int count =3;
		int pageId=pageNumber;
		if (pageNumber==1)
		{
			
		}else {
			pageNumber=pageNumber-1;
			pageNumber=pageNumber*count+1;
		}
		
		int SumRow=DAO.ListenexeciseDAO.countRow(conn);
		int MaxDisplay=(SumRow/count)+1;
		
		List<listenexecise> list = DAO.ListenexeciseDAO.DisplayListenexecisePage(pageNumber, count, conn);
		request.setAttribute("ListListenexecise", list);
		request.setAttribute("pageNumber", pageId);
		request.setAttribute("MaxDisplay", MaxDisplay);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Listenexecise.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Connection conn = DB.DbConnection.CreateConnnection();
		String listenexecisename= request.getParameter("listenexecisename");
		
		listenexecise exam = new listenexecise();
		exam.setListenexecisename(listenexecisename);
		DAO.ListenexeciseDAO.InsertListenexeciseName(request, response, conn, exam);
		
		List<listenexecise> list = DAO.ListenexeciseDAO.Displaylistenexecise(conn);
		request.setAttribute("ListListenexecise", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Listenexecise.jsp");
		dispatcher.forward(request, response);
	}

}
