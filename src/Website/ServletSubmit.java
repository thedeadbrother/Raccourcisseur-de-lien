package Website;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

import dao.User;
import dao.userDAO;
 
@WebServlet("/login")
public class ServletSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	userDAO userDAO;
	
	public ServletSubmit() {
		super();
		try {
			userDAO = new userDAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("test works like a charm");
			
			
			User returnedUser = userDAO.getUser(request.getParameter("login"));
			
			if(returnedUser != null) {
				Session.LOGGED = true;
				Session.USERNAME = returnedUser.Login;
				Session.ID_USER = returnedUser.idUser;
				
			}else {
				User newUser = new User();
				newUser.Login = request.getParameter("login");
				newUser.password = request.getParameter("pwd");
				this.userDAO.insert(newUser);
				Session.LOGGED = true;
				Session.USERNAME = request.getParameter("login");
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
