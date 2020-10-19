package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import tools.DatabaseConnection;
import vo.User;

/**
 * Servlet implementation class CheckUserExistServlet
 */
@WebServlet("/CheckUserExistServlet")
public class CheckUserExistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从request中获取ajax传过来的userName
		String userName = request.getParameter("userName");
		String jsonStr = "";
		DatabaseConnection dbc= new DatabaseConnection();
		Connection conn = dbc.getConnection();
		UserDao userDao = new UserDao(conn);
		User u = new User();
		u = userDao.getByUserName(userName);
		if(u==null)
		{
			response.addIntHeader("code", 0);
		}
		else {
			response.addIntHeader("code", 1);
		}
		response.setContentType("text/html;charset=utf-8");
		System.out.println("CheckUserExist已经检测输入的userName在不在了");
	}

}
