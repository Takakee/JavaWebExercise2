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
 * Servlet implementation class SignUpController
 */
@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��request�л�ȡajax��������userName
		String userName = request.getParameter("userName");
		String chrName = request.getParameter("chrName");
		String mail = request.getParameter("mail");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String password = request.getParameter("password");
		String jsonStr = "";
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConnection();
		User u = new User(userName, password, chrName, "user");
		UserDao userDao = new UserDao(conn);
		
		try {
			boolean result = userDao.insertUser(u);
			if(result)
			{ //����ɹ�
				response.addIntHeader("code", 0);
			}
			else { //����ʧ��
				response.addIntHeader("code", 1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
