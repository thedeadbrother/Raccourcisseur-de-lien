package Website;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Website.Session;

@WebServlet("/home")
public class ServeLetAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServeLetAccount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(Session.LOGGED)
				this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
			else
				response.sendRedirect(request.getContextPath() + "/login");
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logged out");
		Session.logOut();
		response.sendRedirect("/Server_10/login");
    }
}
