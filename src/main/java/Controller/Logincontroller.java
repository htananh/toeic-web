package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.member;

import java.sql.*;
@WebServlet("/Logincontroller")
public class Logincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Logincontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DB.DbConnection.CreateConnnection();
		String membername=request.getParameter("membername");
		String memberpass=request.getParameter("memberpass");
		member mem = new member();
		mem.setMembername(membername);
		mem.setMemberpass(memberpass);
		
		String fullname= DAO.LoginDAO.GetFullName(conn, mem);
		int memberid=DAO.LoginDAO.GetMemberId(conn, mem);
		int loginCheck = DAO.LoginDAO.CheckLogin(conn, mem);
		
		if(loginCheck>=1)
		{
			int categorymemberid = DAO.LoginDAO.Authorization(conn, mem);
			if(categorymemberid==1)
			{
			HttpSession session = request.getSession(true);
			session.setAttribute("SSfullname",fullname);
			session.setAttribute("SSmemberid",memberid);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("View/Home.jsp");
			dispatcher.forward(request, response);
			}else {
				HttpSession session = request.getSession(true);
				session.setAttribute("SSadmimname",fullname);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin/Home.jsp");
				dispatcher.forward(request, response);
				}
		}else {
			request.setAttribute("messageLogin", "Sai tên đăng nhập hoặc mật khẩu.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("View/Login.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
